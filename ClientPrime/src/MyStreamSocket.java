import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class MyStreamSocket {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	InputStream inStream = null;

	MyStreamSocket(String acceptorHost, int acceptorPort) throws SocketException, IOException {
		InetAddress address = InetAddress.getByName(acceptorHost);
		socket = new Socket(address, acceptorPort);
		setStreams();
	}

	/*
	 * MyStreamSocket(Socket socket) throws IOException { this.socket = socket;
	 * setStreams(); }
	 */
	private void setStreams() throws IOException {
		// get an input stream for reading from the data socket
		inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = socket.getOutputStream();
		// create a PrinterWriter object for character-mode output
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}

	public void sendMessage(String message) throws IOException {
		output.print(message + "\n");
		// The ensuing flush method call is necessary for the data to
		// be written to the socket data stream before the
		// socket is closed.
		output.flush();
	} // end sendMessage

	public String receiveMessage() throws IOException {
		// read a line from the data stream
		byte[] msg = new byte[1000];
		System.out.println("Waiting for result...");
		inStream.read(msg);
		String message = new String(msg);
		System.out.println("");
		System.out.println("Result: " + message);
		return message;
	} // end receiveMessage

	public void close() throws IOException {
		socket.close();
	}

}
