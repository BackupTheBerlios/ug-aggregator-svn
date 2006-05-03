package it.xpug.aggregator;

import junit.framework.TestCase;

public class NewsFilterTest extends TestCase {
	
	private NewsCollection newsList;
	private NewsFilter newsFilter;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		newsList = new NewsCollection();
		newsFilter = new NewsFilter();
	}
	
	public void testFilterUnchanged() {

		News news = getNews("2010/04/25");
		
		newsList.addNews(news);
		
		NewsCollection filteredNewsList = newsFilter.filter(newsList);
		
		News filteredNews = (News)filteredNewsList.newsList.first();
		
		assertEquals(news.title(), filteredNews.title());
	}
	
	public void testFilterOneExpired() {

		newsList.addNews(getNews("2005/04/25"));
		
		assertEquals(0, newsFilter.filter(newsList).newsList.size());
	}
	
	private News getNews(String expirationDate) {
		
		String newsAsString = "Titolo news\n" + 
						"Descrizione news\n" +
						"2006/04/14\n" + 
						expirationDate +
						"\n";
		
		return new News(newsAsString);
	}

	
}

