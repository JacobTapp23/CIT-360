import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HTTPServerSimple {
	public static void main(String[] args) {
		PrintStream out = System.out;
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		int port = 8080;
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

					// Read from the HTTP request from the input stream.
					int read;
					while ((read = is.read(readBuf)) != -1) {
						String request = new String(readBuf, 0, read, charset);
						out.println(request);
						if (request.contains("\r\n\r\n")) {
							break;
						}
					}

					// Always respond with "not found".
					Date now = new Date();
					String response =
						"HTTP/1.1 404 Not Found\r\n" +
						"Content-Type: text/html; charset=" + charset + "\r\n" +
						"Content-Length: " + html.length() + "\r\n" +
						"Connection: close\r\n" +
						"Date: " + sdf.format(now) + "\r\n" +
						"\r\n" +
						html;

					out.println(response);
					os.write(response.getBytes(charset));
					os.flush();

					// Close the streams and the client socket.
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

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"EEE, dd MMM yyyy hh:mm:ss z");
	private static final String html =
		"<!DOCTYPE HTML>\n" +
		"<html lang=\"en-us\">\n" +
		"<head>\n" +
		"<meta charset=\"utf-8\">\n" +
		"<title>Error 404 (Not Found)</title>\n" +
		"<style>\n" +
		"body { font-family: Helvetica,Tahoma,Arial,Geneva,sans-serif; }\n" +
		"</style>\n" +
		"</head>\n" +
		"<body>\n" +
		"<p>The URL that you requested is not on this server.</p>\n" +
		"</body>\n" +
		"</html>\n";
}
