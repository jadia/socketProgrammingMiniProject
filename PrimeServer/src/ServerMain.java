import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class ServerMain {
	static InetAddress serverIpAddress = null;
	int farmListenport;
	int clientListenPort;
	static FarmProvider farmprovider = null;
	ServerSocket farmSocket = null;
	ServerSocket clientServerSocket = null;
	int numberOfFaarms;

	public static void main(String[] args) throws Exception {
		ServerMain gatway = new ServerMain();
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
		System.out.println("Enter the number of farm you want to initialize =");
		numberOfFaarms = Integer.parseInt(reader.readLine());

		for (int i = 0; i < numberOfFaarms; i++) {
			System.out.println("Enter the ip address of farm number "+ (i+1) +"=");
			String ip = reader.readLine();
			InetAddress inetAddress = InetAddress.getByName(ip);
			System.out.println("Enter the corresponding port number of farms =");
			int port = Integer.parseInt(reader.readLine());
			farmprovider.listoffarms.add(new FarmId(inetAddress, port));
		}
		System.out.println("<====Farm registration with server done=====>");
		System.out.println("<=====list of farm with ip and port========>");
		for (int i = 0; i < farmprovider.listoffarms.size(); i++) {
			FarmId farmId = farmprovider.listoffarms.get(i);
			System.out.println("Farm Number = " + i + "with ip address = " + farmId.ipaddress.getHostAddress()
					+ " with port number = " + farmId.port);

		}
	}

	public void acceptClientConnectionToServicce() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the port on which you want to listen to client for service = ");

		clientListenPort = Integer.parseInt(reader.readLine());
		clientServerSocket = new ServerSocket(clientListenPort, 20, serverIpAddress);
		System.out.println("<=======server is running for client=======> ");
// update server thread start hear
		
		Thread updaterTHread = new Thread(new UpdateFarmList());
//		updaterTHread.start();

		while (true) {
Client client= new Client(new ClientSocket(clientServerSocket.accept()));
System.out.println("client created");			
Thread newconnection = new Thread(client);
		newconnection.start();
			System.out.println("<------connection accepted and new thread started ------->");

		}
	}

}
