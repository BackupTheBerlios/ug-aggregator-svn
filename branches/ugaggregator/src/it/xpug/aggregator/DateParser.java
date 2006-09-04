package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateParser {

	public static final String NEWS_REGISTRATION_DATE_FORMAT = "yyyy/MM/dd HHmmss";
	public static final String NEWS_EXPIRATION_DATE_FORMAT = "yyyy/MM/dd";

	public static GregorianCalendar parseRegistration(String dateStringToParse) throws ParseException {
		return parse(dateStringToParse, NEWS_REGISTRATION_DATE_FORMAT);
	}
	
	public static GregorianCalendar parseExpiration(String dateStringToParse) throws ParseException {
		return parse(dateStringToParse, NEWS_EXPIRATION_DATE_FORMAT);
	}
	
	private static GregorianCalendar parse(String dateStringToParse, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		Date parse = format.parse(dateStringToParse);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}
	
	
}
