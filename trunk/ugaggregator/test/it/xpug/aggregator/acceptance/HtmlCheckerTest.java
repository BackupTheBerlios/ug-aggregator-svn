package it.xpug.aggregator.acceptance;

import it.xpug.aggregator.acceptance.util.HtmlChecker;
import junit.framework.TestCase;

public class HtmlCheckerTest extends TestCase {
	
	static final String TEST_HTML = "" +
	"<html>" +
	"<body>" +
	"<div class='news'>" +
	"  <p class='title'>Titolo</p>" +
	"  <div class='description'>Descrizione</div>" +
	"</div>" +
	"</body>" +
	"</html>";
	
	public void testCheck() throws Exception {
		HtmlChecker checker = new HtmlChecker(TEST_HTML);
		assertTrue(checker.existsClass("title"));
	}
}
