
package it.xpug.aggregator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

public class NewsFilterTest extends TestCase {

	public void testFilterUnchanged() {
		
		NewsFilter newsFilter = new NewsFilter(new Date());

		List expectedNewsList = this.buildTestList();
		
		List filteredNews =  newsFilter.filter(expectedNewsList);
		
		assertEquals(expectedNewsList, filteredNews);	
	}
	
	private List buildTestList() {
		NewsMock newsUno = new NewsMock(new Date(2006, 3, 25));
		NewsMock newsDue = new NewsMock(new Date(2006, 3, 26));
		List testList = new ArrayList();
		testList.add(newsUno);
		testList.add(newsDue); 
		return testList;
	}

	private class NewsMock extends News {

		private static final String NEWS_AS_STRING = "Titolo news\n" + 
		"Descrizione news\n" +
		"2006/04/14\n" + 
		"2006/04/25\n";
		
		private String title = "";
		private String description = "";
		private Date expiring = null;
		
		public NewsMock(Date expiring) {
			super(NEWS_AS_STRING);
			this.expiring = expiring;
		}
		
		public Date expiringDate() {
			return this.expiring;
		}
	}
}

