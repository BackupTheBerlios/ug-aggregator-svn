package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class News {

	private String[] strings;

	public News(String news) {
		strings = news.split("\n");
		
	}

	public String title() {
		return strings[0];
	}

	public String description() {
		return strings[1];
	}

	public GregorianCalendar insertionDate() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		
		Date parse = format.parse(strings[2]);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}

}
