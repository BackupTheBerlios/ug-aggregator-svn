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
	private GregorianCalendar itsExpirationDate;
	private String itsTitle;
	private String itsDescription;

	public News(String news) {
		try {
			strings = news.split("\n");
			if (strings.length != 4)
				throw new InvalidNewsString();
			itsTitle = strings[0];
			itsDescription = strings[1];
			itsInsertionDate = parseDate(strings[2]);
			itsExpirationDate = parseDate(strings[3]);
			
		} catch (Exception e) {
			throw new InvalidParameterException(e.getMessage());
		}
	}

	public String title() {
		return itsTitle;
	}

	public String description() {
		return itsDescription;
	}

	public GregorianCalendar insertionDate() {
		return itsInsertionDate;
	}

	public GregorianCalendar expirationDate() {
		return itsExpirationDate;
	}

	private GregorianCalendar parseDate(String dateAsString) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parse = format.parse(dateAsString);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}
	

}
