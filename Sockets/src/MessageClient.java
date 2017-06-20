import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class MessageClient {
	public static void main(String[] args) {
		PrintStream out = System.out;
		String host = "localhost";
		int port = 30128;
		String charset = "UTF-8";
		String[] messages = {
				"Oh, what a beautiful morning",
				"It's only right to think about the girl you love and hold her tight",
				"Four score and seven years ago...",
				"I, Nephi, having been born of goodly parents.",
				"Sockets are fun."
		};
		byte[] readBuf = new byte[4096];

		for (int i = 0;  i < 3;  ++i) {
			try {
				// Connect to the server by opening a socket.
				Socket client = new Socket(host, port);

				// Choose one of the messages to send to the server.
				String message = choose(messages);

				// Print the message to the console for the user to see.
				out.println(message);

				// Open an output stream to the server and write the
				// message to the stream.
				OutputStream os = client.getOutputStream();
				byte[] writeBuf = message.getBytes(charset);
				os.write(writeBuf);
				os.flush();

				// Open an input stream from the server and read the
				// server's response.
				InputStream is = client.getInputStream();
				int read;
				while ((read = is.read(readBuf)) != -1) {
					String response = new String(readBuf, 0, read, charset);
					out.println(response);
				}

				// Close the streams and the socket.
				os.close();
				is.close();
				client.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static String choose(String[] a) {
		int i = (int)Math.floor(Math.random() * a.length);
		return a[i];
	}
}
