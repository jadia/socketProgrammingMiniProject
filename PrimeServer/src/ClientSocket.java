import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
	Socket clientSocket;
	InputStream inputStream = null;
	OutputStream outputStream = null;

	public ClientSocket(Socket s) throws Exception {
		// TODO Auto-generated constructor stub
		this.clientSocket = s;
		inputStream = clientSocket.getInputStream();
		outputStream = clientSocket.getOutputStream();
	}
	
	public void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String data)throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
	bufferedWriter.write(data);
		bufferedWriter.flush();
		System.out.println("message send "+data);
	}
	public String receiveMessage()throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String data= bufferedReader.readLine();
	System.out.println("number received  = "+data);
	return data;
	}
}
