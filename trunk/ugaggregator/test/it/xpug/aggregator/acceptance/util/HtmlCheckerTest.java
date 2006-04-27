package it.xpug.aggregator.acceptance.util;

import junit.framework.TestCase;

public class HtmlCheckerTest extends TestCase {
	
	static final String TEST_HTML = "" +
	"<html>" +
	"<body>" +
	"<div class='news' id='milano-20060412-0'>" +
	"  <p class='title'>Titolo</p>" +
	"  <p class='insertion-date'>12 aprile 2006</p>" +
	"  <div class='description'>Descrizione</div>" +
	"</div>" +
	"</body>" +
	"</html>";
	
	public void testCheck() throws Exception {
		HtmlChecker checker = new HtmlChecker(TEST_HTML);
		assertTrue(checker.existsElementWithClass("title"));
		assertFalse(checker.existsElementWithClass("piciopacio"));
	}
	
	public void testClassWithContent() throws Exception {
		HtmlChecker checker = new HtmlChecker(TEST_HTML);
		assertTrue(checker.existsElementWithClassAndContent("title", "Titolo"));
		assertFalse(checker.existsElementWithClassAndContent("title", "piciopacio"));
		assertFalse(checker.existsElementWithClassAndContent("title", "**[]'"));
	}
	
	public void testNumberOfElements() throws Exception {
		HtmlChecker checker = new HtmlChecker(TEST_HTML);
		assertEquals(1, checker.getNumberOfElementsWithClass("title"));
		assertEquals(0, checker.getNumberOfElementsWithClass("piciopacio"));
	}
}
