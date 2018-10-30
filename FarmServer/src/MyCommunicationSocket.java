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
		newConnection = s;
		inputStream = newConnection.getInputStream();
		outputStream = newConnection.getOutputStream();

	}

	public void sendMessage(String msg) throws Exception {

		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.write(msg);
		bufferedWriter.flush();
		// System.out.println("Message send to server is " + msg);
	}

	public String receaveMsg() throws Exception {
		// System.out.println("inside receive function");
		// BufferedReader bufferedReader = new BufferedReader(new
		// InputStreamReader(inputStream));
		byte msg[] = new byte[100];
		inputStream.read(msg);
		// System.out.println("buffered reader initiated");
		String receavedmsg = new String(msg);

		System.out.println("");
		System.out.println("Message Received at farm is = " + receavedmsg);
		return receavedmsg;

	}

}
