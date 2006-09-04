package it.xpug.aggregator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class DateParserTest extends TestCase {

	public DateParserTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testParseRegistrationDate() {
		GregorianCalendar expected = new GregorianCalendar();
		expected.set(Calendar.DAY_OF_MONTH, 23);
		expected.set(Calendar.MONTH, Calendar.DECEMBER);
		expected.set(Calendar.YEAR, 2006);
		expected.set(Calendar.HOUR_OF_DAY, 21);
		expected.set(Calendar.MINUTE, 45);
		expected.set(Calendar.SECOND, 39);
		try {
			GregorianCalendar actual = DateParser.parseRegistration("2006/12/23 214539");
			assertEquals(expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));
			assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
			assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
			assertEquals(expected.get(Calendar.HOUR_OF_DAY), actual.get(Calendar.HOUR_OF_DAY));
			assertEquals(expected.get(Calendar.MINUTE), actual.get(Calendar.MINUTE));
			assertEquals(expected.get(Calendar.SECOND), actual.get(Calendar.SECOND));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	public void testParseExpirationDate() {
		GregorianCalendar expected = new GregorianCalendar();
		expected.set(Calendar.DAY_OF_MONTH, 23);
		expected.set(Calendar.MONTH, Calendar.DECEMBER);
		expected.set(Calendar.YEAR, 2006);
		expected.set(Calendar.HOUR_OF_DAY, 0);
		expected.set(Calendar.MINUTE, 0);
		expected.set(Calendar.SECOND, 0);
		try {
			GregorianCalendar actual = DateParser.parseExpiration("2006/12/23");
			assertEquals(expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));
			assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
			assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
			assertEquals(expected.get(Calendar.HOUR_OF_DAY), actual.get(Calendar.HOUR_OF_DAY));
			assertEquals(expected.get(Calendar.MINUTE), actual.get(Calendar.MINUTE));
			assertEquals(expected.get(Calendar.SECOND), actual.get(Calendar.SECOND));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	public void testParseExpirationDateWithException() {
		try {
			DateParser.parseExpiration("fergrwjhteyj");
			fail("Non deve sollevare eccezione!");
		} catch (ParseException e) {
			//Se solleva eccezione il test è OK

		}
	}
	
	public void testParseRegistrationDateWithException() {
		try {
			DateParser.parseRegistration("fergrwjhteyj");
			fail("Non deve sollevare eccezione!");
		} catch (ParseException e) {
			//Se solleva eccezione il test è OK

		}
	}

}
