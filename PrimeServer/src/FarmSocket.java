import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FarmSocket {
Socket farmsocket = null;
InputStream inputStream;
OutputStream outputStream;
int cpuusages = 0;
int connection = 0;
	public FarmSocket(Socket s)throws Exception {
		// TODO Auto-generated constructor stub
	farmsocket = s;
	inputStream = farmsocket.getInputStream();
	outputStream = farmsocket.getOutputStream();
	}
	
	void closeFarm() {
		try {
			farmsocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String receivePrimeFromFarm()throws Exception {
	byte[] msgage = new byte[100];
		//BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
inputStream.read(msgage);
		String msg = new String(msgage);
	//	System.out.println("Prime Number received from farm is "+msg);
		inputStream.close();
		
		return msg;
		
	}
	public void sendNumberToFarmtoFindPrimeNumber(String msg)throws Exception  {
	//	System.out.println("ip address of firm on which this msg is send is "+farmsocket.getInetAddress().getHostAddress());
		BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(outputStream));
		writter.write(msg);
		writter.flush();
		//System.out.println("Data send to farms is = "+msg);
		}
	
}
