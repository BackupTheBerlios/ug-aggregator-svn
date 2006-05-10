
package it.xpug.aggregator;

import junit.framework.TestCase;

public class NewsValidatorTest extends TestCase {
	
	public void testInvaldExpirationDateNews() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2005/04/25\n";
		
		News news = new News(newsAsString);
		
	
		assertFalse(NewsValidator.validate(news));
	}
	
	public void testInvalidTitleNews() {

		String newsAsString = "\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertFalse(NewsValidator.validate(new News(newsAsString)));
	}
	
	public void testValidExpirationDateNews() {
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertTrue(NewsValidator.validate(new News(newsAsString)));
	}
	
	public void testNormalizeNews() {

		String newsAsString = "Ti\ttolo\n\n\nnews\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertFalse(NewsValidator.validateNewsString(newsAsString));
	}
}
