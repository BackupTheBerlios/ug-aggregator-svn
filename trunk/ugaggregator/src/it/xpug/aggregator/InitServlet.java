package it.xpug.aggregator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
	public static final String NEWSDB_DIR_PARAM = "newsdb_dir";
	public static final String DEFAULT_NEWSDB_DIR = "newsdb";

	public void init(ServletConfig config) throws ServletException {
		String dirName = config.getInitParameter(NEWSDB_DIR_PARAM);
		if (null == dirName) {
			dirName = DEFAULT_NEWSDB_DIR;
		}
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR, dirName);
		System.out.println("Vivo, esisto!");
	}
}
