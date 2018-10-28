import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyCommunicationSocket {
	Socket newConnection = null;
	InputStream inputStream = null;
	OutputStream outputStream = null;

	public MyCommunicationSocket(Socket s) throws Exception {
		// TODO Auto-generated constructor stub
		newConnection = s;
		inputStream = newConnection.getInputStream();
		outputStream = newConnection.getOutputStream();

	}

	public void sendMessage(String msg) throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.write(msg);
		bufferedWriter.flush();
		System.out.println("prime nuberSend is " + msg);
	}

	public String receaveMsg() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println("checkpoint 1");
		String receavedmsg = bufferedReader.readLine();
		System.out.println("checkpoint 1");
		System.out.println("Message Received at farm is =" + receavedmsg);
		return receavedmsg;

	}

}
