
package it.xpug.aggregator;

import junit.framework.TestCase;

public class NewsValidatorTest extends TestCase {
	
	public void testInvalidExpirationDateFormat() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2007-04/--5\n";
		
		NewsValidator validator = new NewsValidator(newsAsString);
		
		assertFalse(validator.isValid());
		assertEquals(NewsValidator.ERROR_INVALID_EXPIRATION_DATE_FORMAT, validator.errorMessage());	
	}
	
	public void testInvalidRegistrationDateFormat() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006-04-14\n" + 
								"2007/04/25\n";
		
		NewsValidator validator = new NewsValidator(newsAsString);
		
		assertFalse(validator.isValid());
		assertEquals(NewsValidator.ERROR_INVALID_REGISTRATION_DATE_FORMAT, validator.errorMessage());	
	}
	
	public void testInvalidExpirationDateNews() {
		
		String newsAsString = "Titolo news\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2005/04/25\n";
				
		NewsValidator validator = new NewsValidator(newsAsString);
	
		assertFalse(validator.isValid());
		assertEquals(NewsValidator.ERROR_EXPIRATION_DATE_BEFORE_TODAY, validator.errorMessage());
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

	public void testInvalidTitleNews() {

		String newsAsString = "\n" + 
								"Descrizione news\n" +
								"2006/04/14\n" + 
								"2007/04/25\n";

		NewsValidator validator = new NewsValidator(newsAsString);

		assertFalse(validator.isValid());
		assertEquals(NewsValidator.ERROR_EMPTY_TITLE, validator.errorMessage());
	}

}
