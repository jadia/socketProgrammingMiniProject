import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class MainGatway {
	static InetAddress serverIpAddress = null;
	int farmListenport;
	int clientListenPort;
	static FarmProvider farmprovider = null;
	ServerSocket farmSocket = null;
	ServerSocket clientServerSocket = null;
	int numberOfFaarms;

	public static void main(String[] args) throws Exception {
		MainGatway gatway = new MainGatway();
		farmprovider = new FarmProvider();
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the ip address this server machine you want are running = ");
		serverIpAddress = InetAddress.getByName(reader.readLine());

		gatway.initializationFarms();

		gatway.acceptClientConnectionToServicce();

	}

	public void initializationFarms() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of farm you want to keep =");
		numberOfFaarms = Integer.parseInt(reader.readLine());

		for (int i = 0; i < numberOfFaarms; i++) {
			System.out.println("Enter the ip address of " + i + "th farm =");
			String ip = reader.readLine();
			InetAddress inetAddress = InetAddress.getByName(ip);
			System.out.println("Enter the corresponding port number of farms =");
			int port = Integer.parseInt(reader.readLine());
			farmprovider.listoffarms.add(new FarmId(inetAddress, port));
		}
		System.out.println("All farm are registed with server");

//		Thread updaterTHread = new Thread(new UpdateFarmList());
//		updaterTHread.start();
	}

	public void acceptClientConnectionToServicce() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the port on whichyou want to listen to client to service = ");

		clientListenPort = Integer.parseInt(reader.readLine());
		clientServerSocket = new ServerSocket(clientListenPort, 20, serverIpAddress);
		System.out.println("<------server is running for client--------> ");
		while (true) {

			Thread newconnection = new Thread(new Client(new ClientSocket(clientServerSocket.accept())));
			System.out.println("CLient thread is created");
			newconnection.start();
			System.out.println("<------connection accepted and new thread started ------->");

		}
	}

}
