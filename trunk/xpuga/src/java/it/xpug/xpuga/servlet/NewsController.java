package it.xpug.xpuga.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NewsController extends HttpServlet {

	private static String location = null;
	private ServletContext context;
	private ServletConfig config;
	
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
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
		if (this.location == null)
			setLocation(location);
	}

	private void setLocation(String location) {
		synchronized(NewsController.class) {
			this.location = location;
		}
	}

	private String getLocation() {
		synchronized(NewsController.class) {
			return this.location;
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
