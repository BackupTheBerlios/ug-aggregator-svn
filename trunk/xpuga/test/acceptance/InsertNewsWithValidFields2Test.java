import java.io.File;
import java.util.Map;

public class InsertNewsWithValidFields2Test extends XpugaTestCase {

	private File newsLocation;

	public void setUp() throws Exception {
		super.setUp();
		newsLocation = createUniqueTempDir();
		setLocation(newsLocation.getAbsolutePath());
	}
	
	public void tearDown() throws Exception {
		newsLocation.delete();
	}

	public void testInsertInvalidExpirationDate() throws Exception {
		Map news = InsertNews.validNews();
		news.put("expiration-date", "2007-07-04 210000"); 
				
		/*		
		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				System.out.println(content());
				assertEquals(422, status);
				assertInvalidFields(new String[] { "expiration-date" });
			}
		}.execute();
		*/

		postInsertNews(news);
		
		assertPostStatus(422);
		assertInvalidField("expiration-date");

	}

	public void testInsertionWithoutHtmlTags() throws Exception {
		Map news = InsertNews.validNews();
		news.put("body", "<b>Il grassetto e' bello, ma inadatto al testo di una news</b>");

		/*		
		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "body" });
			}
		}.execute();
		*/
		
		postInsertNews(news);
		
		assertPostStatus(422);
		assertInvalidField("body");
	}

	public void testInsertionWithLowerThenSymbol() throws Exception {
		Map news = InsertNews.validNews();
		news.put("body", "Dire 3<2 ? scorretto, ma e'una news accettabile");
				
		/*		
		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(201, status);
			}
		}.execute();
		*/
		
		postInsertNews(news);
		
		assertPostStatus(201);
		
	}
	
	public void testInsertionWithoutHtmlTagsInTitle() throws Exception {
		
		Map news = InsertNews.validNews();
		news.put("title", "<b>Il grassetto ? bello, ma inadatto al testo di una news</b>");

		/*		
		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "title" });
			}
		}.execute();
		*/
		
		postInsertNews(news);	
		
		assertPostStatus(422);
		assertInvalidField("title");

	}

	public void testInsertionWithLowerThenSymbolInTitle() throws Exception {
		Map news = InsertNews.validNews();
		news.put("title", "Dire 3<2 ? scorretto, ma ? una news accettabile");
				
		/*		
		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(201, status);
			}
		}.execute();
		*/
		
		postInsertNews(news);
		
		assertPostStatus(201);

	}	
}
