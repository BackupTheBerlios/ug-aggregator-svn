package it.xpug.aggregator.acceptance.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

public class AcceptanceTestCase extends TestCase {
	protected String currentPage = "";
	protected SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");	
	
	protected void insertNews(String title, String body, Calendar insertionDate, Calendar expirationDate) {
		currentPage += "" +
		"<div class='news'>" +
		"  <p class='title'>" + title + "</p>" +
		"  <p class='insertion-date'>" + dateFormatter.format(insertionDate.getTime()) +"</p>" +
		"  <div class='body'>" + body + "</div>" +
		"</div>" ;
	}

	protected Calendar insertedToday() {
		return new GregorianCalendar();
	}

	protected Calendar insertedYesterday() {
		Calendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		return yesterday;
	}
	
	protected int numberOfNewsInPublicPage() throws Exception {
		return new HtmlChecker(getPageContents()).getNumberOfElementsWithClass("news");
	}

	protected String getPageContents() {
		return "<html><body>" + currentPage + "</body></html>";
	}

	protected Calendar expiresTomorrow() {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		return tomorrow;
	}

	protected void assertNewsPresent(String title, String body, Calendar insertionDate) throws Exception {
		HtmlChecker checker = new HtmlChecker(getPageContents());
		assertTrue(checker.existsElementWithClassAndContent("news", "title", title));
		assertTrue(checker.existsElementWithClassAndContent("news", "body", body));
	}
	
	protected void assertNewsComesBefore(String title0, String title1) throws Exception {
		HtmlChecker checker = new HtmlChecker(getPageContents());
		List elementContents = checker.getElementContents("title");
		assertEquals(title0, elementContents.get(0));
		assertEquals(title1, elementContents.get(1));
	}
	
	public void testTruth() {
		assertEquals(0, 0);
	}
}
