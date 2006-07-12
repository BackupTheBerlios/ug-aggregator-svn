package it.xpug.aggregator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewsFileWriter {

	private News itsNews;
	private final String pathToWrite = System.getProperty("it.xpug.aggregator.newsDir");

	public NewsFileWriter(News news) {
		itsNews = news;
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
		    out.close();
		}
		return file;
	}
}
