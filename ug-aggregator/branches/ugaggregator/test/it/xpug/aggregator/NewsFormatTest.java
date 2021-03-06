package it.xpug.aggregator;

import java.security.InvalidParameterException;

import junit.framework.TestCase;

public class NewsFormatTest extends TestCase {

	public void testInvalidFormat() throws Exception {
		String newsAsString = "";

		News news = null;
		try {
			
			news = NewsBuilder.withAllFields(newsAsString);
			fail("With empty string should throw an exception");
		} catch (InvalidParameterException e) {
		}

		try {
			news = new News("pippo\npluto\npaperino", "XPUGMI");
			fail("Msgstring must have exactly 4 lines");
		} catch (InvalidParameterException e) {
		}

		try {
			news = new News("topolino\npaperina\npippo\npluto\npaperino", "XPUGMI");
			fail("Msgstring must have exactly 4 lines");
		} catch (InvalidParameterException e) {
		}

		assertNull(news);
	}

	public void testValidFormat() throws Exception {
		NewsFormat myNewsFormat = new NewsFormat();
		String newsAsString = "titolo\ndescrizione\n2006/01/01 234412\n2006/12/31";

		News news = NewsBuilder.withAllFields(newsAsString);
		String s = myNewsFormat.format(news);
		
		assertEquals(newsAsString, s);
	}

}
