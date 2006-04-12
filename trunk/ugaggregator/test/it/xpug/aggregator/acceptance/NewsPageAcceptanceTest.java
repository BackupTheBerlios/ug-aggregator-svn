package it.xpug.aggregator.acceptance;

import junit.framework.TestCase;

public class NewsPageAcceptanceTest extends TestCase {
	
	
	
	private void insertNewsWithTitleAndDescription(String string, String string2) {
		
	}

	private void assertNewsWithTitleAndDescription(String string, String string2) {
		
	}

	public void testInsertedNewsAreShownOnThePage() throws Exception {
		insertNewsWithTitleAndDescription("Titolo", "Descrizione");
		assertNewsWithTitleAndDescription("Titolo", "Descrizione");
	}
}
