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
	private String userGroup;
	
	public News(String title, String description, GregorianCalendar insertionDate, GregorianCalendar expirationDate, 
			String userGroup) {
		this.itsTitle = title;
		this.itsDescription = description;
		this.itsInsertionDate = insertionDate;
		this.itsExpirationDate = expirationDate;
		this.userGroup = userGroup;
	}
	
	public News(String news, String userGroup) {
		try {
			strings = news.split("\n");
			if (strings.length != 4)
				throw new InvalidNewsString();
			itsTitle = strings[0];
			itsDescription = strings[1];
			itsInsertionDate = parseDate(strings[2], "yyyy/MM/dd HHmmss");
			itsExpirationDate = parseDate(strings[3], "yyyy/MM/dd");
			this.userGroup = userGroup;
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

	private GregorianCalendar parseDate(String dateAsString, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		Date parse = format.parse(dateAsString);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}

	public String getUserGroup() {
		return userGroup;
	}
	

}
