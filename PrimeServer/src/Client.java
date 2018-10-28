import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {
	ClientSocket clientSocket;

	public Client(ClientSocket clientSocket) {
		// TODO Auto-generated constructor stub
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String data = null;
		try {
		
			data = receiveDataFromClient();
			System.out.println("data received from client  = "+data);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result;
		try {
			result = startprocessTosendandReceivePrimeNumber(data);
			sendBackToClientResult(result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String receiveDataFromClient() throws Exception {
		
		return clientSocket.receiveMessage();
		

	}

	public String startprocessTosendandReceivePrimeNumber(String data)throws Exception {
		FarmId farmId = FarmProvider.getFreeFarm();
		FarmSocket farmSocket = new FarmSocket( new Socket(farmId.ipaddress, farmId.port));
	
		farmSocket.sendNumberToFarmtoFindPrimeNumber(data);
		System.out.println("number send to firm for getting prime number ");
		String result = farmSocket.receivePrimeFromFarm();
		System.out.println("data received from firm is = "+result);
		return result;
		
	
	}

	public void sendBackToClientResult(String result) {
		try {
			clientSocket.sendMessage(result);
			System.out.println("Prime number sent to client is =  "+result);
			clientSocket.closeSocket();
			System.out.println("cient socket closed from server side");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
