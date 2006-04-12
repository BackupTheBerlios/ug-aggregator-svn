package it.xpug.aggregator;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class News {

	private String[] strings;
	private GregorianCalendar itsInsertionDate;

	public News(String news) {
		try {
			strings = news.split("\n");
			itsInsertionDate = parseDate(strings[2]);
			
		} catch (Exception e) {
			new InvalidParameterException(e.getMessage());
		}
	}

	public String title() {
		return strings[0];
	}

	public String description() {
		return strings[1];
	}

	public GregorianCalendar insertionDate() throws ParseException {
		return parseDate(strings[2]);
	}

	public GregorianCalendar exirationDate() throws ParseException {
		return parseDate(strings[3]);
	}

	private GregorianCalendar parseDate(String dateAsString) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parse = format.parse(dateAsString);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}
	

}
