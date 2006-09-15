package it.xpug.xpuga.servlet;

import it.xpug.xpuga.*;

import java.io.*;
import java.util.*;
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

		if ("/location/path".equals(req.getPathInfo())) {
			doGetRealLocation(req, res); return;
		}

		if ("/form".equals(req.getPathInfo())) {
			req.getRequestDispatcher("/jsp/news/insert.jsp").forward(req, res);
			return;
		}

		doGetAllNews(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		if ("/location".equals(req.getPathInfo())) {
			setLocation(req.getParameter("location"));
			res.sendRedirect("/news");
			return;
		}
	
		// TODO: insertion
		StringBuffer errors = new StringBuffer();
		errors.append("<errors>");
		if (req.getParameter("title").equals(""))
			errors.append("<error class='error' title='title'>Title couldn't be empty</error>");
		if (req.getParameter("body").equals(""))
			errors.append("<error class='error' title='body'>Body couldn't be empty</error>");
		if (req.getParameter("user-group").equals(""))
			errors.append("<error class='error' title='user-group'>UserGroup couldn't be empty</error>");
		if (req.getParameter("expiration-date").equals(""))
			errors.append("<error class='error' title='expiration-date'>ExpirationDate couldn't be empty</error>");
		errors.append("</errors>");
		if (errors.toString().indexOf("<error class=") > 0) {
			res.setStatus(422);
			res.getWriter().println(errors.toString());
			return;
		}

		String insertionDate = XDate.getCode(new Date());
		PrintWriter writer = new PrintWriter(new FileWriter(
					getRealLocation() + File.separator + insertionDate));
		writer.println(req.getParameter("title"));
		writer.println(req.getParameter("body"));
		writer.println(insertionDate);
		writer.println(req.getParameter("expiration-date"));
		writer.println(req.getParameter("user-group"));
		writer.close();

		res.setStatus(HttpServletResponse.SC_CREATED);
		res.setHeader("Location", "/news/" + insertionDate);

		// TODO: insertion


		// TODO: restore
		// res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	private void doGetAllNews(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		try {
			req.setAttribute("news", new NewsManager(getRealLocation()).getNewsList());
			req.getRequestDispatcher("/jsp/news/list.jsp").forward(req, res);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	private void initLocation(String location) {
		if (NewsController.location == null)
			setLocation(location);
	}

	private void setLocation(String location) {
		synchronized(NewsController.class) {
			NewsController.location = location;
			// TODO: insertion
			if (!new File(getRealLocation()).isDirectory())
				new File(getRealLocation()).mkdirs();
			// TODO: insertion
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

	private void doGetLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain");
		res.getWriter().println(getLocation());
	}

	private void doGetRealLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain");
		res.getWriter().println(getRealLocation());
	}
}
