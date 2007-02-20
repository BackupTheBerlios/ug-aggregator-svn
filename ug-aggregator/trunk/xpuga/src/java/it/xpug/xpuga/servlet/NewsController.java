package it.xpug.xpuga.servlet;

import it.xpug.xpuga.Location;
import it.xpug.xpuga.NewsException;
import it.xpug.xpuga.NewsManager;
import it.xpug.xpuga.NewsPiece;
import it.xpug.xpuga.XDate;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsController extends HttpServlet {

	private static final long serialVersionUID = -1753743437930868893L;
	private static Location location = null;
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
		initLocation(config.getInitParameter("location"));
	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		if ("/location".equals(req.getPathInfo())) {
			doGetLocation(req, res); 
			return;
		}

		if ("/location/path".equals(req.getPathInfo())) {
			doGetRealLocation(req, res); 
			return;
		}

		if ("/form".equals(req.getPathInfo())) {
			req.getRequestDispatcher("/jsp/news/insert.jsp").forward(req, res);
			return;
		}

		doGetAllNews(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		if ("/location".equals(req.getPathInfo())) {
			synchronized(NewsController.class) {
				location = new Location(context.getRealPath("."), req.getParameter("location"));
				if (!location.exists()) {
					location.create();
					res.setStatus(201);
					return;
				}
				res.sendRedirect("/news");
				return;
			}
		}
		
		synchronized(NewsController.class) {
			try {
				
				String newsFilename = saveNews(req);
				res.sendRedirect(req.getContextPath()+"/news");

			} catch (ParseException e) {
				res.setStatus(422);
				req.getRequestDispatcher("/jsp/news/insert.jsp").forward(req, res);

			} catch (NewsException e) {
				res.setStatus(422);
				req.getRequestDispatcher("/jsp/news/insert.jsp").forward(req, res);

			}
		}
	}


	private String saveNews(HttpServletRequest req) throws ParseException, NewsException {
		NewsPiece news=new NewsPiece();
		req.setAttribute("errata-news", news);
		news.setTitle(req.getParameter("title"));
		news.setBody(req.getParameter("body"));
		news.setGroupName(req.getParameter("user-group"));
		String insertionDate = XDate.getCode(new Date());//req.getParameter("insertion-date");
		news.setInsertionDate(insertionDate);
		news.setExpirationDate(req.getParameter("expiration-date"));
		
		if (!news.isValid()) {
		    throw new NewsException("news invalida");
		}
		
		news.save(getRealLocation() + "/"+insertionDate);
		return insertionDate;
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

	private void initLocation(String path) {
		if (NewsController.location == null)
			NewsController.location = new Location(context.getRealPath("."), path);
	}

	private String getRealLocation() {
		return NewsController.location.getAbsolutePath();
	}

	private void doGetLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain");
		res.getWriter().println(NewsController.location.getPath());
	}

	private void doGetRealLocation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain");
		res.getWriter().println(getRealLocation());
	}
}
