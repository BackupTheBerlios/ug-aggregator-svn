package it.xpug.aggregator;

import java.util.Iterator;

import junit.framework.TestCase;

public class FakeNewsListTest extends TestCase {

	public void testFakeNewsList() throws Exception {
		NewsCollection newsCollection = new NewsCollection();
		String newsAsString = "Titolo news\n" + "Descrizione news\n"
				+ "2006/04/14\n" + "2006/04/25\n";
		News news1 = new News(newsAsString, "milano-xpug");
		newsCollection.addNews(news1);
		FakeNewsList.add(newsCollection);
		// FakeNewsList.setTitle("titolo");
		Iterator iterator = FakeNewsList.getIterator();
		News news = (News) iterator.next();
		assertEquals("Titolo news", news.title());
		assertEquals("Descrizione news", news.description());
	}

	public void testFakeNewsMultipleList() throws Exception {
		NewsCollection newsCollection = new NewsCollection();
		News news1 = new News("Titolo news\n" + "Descrizione news\n"
				+ "2006/04/14\n" + "2006/04/25\n", "milano-xpug");
		newsCollection.addNews(news1);
		News news2 = new News("Titolo news2\n" + "Descrizione news2\n"
				+ "2006/04/14\n" + "2006/04/25\n", "milano-xpug");
		newsCollection.addNews(news2);
		FakeNewsList.add(newsCollection);
		// FakeNewsList.setTitle("titolo");
		Iterator iterator = FakeNewsList.getIterator();
		News testNews = null;
		;
		while (iterator.hasNext()) {
			testNews = (News) iterator.next();
		}
		assertEquals("Titolo news2", testNews.title());
		assertEquals("Descrizione news2", testNews.description());
	}

}
