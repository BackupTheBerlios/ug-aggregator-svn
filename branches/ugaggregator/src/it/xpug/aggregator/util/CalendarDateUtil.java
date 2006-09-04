package it.xpug.aggregator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDateUtil {

	public static GregorianCalendar parseDate(String dateAsString, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		Date parse = format.parse(dateAsString);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}

	public static GregorianCalendar parseYYYYMMDD(String string) throws ParseException {
		return parseDate(string, "yyyy/MM/dd");
	}

	public static GregorianCalendar parseYYYYMMDD_HHMMSS(String string) throws ParseException {
		return parseDate(string, "yyyy/MM/dd HHmmss");
	}

}
