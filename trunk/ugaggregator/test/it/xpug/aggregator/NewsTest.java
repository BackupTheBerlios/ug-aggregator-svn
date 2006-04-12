package it.xpug.aggregator;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class NewsTest extends TestCase {

	public void testSimple() throws Exception {
		String newsAsString = "Titolo news\n" + 
			"Descrizione news\n" +
			"2006/04/14\n" + 
			"2006/04/25\n";
		News news = new News(newsAsString);
		
		assertNotNull(news);
		assertEquals("Titolo news", news.title());
		assertEquals("Descrizione news", news.description());
		assertEquals(new GregorianCalendar(2006, GregorianCalendar.APRIL, 14), news.insertionDate());
	}
}
