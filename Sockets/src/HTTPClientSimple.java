import java.io.*;
import java.net.Socket;

/** A very simply HTTP 1.1 client that makes a request, prints the
 * response, and terminates.
 */
public class HTTPClientSimple {
	public static void main(String[] args) {
		PrintStream out = System.out;
		String host = "www.google.com";
//		String host = "www.w3.org";
//		String host = "www.amazon.com";
//		String host = "emp.byui.edu";
		int port = 80;
		String charset = "UTF-8";
		String command = "GET";
//		String url = "/";
		String url = "/stuff_that_is_cool.html";
//		String url = "/barzeer/cit160/";
		String protocol = "HTTP/1.1";

		try {
			// Open a socket to the server.
			Socket client = new Socket(host, port);

			// Form the HTTP 1.1. request.
			String request = command + " " + url + " " + protocol + "\r\n" +
					"Host: " + host + "\r\n" +
					"\r\n";

			// Print the request to the console for the user to see.
			out.println(request);

			// Convert the request from a Java String into an array of
			// bytes.  Get the OutputStream from the socket. Write the
			// request to the OutputStream so that the server will
			// receive it.
			byte[] buf = request.getBytes(charset);
			OutputStream os = client.getOutputStream();
			os.write(buf);
			os.flush();

			// Get the InputStream from the socket. Read the server's
			// response. Convert the reponse to a Java String and print
			// it to the console for the user to see.
			InputStream is = client.getInputStream();
			buf = new byte[4096];
			int read;
			while ((read = is.read(buf)) != -1) {
				String response = new String(buf, 0, read, charset);
				out.println(response);
			}

			// Close the streams and the socket.
			is.close();
			os.close();
			client.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
