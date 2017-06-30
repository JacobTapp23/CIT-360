/* From the IntelliJ IDE, run this program. Then using a browser on the
 * same computer where this program is running, type
 * "localhost:8080/second" (without the quotes) into the browser's
 * address bar and then press enter. You should see a report from this
 * Servlet in your browser.
 */

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class Second extends HttpServlet {
	private static final String
			doctype = "<!DOCTYPE HTML>\n",
			html = "<html lang=\"en-us\">\n",
			_html = "</html>\n",
			head = "<head>\n",
			_head = "</head>\n",
			charset = "<meta charset=\"utf-8\">\n",
			author = "<meta name=\"author\" content=\"Second Servlet\">\n",
			title = "<title>",
			_title = "</title>\n",
			body = "<body>\n",
			_body = "</body>\n",
			h1 = "<h1>",
			_h1 = "</h1>\n",
			h2 = "<h2>",
			_h2 = "</h2>\n",
			p = "<p>",
			_p = "</p>\n",
			div = "<div>",
			_div = "</div>\n";
	private int requestNum = 0;

	@Override
	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.print(doctype);
		out.print(html);
		out.print(head);
		out.print(charset);
		out.print(author);
		out.print(title + "Second Servlet" + _title);
		out.print(_head);

		out.print(body);
		out.print(h1 + "Second Servlet" + _h1);

		out.print(h2 + "User Principal" + _h2);
		out.print(div + req.getUserPrincipal() + _div);

		out.print(h2 + "Remote User" + _h2);
		out.print(div + req.getRemoteUser() + _div);

		out.print(h2 + "URL" + _h2);
		out.print(div + req.getRequestURL() + _div);

		out.print(h2 + "Method" + _h2);
		out.print(div + req.getMethod() + _div);

		out.print(h2 + "Session ID" + _h2);
		out.print(div + req.getRequestedSessionId() + _div);
		out.print(div + "from cookie: "
						+ req.isRequestedSessionIdFromCookie() + _div);
		out.print(div + "from URL: "
				+ req.isRequestedSessionIdFromURL() + _div);
		out.print(div + "is valid: "
				+ req.isRequestedSessionIdValid() + _div);

		HttpSession session = req.getSession();
		if (session != null) {
			out.print(h2 + "Session" + _h2);
			out.print(div + "id: " + session.getId() + _div);
			out.print(div + "creation: " + session.getCreationTime() + _div);
			out.print(div + "accessed: " + session.getLastAccessedTime() +
					_div);
			out.print(div + "max interval: "
					+ session.getMaxInactiveInterval() + _div);
		}

		out.print(h2 + "Header" + _h2);
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = req.getHeader(name);
			out.print(div + name + ": " + value + _div);
		}

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			out.print(h2 + "Cookies" + _h2);
			for (Cookie c : req.getCookies()) {
				out.println(div + c.getName() + " " + c.getValue() + _div);
			}
		}

		out.print(h2 + "Request Number" + _h2);
		out.print(p + nextRequestNumber() + _p);

		out.print(_body);
		out.print(_html);
	}

	private synchronized int nextRequestNumber() {
		return ++requestNum;
	}

	@Override
	public void destroy() {
	}
}
