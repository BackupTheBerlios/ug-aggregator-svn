package it.xpug.aggregator.acceptance;

import it.xpug.aggregator.acceptance.util.AcceptanceTestCase;

public class NewsPageAcceptanceTest extends AcceptanceTestCase {
	public void testIfInseritaThenAppare() throws Exception { 
		insertNews("Titolo", "Testo testo testo", insertedToday(), expiresTomorrow()); 
		assertEquals(1, numberOfNewsInPublicPage()); 
		assertNewsPresent("Titolo", "Testo testo testo", insertedToday());
	}
	
	public void testIfSortOfTwoNews() throws Exception { 
		insertNews("Titolo1", "Testo testo testo1", insertedYesterday(), expiresTomorrow()); 
		insertNews("Titolo2", "Testo testo testo2", insertedToday(), expiresTomorrow()); 
		assertEquals(2, numberOfNewsInPublicPage()); 
		assertNewsPresent("Titolo1", "Testo testo testo1", insertedYesterday());
		assertNewsPresent("Titolo2", "Testo testo testo2", insertedToday());
		assertNewsComesBefore("Titolo1", "Titolo2");
	}
}
