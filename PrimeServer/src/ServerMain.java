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
		System.out.print("This server's IP: ");
		serverIpAddress = InetAddress.getByName(reader.readLine());

		gatway.initializationFarms();

		gatway.acceptClientConnectionToServicce();

	}

	public void initializationFarms() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("");
		System.out.println("");
		System.out.println("Farm settings->");
		System.out.println("");
		System.out.print("Number of Farms: ");
		numberOfFaarms = Integer.parseInt(reader.readLine());

		for (int i = 0; i < numberOfFaarms; i++) {
			System.out.print("IP of farm " + (i + 1) + " : ");
			String ip = reader.readLine();
			InetAddress inetAddress = InetAddress.getByName(ip);
			System.out.print("Farm " + (i + 1) + "'s Port: ");
			int port = Integer.parseInt(reader.readLine());
			farmprovider.listoffarms.add(new FarmId(inetAddress, port));
		}
		System.out.println("<====Farm registration with server done=====>");
		System.out.println("");
		System.out.println("List of sub-servers active:");
		for (int i = 0; i < farmprovider.listoffarms.size(); i++) {
			FarmId farmId = farmprovider.listoffarms.get(i);
			System.out.println("Farm Number = " + (i + 1) + "-> IP: " + farmId.ipaddress.getHostAddress() + ", Port: "
					+ farmId.port);
		}
	}

	public void acceptClientConnectionToServicce() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("");
		System.out.print("Listening Port for Clients: ");

		clientListenPort = Integer.parseInt(reader.readLine());
		clientServerSocket = new ServerSocket(clientListenPort, 20, serverIpAddress);
		System.out.println("<=======Server is running for client=======> ");
// update server thread start hear

	
		while (true) {
			Client client = new Client(new ClientSocket(clientServerSocket.accept()));
			//System.out.println("client created");
			Thread newconnection = new Thread(client);
			newconnection.start();
			System.out.println("<------Connection accepted and new thread started ------->");
			System.out.println("");

		}
	}

}
