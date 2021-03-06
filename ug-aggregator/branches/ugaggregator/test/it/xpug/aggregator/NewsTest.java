package it.xpug.aggregator;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class NewsTest extends TestCase {

	public void testSimple() throws Exception {
		String newsAsString = "Titolo news\n" + 
			"Descrizione news\n" +
			"2006/04/14 234412\n" + 
			"2006/04/25\n";
		News news = NewsBuilder.withAllFields(newsAsString);
		
		assertEquals("Titolo news", news.title());
		assertEquals("Descrizione news", news.description());
		assertEquals(new GregorianCalendar(2006, GregorianCalendar.APRIL, 14, 23, 44, 12), news.insertionDate());
		assertEquals(new GregorianCalendar(2006, GregorianCalendar.APRIL, 25), news.expirationDate());
	}
	
}
