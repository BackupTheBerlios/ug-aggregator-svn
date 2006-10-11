import java.util.Map;

public class InsertNewsWithValidFileds2Test extends HttpTestCase {

	public InsertNewsWithValidFileds2Test(String name) {
		super(name);
	}

	public void testInsertInvalidExpirationDate() throws Exception {
		setLocation("/insert/newsWithInvalidExpirationDate");

		Map news = InsertNews.validNews();
		news.put("expiration-date", "2007-07-04 210000"); 

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "expiration-date" });
			}
		}.execute();
	}

	public void testInsertionWithoutHtmlTags() throws Exception {
		setLocation("/insert/newsWithHtmlTags");

		Map news = InsertNews.validNews();
		news.put("body", "<b>Il grassetto è bello, ma inadatto al testo di una news</b>");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "body" });
			}
		}.execute();
	}

	public void testInsertionWithLowerThenSymbol() throws Exception {
		setLocation("/insert/newsWithLowerThenSymbol");

		Map news = InsertNews.validNews();
		news.put("body", "Dire 3<2 è scorretto, ma  una news accettabile");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(201, status);
			}
		}.execute();
	}
	
	public void testInsertionWithoutHtmlTagsInTitle() throws Exception {
		setLocation("/insert/newsWithHtmlTags");

		Map news = InsertNews.validNews();
		news.put("title", "<b>Il grassetto è bello, ma inadatto al testo di una news</b>");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "title" });
			}
		}.execute();
	}

	public void testInsertionWithLowerThenSymbolInTitle() throws Exception {
		setLocation("/insert/newsWithLowerThenSymbolInTitle");

		Map news = InsertNews.validNews();
		news.put("title", "Dire 3<2 è scorretto, ma è una news accettabile");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(201, status);
			}
		}.execute();
	}
	
}
