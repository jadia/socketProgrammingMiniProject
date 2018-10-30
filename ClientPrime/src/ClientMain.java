import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class ClientMain {
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Server IP: ");
		String acceptorHost = bufferedReader.readLine();

		System.out.print("Server Port: ");
		int acceptorPort = Integer.parseInt(bufferedReader.readLine()); // instantiates a data socket
		MyStreamSocket mySocket = new MyStreamSocket(acceptorHost, acceptorPort);
		boolean more = true;
		System.out.println("<====Type DONE to stop the service====> ");

		while (more) {
			System.out.println("");
			System.out.print("Number: ");
			String message = bufferedReader.readLine();

			if (message.trim().equalsIgnoreCase("done")) {
				more = false;
				break;
			}

			mySocket.sendMessage(message);
			System.out.println("** " + message + " sent to the server. **");
			message = mySocket.receiveMessage();
		}
		mySocket.close();
	}
}