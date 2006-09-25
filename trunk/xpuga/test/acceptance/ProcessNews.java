
import java.util.*;
import java.text.*;

import org.w3c.dom.*; 

import junit.framework.*;

public class ProcessNews extends HttpTestCase {

	public ProcessNews(String name) {
		super(name);
	}

	static class Implementation extends ProcessDocument {

		public Implementation() throws Exception {
			super(urlFor("/news"));
		}

		protected void assertNewsOnPage(final Map news) throws Exception {
			assertTrue(evalAsBoolean(document, newsAttributeContains("title", (String)news.get("title"))));
			assertTrue(evalAsBoolean(document, newsAttributeContains("body", (String)news.get("body"))));
			assertEquals((String)news.get("insertion-date"), evalAsString(document, newsAttribute("insertion-date")));
			assertEquals((String)news.get("expiration-date"), evalAsString(document, newsAttribute("expiration-date")));
			assertEquals((String)news.get("user-group"), evalAsString(document, newsAttribute("user-group")));

		}

		private String newsAttribute(String name) {
			return "//*[@class='" + name + "']/@title";
		}

		private String newsAttributeContains(String name, String value) {
			return "contains(//*[@class='" + name + "'], '" + value + "')";
		}
	}

}
