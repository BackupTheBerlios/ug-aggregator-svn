
package it.xpug.aggregator;

import junit.framework.TestCase;

public class NewsValidatorTest extends TestCase {
	
	public void testInvalidExpirationDateNews() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2005/04/25\n";
				
		NewsValidator validator = new NewsValidator(newsAsString);
	
		assertFalse(validator.isValid());
		assertEquals("Data scadenza antecedente data odierna", validator.errorMessage());
	}
	
	public void testValidExpirationDateNews() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2007/04/25\n";
				
		NewsValidator validator = new NewsValidator(newsAsString);
	
		assertTrue(validator.isValid());
		assertEquals("", validator.errorMessage());
	}

//	public void testInvalidTitleNews() {
//
//		String newsAsString = "\n" + 
//								"Descrizione news\n" +
//								"2006/04/14\n" + 
//								"2007/04/25\n";
//
//		NewsValidator validator = new NewsValidator(newsAsString);
//
//		assertFalse(validator.isValid());
//		assertEquals("Titolo Obbligatorio", validator.errorMessage());
//	}
	
/*
	public void testValidExpirationDateNews() {
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertTrue(NewsValidator.validate(new News(newsAsString)));
	}
	
	public void testInvalidStringNews() {

		String newsAsString = "Ti\ttolo\n\n\nnews\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertFalse(NewsValidator.validateNewsString(newsAsString));
	}
	
	public void testValidStringNews() {

		String newsAsString = "Ti\ttolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2006/04/25\n";

		assertTrue(NewsValidator.validateNewsString(newsAsString));
	}
	*/

}
