package it.xpug.aggregator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XpugServlet extends HttpServlet 
{
	public static final String NEWSDB_DIR_PARAM = "newsdb_dir";
	public static final String DEFAULT_NEWSDB_DIR = "newsdb";

	public void init(ServletConfig config) throws ServletException {
		String dirName = config.getInitParameter(NEWSDB_DIR_PARAM);
		if (null == dirName) {
			dirName = DEFAULT_NEWSDB_DIR;
		}
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR, dirName);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		// ... handle GET requests
		rsp.setContentType("text/html");
		PrintWriter out = rsp.getWriter();
		out.println("<html>");
		out.println("<head><title>Servlet</title></head>");
		out.println("<body>");
		out.println("<p>This is a simple Servlet!</p>");
		out.println("</body></html>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		doGet(req,rsp);
	}
	
}
