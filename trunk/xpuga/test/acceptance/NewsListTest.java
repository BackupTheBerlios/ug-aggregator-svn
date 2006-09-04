import org.w3c.dom.*;

public class NewsListTest extends HttpTestCase {

	public NewsListTest(String name) {
		super(name);
	}

	public void tearDown() throws Exception {
		resetLocation();
	}

	public void testNoNewsWithClosure() throws Exception {
		setLocation("/fixtures/news/noNews");
		new ProcessDocument("http://localhost:8080/xpuga/news") {
			public void all(Document document) throws Exception {
				assertEquals(0, evalAsInteger(document, countNews()));

			}
		}.execute();
	}

	public void testOneNewsWithClosure() throws Exception {
		setLocation("/fixtures/news/oneNews");
		new ProcessDocument("http://localhost:8080/xpuga/news") {
			public void all(Document document) throws Exception {
				assertEquals(1, evalAsInteger(document, countNews()));

			}
		}.execute();
	}

	public void testFewNewswithClosure() throws Exception {
		setLocation("/fixtures/news/fewNews");
		new ProcessDocument("http://localhost:8080/xpuga/news") {
			public void all(Document document) throws Exception {
				assertEquals(4, evalAsInteger(document, countNews()));
				assertEquals(2, evalAsInteger(document, countNewsFromGroup("milano-xpug")));
				assertEquals(1, evalAsInteger(document, countNewsFromGroup("cagliari-xpug")));
				assertEquals(1, evalAsInteger(document, countNewsFromGroup("roma-xpug")));
			}
		}.execute();
	}

	
	private String countNews() {
		return "count(//*[@class='news'])";
	}

	private String countNewsFromGroup(String group) {
		return "count(//*[@class='user-group'][@title='" + group + "'])";
	}

	private void setLocation(final String location) throws Exception {
		new DoPost("http://localhost:8080/xpuga/news/location") {
			public void prepare() throws Exception {
				parameter("location", location);
			}
		}.execute();
	}

	private void resetLocation() throws Exception {
		setLocation("/data/news");
	}

}
