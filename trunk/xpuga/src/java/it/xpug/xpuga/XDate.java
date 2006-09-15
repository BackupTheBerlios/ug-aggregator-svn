package it.xpug.xpuga;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class XDate extends Date {
	
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final long serialVersionUID = 1972019358732929946L;

	public static Date create(int year, int month, int day, int hours, int minutes, int seconds) {
		return new GregorianCalendar(year, month-1, day, hours, minutes, seconds).getTime();
	}

	public static Date create(String s) throws ParseException {
		return fmt.parse(s);
	}

	public static String getCode(Date date) {
		return fmt.format(date);
	}

}
