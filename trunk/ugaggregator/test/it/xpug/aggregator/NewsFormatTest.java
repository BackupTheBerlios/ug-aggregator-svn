package it.xpug.aggregator;

import java.security.InvalidParameterException;

import junit.framework.TestCase;

public class NewsFormatTest extends TestCase {
	
	public void testFormat() throws Exception {
		NewsFormat myNewsFormat = new NewsFormat();
		String newsAsString = "";
		
		try {
			News news = new News(newsAsString);
			fail("With empty string should throw an exception");
		} catch (InvalidParameterException e) {
		}
		
		// TODO: uncomment and implement this please! :D
		// assertEquals(myNewsFormat.format(news), newsAsString);		
	}
	
}
