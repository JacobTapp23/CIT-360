import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServerReorder {
	public static void main(String[] args) {
		PrintStream out = System.out;
		int port = 30128;
		String charset = "UTF-8";
		byte[] readBuf = new byte[16384];
		try {
			// Open the server socket that will accept connections from
			// clients.
			ServerSocket server = new ServerSocket(port, 10);
			out.println(server);

			while (true) {
				try {
					// Wait for a client to connect.
					Socket client = server.accept();
					out.println(client);

					// A client connected. Get the streams from the
					// socket that is connected to the client.
					InputStream is = client.getInputStream();
					OutputStream os = client.getOutputStream();

					// Read from the input stream.
					int read = is.read(readBuf);
					if (read != -1) {
						String request = new String(readBuf, 0, read, charset);
						out.println(request);

						// Reorder the message that came from the client
						// and send the reordered message back to the
						// client.
						String response = reorder(request);
						out.println(response);
						os.write(response.getBytes(charset));
						os.flush();
					}

					// Close the streams and socket that is connected to
					// the client.
					os.close();
					is.close();
					client.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}

				// Wrap around to the top of the while loop and wait for
				// another client to connect.
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private static String reorder(String s) {
		String[] a = s.split("\\s+");
		shuffle(a);
		return String.join(" ", a);
	}

	/** Implementation of the Durstenfeld shuffle. */
	private static String[] shuffle(String[] a) {
		for (int i = a.length - 1;  i > 0;  --i) {
			int j = (int)Math.floor(Math.random() * (i + 1));
			String swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}
		return a;
	}
}
