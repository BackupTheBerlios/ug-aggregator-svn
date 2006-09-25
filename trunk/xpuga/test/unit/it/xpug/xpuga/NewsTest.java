package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

public class NewsTest extends TestCase {

	public void testConstructor() throws Exception {
		NewsPiece news = new NewsPiece();
		news.load("test/fixtures/news/oneNews/20060902173559");
		assertEquals("Test News Title", news.getTitle());
		assertEquals("Test News Description", news.getBody());
		assertEquals(XDate.create(2006, 9, 2, 17, 35, 59), news.getInsertionDate());
		assertEquals(XDate.create(2006, 10, 2, 17, 35, 59), news.getExpirationDate());
		assertEquals("milano-xpug", news.getGroupName());
	}
	
	public void testXDate() throws Exception {
		assertEquals(XDate.create(2006, 9, 2, 17, 35, 59), XDate.create("20060902173559"));
	}
	
	public void testFindNews() throws Exception {
		NewsManager nm = new NewsManager("test/fixtures/news/oneNews");
		List l = nm.getNewsList();
		assertEquals("ho avuto" + l, 1, l.size());
		NewsPiece news = (NewsPiece) l.get(0);
		assertEquals("Test News Title", news.getTitle());

		nm = new NewsManager("test/fixtures/news/fewNews");
		l = nm.getNewsList();
		assertEquals("ho avuto" + l, 4, l.size());
	}
	
	public void testDateHelpers() throws IOException, ParseException {
		NewsPiece news = new NewsPiece();
		news.load("test/fixtures/news/oneNews/20060902173559");
		assertEquals("20060902173559", news.getInsertionDateAsCode());
	}
	
	public void testOrder() throws Exception {
		NewsManager nm = new NewsManager("test/fixtures/news/fewNews");
		List news = nm.getNewsList();
		NewsPiece n0 = (NewsPiece) news.get(0);
		NewsPiece n3 = (NewsPiece) news.get(3);
		assertEquals("20060903120002", n0.getInsertionDateAsCode());
		assertEquals("20060902173559", n3.getInsertionDateAsCode());
	}
	
	public void testInsertNews() throws Exception {
		String fileName="20060920200104";
		NewsPiece news = new NewsPiece();
		news.setTitle("nuova news");
		news.setBody("abbiamo una nuova news");
		news.setGroupName("milano-xpug");
		news.setInsertionDate("20060920200104");
		news.setExpirationDate("20070920200104");
		news.save(fileName);
		File file=new File(fileName);
		assertTrue("il file non è stato creato",file.exists());
		NewsPiece readNews=new NewsPiece();
		readNews.load(fileName);
		assertEquals("nuova news",readNews.getTitle());
		assertEquals("abbiamo una nuova news",readNews.getBody());
		assertEquals("20060920200104",readNews.getInsertionDateAsCode());
		assertEquals("20070920200104",readNews.getExpirationDateAsCode());
		assertEquals("milano-xpug",readNews.getGroupName());
		
		file.delete();
	}
	
	public void testNewsValidation() throws Exception {
		NewsPiece news=new NewsPiece();
		assertFalse(news.isValid());
		
	}
}
