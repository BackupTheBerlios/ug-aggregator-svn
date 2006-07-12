package it.xpug.aggregator.test;

import it.xpug.aggregator.util.CalendarDateUtil;

import java.text.ParseException;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class CalendarDateUtilTest extends TestCase {
	
	public void testYYYYMMDD() throws Exception {
		assertEquals(new GregorianCalendar(2004, 3, 15), CalendarDateUtil.parseYYYYMMDD("2004/04/15"));
	}

	public void testYYYYMMDDThrowsParseExceptionWithWrongString() throws Exception {
		try {
			CalendarDateUtil.parseYYYYMMDD("wrong");
			fail("Should throw an exception");
		} catch (ParseException ex){}
	}

	public void testYYYYMMDD_HHMMSS() throws Exception {
		assertEquals(new GregorianCalendar(2004, 3, 15, 12, 24, 36), CalendarDateUtil.parseYYYYMMDD_HHMMSS("2004/04/15 122436"));
	}

	public void testYYYYMMDD_HHMMSSThrowsParseExceptionWithWrongString() throws Exception {
		try {
			CalendarDateUtil.parseYYYYMMDD_HHMMSS("wrong");
			fail("Should throw an exception");
		} catch (ParseException ex){}
	}
	
}
