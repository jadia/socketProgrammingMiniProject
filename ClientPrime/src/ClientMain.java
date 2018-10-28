import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

	public static void main(String[] args) {

		if (args.length != 2)
			System.out.println("This program requires three command line arguments <serverIP><serverport>.");
		else {
			try {
				String acceptorHost = args[0];
				// this is change to github
				int acceptorPort = Integer.parseInt(args[1]);

				// instantiates a data socket
				MyStreamSocket mySocket = new MyStreamSocket(acceptorHost, acceptorPort);
				/**/ System.out.println("Connection request granted");
				System.out.println("Enter the upper limit of the number ");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String message = bufferedReader.readLine();

				mySocket.sendMessage(message);
				/**/ System.out.println("Message sent: " + message);
				message = mySocket.receiveMessage();
				System.out.println("Received message: " + message);
				mySocket.close();
				System.out.println("data socket closed");
			} // end try
			catch (Exception ex) {
				ex.printStackTrace();
			}
		} // end else
	} // end main

}
