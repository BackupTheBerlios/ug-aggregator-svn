

import junit.framework.TestCase;

import org.w3c.dom.Document;

// TODO: rename to XpugaTestCase
public class HttpTestCase extends TestCase {

	protected static final String DEFAULT_LOCATION = "/data/news";
	private static final String DEFAULT_URL = "http://localhost:8080/xpuga";

	public HttpTestCase(String name) {
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
				parameter("location", location);
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

}
