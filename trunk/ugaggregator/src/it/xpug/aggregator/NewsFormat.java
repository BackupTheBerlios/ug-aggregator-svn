package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class NewsFormat {

	public String format(News news) throws ParseException {
	
		String insertionDate = formatDateForFile(news.insertionDate());
		String exirationDate = formatDateForFile(news.exirationDate());
		
		return news.title() + "\n" + news.description() + "\n" + insertionDate + "\n" + exirationDate;
	}
	
	protected String formatDateForFile(GregorianCalendar date){
		
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String formatDate = format.format(date.getTime());
		
		//String formatDate = new Integer(date.YEAR).toString()+ "/" + new Integer(date.MONTH).toString()+ "/" + new Integer(date.DAY_OF_MONTH).toString();
		return formatDate;
	}

}
