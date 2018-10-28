import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainGatway {
	static ServerSocket socket = null;
	static InetAddress myipaddress = null;
	static int myport;

	public static void main(String args[]) throws Exception {
		initialise();
	}

	public static void initialise() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the your computer ip address =  ");
		myipaddress = InetAddress.getByName(bufferedReader.readLine());
		System.out.println("Enter the port you want to listen = ");
		myport = Integer.parseInt(bufferedReader.readLine());
		socket = new ServerSocket(myport, 100, myipaddress);
		System.out.println("<-------FarmServer is running-------->");
		while (true) {
			MyCommunicationSocket communicationSocket = new MyCommunicationSocket(socket.accept());
			System.out.println("socket accepted for incomming msg ");
			Myservice myservice = new Myservice();
			String msg = communicationSocket.receaveMsg();
			System.out.println("message received at machine sub server = " + msg);
			if (msg.equalsIgnoreCase("cpu")) {

				double cpuusages = myservice.getCpuUsages();
				communicationSocket.sendMessage(Double.toString(cpuusages));
				System.out.println("cpu load send is  = " + cpuusages);
			} else {
				int prime = 0;
				try {
					prime = myservice.generatePrime(Integer.parseInt(msg));
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("problem in generating prime number ");
				}
				communicationSocket.sendMessage(Integer.toString(prime));
				System.out.println("prime number send");
			}

		}

	}

}
