package it.xpug.aggregator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewsFileWriter {

	public static final String IT_XPUG_AGGREGATOR_NEWS_DIR = "it.xpug.aggregator.newsDir";
	
	private News itsNews;
	private String pathToWrite = System.getProperty(IT_XPUG_AGGREGATOR_NEWS_DIR);

	public NewsFileWriter(News news) {
		itsNews = news;
		if (null == pathToWrite) pathToWrite = XpugServlet.DEFAULT_NEWSDB_DIR; 
		new File(pathToWrite).mkdirs();
	}

	public File write() throws IOException {
		DateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String formattedDate = format.format(itsNews.insertionDate().getTime());	
		String fileName = formattedDate + "_" + itsNews.getUserGroup() + ".txt";
		File file = new File(pathToWrite, fileName);
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
			out.println(itsNews.title());
			out.println(itsNews.description());
			out.println(format.format(itsNews.insertionDate().getTime()));
			out.println(format.format(itsNews.expirationDate().getTime()));
		} finally {
		    if (null != out) out.close();
		}
		return file;
	}
}
