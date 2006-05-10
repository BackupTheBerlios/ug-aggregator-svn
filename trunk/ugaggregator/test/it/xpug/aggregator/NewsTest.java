package it.xpug.aggregator;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class NewsTest extends TestCase {

	public void testSimple() throws Exception {
		String newsAsString = "Titolo news\n" + 
			"Descrizione news\n" +
			"2006/04/14\n" + 
			"2006/04/25\n";
		News news = new News(newsAsString);
		
		assertEquals("Titolo news", news.title());
		assertEquals("Descrizione news", news.description());
		assertEquals(new GregorianCalendar(2006, GregorianCalendar.APRIL, 14), news.insertionDate());
		assertEquals(new GregorianCalendar(2006, GregorianCalendar.APRIL, 25), news.expirationDate());
	}
	
	public void testEmpty(){
		try {
			News news = new News("");
			fail("With empty string should throw an exception");
		} catch (InvalidParameterException e) {
		}
	}
}
