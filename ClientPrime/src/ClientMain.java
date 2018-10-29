import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class ClientMain {
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the server's ip address = ");
		String acceptorHost = bufferedReader.readLine();

		System.out.println("Enter the port address of server");
		int acceptorPort = Integer.parseInt(bufferedReader.readLine()); // instantiates a data socket
		MyStreamSocket mySocket = new MyStreamSocket(acceptorHost, acceptorPort);
		boolean more = true;
		System.out.println("<====Enter done when want to stop the service====> ");

		while (more) {
			
			System.out.println("Enter the number ");
			String message = bufferedReader.readLine();

			if (message.trim().equalsIgnoreCase("done")) {
				more = false;
				break;
			}

			mySocket.sendMessage(message);
System.out.println("message sent from client = "+message);
			message = mySocket.receiveMessage();
			}
		mySocket.close();
		// end try
	} // end else
	// end main

}
