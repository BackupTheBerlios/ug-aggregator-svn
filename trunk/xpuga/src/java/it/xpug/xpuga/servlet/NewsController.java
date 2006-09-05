package it.xpug.xpuga.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NewsController extends HttpServlet {

	private static final long serialVersionUID = -1753743437930868893L;
	private static String location = null;
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
		initLocation(config.getInitParameter("location"));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		if ("/location".equals(req.getPathInfo())) {
			doGetLocation(req, res); return;
		}

		// getRealLocation() + File.separator + "fileName";
		// req.setAttribute("location", getLocation());
		
		req.getRequestDispatcher("/jsp/news/list.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		if ("/location".equals(req.getPathInfo())) {
			doSetLocation(req, res);
			res.sendRedirect("/news");
			return;
		}

		res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}


	private void initLocation(String location) {
		if (NewsController.location == null)
			setLocation(location);
	}

	private void setLocation(String location) {
		synchronized(NewsController.class) {
			NewsController.location = location;
		}
	}

	private String getLocation() {
		synchronized(NewsController.class) {
			return NewsController.location;
		}
	}

	private String getRealLocation() {
		return context.getRealPath(getLocation());
	}

	private void doSetLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		setLocation(req.getParameter("location"));
		res.setContentType("text/plain");
		res.getWriter().println(getLocation());
	}

	private void doGetLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain");
		res.getWriter().println(getLocation());
	}

}
