package it.xpug.aggregator.acceptance.util;

import it.xpug.aggregator.News;
import it.xpug.aggregator.NewsFileWriter;
import it.xpug.aggregator.XpugServlet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class AcceptanceTestCase extends TestCase {
	private ServletRunner runner;
	protected String currentPage = "";
	protected SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");	
	
	
	protected void setUp() throws Exception {
		runner = new ServletRunner();
		runner.registerServlet("news", XpugServlet.class.getName());
	}

	protected void insertNews(String title, String body, GregorianCalendar insertionDate, GregorianCalendar expirationDate) throws Exception {
		News news = new News(title, body, insertionDate, expirationDate, "XPUGMI");
		NewsFileWriter writer = new NewsFileWriter(news);
		writer.write();
		
		/*
		currentPage += "" +
		"<div class='news'>" +
		"  <p class='title'>" + title + "</p>" +
		"  <p class='insertion-date'>" + dateFormatter.format(insertionDate.getTime()) +"</p>" +
		"  <div class='body'>" + body + "</div>" +
		"</div>" ;
		*/
	}

	protected GregorianCalendar insertedToday() {
		return new GregorianCalendar();
	}

	protected GregorianCalendar insertedYesterday() {
		return yesterday();
	}

	protected GregorianCalendar expiresYesterday() {
		return yesterday();
	}

	private GregorianCalendar yesterday() {
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		return yesterday;
	}
	
	protected int numberOfNewsInPublicPage() throws Exception {
		return new HtmlChecker(getPageContents()).getNumberOfElementsWithClass("news");
	}

	protected String getPageContents() throws Exception {
	    ServletUnitClient client = runner.newClient();
	    WebRequest request = new GetMethodWebRequest("http://it.xpug.aggregator/news");
	    WebResponse response = client.getResponse(request);
	    return response.getText();
	}

	protected GregorianCalendar expiresTomorrow() {
		GregorianCalendar tomorrow = new GregorianCalendar();
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
