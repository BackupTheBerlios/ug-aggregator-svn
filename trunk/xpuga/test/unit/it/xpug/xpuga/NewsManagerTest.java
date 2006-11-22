package it.xpug.xpuga;

import java.util.List;

import junit.framework.TestCase;

public class NewsManagerTest extends TestCase {

	public NewsManagerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExpireNews() throws Exception {
		NewsManager nm = new NewsManager("test/fixtures/news/oneNews", XDate.create(2016, 10, 3, 0, 0, 0));
	    List newsList = nm.getNewsList();
	    assertEquals("ho avuto" + newsList, 0, newsList.size());
	}
	
	public void testBorderCaseExpire() throws Exception {
		NewsManager nm = new NewsManager("test/fixtures/news/oneNews", XDate.create(2016, 10, 2, 17, 35, 59));
	    assertEquals("ho avuto" + nm.getNewsList(), 1, nm.getNewsList().size());
		
	    nm = new NewsManager("test/fixtures/news/oneNews", XDate.create(2016, 10, 3, 17, 35, 59));
	    assertEquals("ho avuto" + nm.getNewsList(), 0, nm.getNewsList().size());
	}
	
	
	public void testFindNews() throws Exception {
	    NewsManager nm = new NewsManager("test/fixtures/news/oneNews", XDate.create(2006, 9, 2, 0, 0, 0));
	    List l = nm.getNewsList();
	    assertEquals("ho avuto" + l, 1, l.size());
	    NewsPiece news = (NewsPiece) l.get(0);
	    assertEquals("Test News Title", news.getTitle());

	    nm = new NewsManager("test/fixtures/news/fewNews", XDate.create(2006, 9, 2, 0, 0, 0));
	    l = nm.getNewsList();
	    assertEquals("ho avuto" + l, 4, l.size());
	 }
	
	public void testOrder() throws Exception {
	    NewsManager nm = new NewsManager("test/fixtures/news/fewNews", XDate.create(2006, 9, 2, 0, 0, 0));
	    List news = nm.getNewsList();
	    NewsPiece n0 = (NewsPiece) news.get(0);
	    NewsPiece n3 = (NewsPiece) news.get(3);
	    assertEquals("20060903120002", n0.getInsertionDateAsCode());
	    assertEquals("20060902173559", n3.getInsertionDateAsCode());
	  }

}
