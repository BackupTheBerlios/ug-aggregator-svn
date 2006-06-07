package it.xpug.aggregator;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewsFileWriter {

	private News itsNews;
	private static int counter = 0;

	public NewsFileWriter(News news) {
		itsNews = news;
	}

	public File write() {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String formattedDate = format.format(itsNews.insertionDate().getTime());	
		String fileName = formattedDate + "_" + "XUPMI" + "_" + ++counter  + ".txt";
		return new File("/testdata", fileName);
	}

}
