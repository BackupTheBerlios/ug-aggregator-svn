package it.xpug.aggregator;

import it.xpug.aggregator.util.CalendarDateUtil;

import java.security.InvalidParameterException;
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
			itsInsertionDate = CalendarDateUtil.parseYYYYMMDD_HHMMSS(strings[2]);
			itsExpirationDate = CalendarDateUtil.parseYYYYMMDD(strings[3]);
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

	public String getUserGroup() {
		return userGroup;
	}
}
