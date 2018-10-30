import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FarmMain {
	static ServerSocket socket = null;

	static InetAddress myipaddress = null;
	static int myport;

	public static void main(String args[]) throws Exception {
		initialise();
	}

	public static void initialise() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("");
		System.out.print("This farm's IP: ");
		myipaddress = InetAddress.getByName(bufferedReader.readLine());
		System.out.print("Port: ");
		myport = Integer.parseInt(bufferedReader.readLine());
		socket = new ServerSocket(myport, 100, myipaddress);
		System.out.println("");
		System.out.println("<-------FarmServer is running-------->");
		Thread thread = new Thread(new CPUutilizationRunnable(myipaddress, myport));
		thread.start();
		System.out.println("<------CPU request server also running----->");
		while (true) {
			MyCommunicationSocket communicationSocket = new MyCommunicationSocket(socket.accept());
			Myservice myservice = new Myservice();
			String msg = communicationSocket.receaveMsg();
			int prime = myservice.generatePrime(msg);
			communicationSocket.sendMessage(Integer.toString(prime));
			System.out.println("Prime number: " + prime);
			System.out.println("");

		}
	}
	/*
	 * if (msg.trim().equalsIgnoreCase("cpu")) {
	 * 
	 * System.out.println("request for cpuusages came "); String cpuusages =
	 * myservice.getCpuUsages();
	 * System.out.println("CPU useages calculated at farm = "+cpuusages);
	 * communicationSocket.sendMessage(cpuusages);
	 * System.out.println("cpu load send is  = " + cpuusages);
	 * 
	 * } else {
	 * 
	 * }
	 */

}
