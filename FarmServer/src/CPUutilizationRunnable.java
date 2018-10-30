import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CPUutilizationRunnable implements Runnable {
	InetAddress inetAddress;
	static ServerSocket cpuServerSocket = null;

	int port;

	public CPUutilizationRunnable(InetAddress inetAddress, int port) {
		this.inetAddress = inetAddress;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			cpuServerSocket = new ServerSocket(port + 1, 100, inetAddress);
			while (true) {
				MyCommunicationSocket communicationSocket = new MyCommunicationSocket(cpuServerSocket.accept());
				Myservice myservice = new Myservice();
				String msg = communicationSocket.receaveMsg();

				if (msg.trim().equalsIgnoreCase("cpu")) {
					String cpuusages = myservice.getCpuUsages();
					communicationSocket.sendMessage(cpuusages);
					System.out.println("CPUusages send by farm is =" + cpuusages);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
