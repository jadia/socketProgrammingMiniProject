import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class WorkerNodeConnection implements Runnable {
	int port;

	public WorkerNodeConnection(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		int length = 10;
		byte[] buf = new byte[length];
		DatagramPacket receivedpacket = new DatagramPacket(buf, length);
		DatagramSocket socket = null;
		int workerNodeport = 5555;
		while (true) {
			try {
				socket = new DatagramSocket(2222);
				socket.receive(receivedpacket);
				// ByteArrayInputStream bais = new ByteArrayInputStream(buf);
				// DataInput di = new DataInputStream(bais);

				// workerNodeport = di.readInt();
				// String datareceived= receivedpacket.getData().toString();
				System.out.println("data received = " + workerNodeport + "sender port = " + receivedpacket.getPort());

				FarmId farmId = new FarmId(receivedpacket.getAddress(), workerNodeport);
				FarmProvider.listoffarms.add(farmId);
				System.out.println("new worker Nodes added port address " + workerNodeport);
				byte[] buffer = "Done".getBytes();

				DatagramPacket sendpacket = new DatagramPacket(buffer, buffer.length, receivedpacket.getAddress(),
						workerNodeport);
				socket.send(sendpacket);
				socket.close();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/*
		 * try { ServerSocket socket = new ServerSocket(port); while(true) { Socket
		 * newCo = socket.accept(); FarmSocket farmSocket = new FarmSocket(newCo);
		 * String data = farmSocket.receivePrimeFromFarm();
		 * System.out.println("data received = "+data); int port = 0; try { port =
		 * Integer.parseInt(data); } catch (Exception e) { // TODO: handle exception
		 * System.out.println("Nt able to parse "); } FarmId farmId = new
		 * FarmId(newCo.getInetAddress(), port); FarmProvider.listoffarms.add(farmId);
		 * System.out.println("new worker Nodes added "); newCo.close(); }
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * 
		 * 
		 */

	}

}
