package it.xpug.aggregator.acceptance;

import junit.framework.TestCase;

public class NewsPageAcceptanceTest extends TestCase {
	
	private void insertNews(String string, String string2) {
		
	}

	private void assertNewsPresent(String title, String description) {
//		assertTrue(checker.assertElementWithClassAndContent("title", title));
//		assertTrue(checker.assertElementWithClassAndContent("description", description));
	}

	public void testInsertedNewsAreShownOnThePage() throws Exception {
		insertNews("Titolo", "Descrizione");
		assertNewsPresent("Titolo", "Descrizione");
//		assertEquals(1, numberOfNewsShown());
	}
}
