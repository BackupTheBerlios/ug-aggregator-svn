

import it.xpug.xpuga.NewsPiece;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import org.w3c.dom.Document;

// TODO: rename to XpugaTestCase
public class XpugaTestCase extends TestCase {

	protected static final String DEFAULT_LOCATION = "data/news";
	protected static final String DEFAULT_URL = "http://localhost:8080/xpuga";

	public static final int HTTP_OK = 200;
	
	public XpugaTestCase() {
		super();
	}

	public XpugaTestCase(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "fatal");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "fatal");
	}

	public void tearDown() throws Exception {
		resetLocation();
	}

	protected void assertNumberOfNewsOnPage(final int expected) throws Exception {
		new ProcessDocument(urlFor("/news")) {
			public void all(Document document) throws Exception {
				assertEquals(expected, evalAsInteger(document, "count(//*[@class='news'])"));
			}
		}.execute();
	}

	// TODO: extract setResource(name, value)
	protected void setLocation(final String location) throws Exception {
		new DoPost(urlFor("/news/location")) {
			public void prepare() throws Exception {
				addParameter("location", location);
			}
		}.execute();
	}

	protected void resetLocation() throws Exception {
		setLocation(DEFAULT_LOCATION);
	}

	protected static String urlFor(String relativePath) {
		String url = System.getProperty("xpuga.url");
		if (null == url) url = DEFAULT_URL;
		return url + relativePath;
	}
	
	public void testTruth() throws Exception {		
	}

	protected String getSystemTempDir() throws IOException {
		return File.createTempFile("aaa", "bbb").getParent();
	}

	protected File createUniqueTempDir() throws IOException {
		File directory = File.createTempFile("xpuga", "");
		if (!directory.delete())
			throw new IOException();
		if (!directory.mkdir())
			throw new IOException();
		return directory;
	}
	
	protected DoGet executeDoGet(String uri) throws Exception {
		DoGet doGet = new DoGet(urlFor(uri));

		doGet.execute();
		return doGet;
	}
	
	protected DoPost executeDoPost(String uri, Map parameters) throws Exception {
		
		DoPost doPost = new DoPost(urlFor(uri), parameters);
		
		doPost.execute();
		
		return doPost;
		
	}

	protected InsertNews.PostInsertNews currentPostInsertNews = null;
	
	protected void postInsertNews(Map news) throws Exception {
		
		InsertNews.PostInsertNews postInsertNews = new InsertNews.PostInsertNews(news);
		postInsertNews.execute();

		this.currentPostInsertNews = postInsertNews;
		
	}
	
	protected void assertPostStatus(int status) {		
		assertEquals(status, currentPostInsertNews.getStatus());
	}

	protected void assertInvalidField(String field) throws Exception {
		assertInvalidFields(new String[] {field});
	}
	
	protected void assertInvalidFields(String[] fields) throws Exception {
		currentPostInsertNews.assertInvalidFields(fields);
	}

	protected void assertNewsCreatedByPost() {
		currentPostInsertNews.assertNewsCreated();
	}
	
	protected void assertValidFieldsNotSignaledAsWrong(Map news, String[] emptyFields) throws Exception {
		currentPostInsertNews.assertValidFieldsNotSignaledAsWrong(news, emptyFields);
	}
}
