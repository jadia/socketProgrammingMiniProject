import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	
	public String receivePrimeFromFarm()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	String msg = br.readLine();
		inputStream.close();
		
		return msg;
		
	}
	public void sendNumberToFarmtoFindPrimeNumber(String msg)throws Exception  {
		BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(outputStream));
		writter.write(msg);
		writter.flush();
		System.out.println("Data send to farms is = "+msg);
		}
	
}
