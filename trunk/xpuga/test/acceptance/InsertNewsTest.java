

import java.io.File;
import java.util.Map;

import org.w3c.dom.Document;

public class InsertNewsTest extends XpugaTestCase {

	private String locationNewsCreated;
	private Map newsToInsert;
	private File newsLocation;

	public void setUp() throws Exception {
		super.setUp();
		newsLocation = createUniqueTempDir();
		setLocation(newsLocation.getAbsolutePath());
	}
	
	public void tearDown() throws Exception {
		newsLocation.delete();
	}
	
	public void testInsertOneNews() throws Exception {
				
		/*
		new InsertNews.PostNewsImplementation(newsToInsert) {
			public void process() throws Exception {
				assertNewsCreated();
				locationNewsCreated = header("Location");
			}
		}.execute();
		*/
		
		newsToInsert = InsertNews.validNews();
		
		postInsertNews(newsToInsert);
		assertNewsCreatedByPost();
				
		locationNewsCreated = currentPostInsertNews.header("Location");

		newsToInsert.put("insertion-date", extractNewsIdFromNewsLocation(locationNewsCreated));

		/*
		new ProcessNews.Implementation() {
			public void all(Document document) throws Exception {
				assertNumberOfNewsOnPage(1);
				assertNewsOnPage(newsToInsert);
			}
		}.execute();
		*/

		ProcessNews.Implementation processNews = new ProcessNews.Implementation();
		processNews.execute();
		
		assertNumberOfNewsOnPage(1);
		processNews.assertNewsOnPage(newsToInsert);

	}

	private String extractNewsIdFromNewsLocation(String newsLocation) {
		return locationNewsCreated.substring(locationNewsCreated.lastIndexOf('/')+1);
	}

}
