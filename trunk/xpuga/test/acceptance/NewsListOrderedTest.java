import org.w3c.dom.*;

public class NewsListOrderedTest extends HttpTestCase {

	public NewsListOrderedTest(String name) {
		super(name);
	}

	public void testFewNewsOrdered() throws Exception {
		setLocation("/fixtures/news/fewNews");
		
		new ProcessDocument("http://localhost:8080/xpuga/news") {
			private String previousInsertionDate = "99999999999999";
			private String currentInsertionDate = "00000000000000";

			public void all(Document document) throws Exception {
				select("//*[@class='news']");
			}
			public void each(Node current) throws Exception {
				currentInsertionDate = evalAsString(current, ".//*[@class='insertion-date']/@title");
				assertTrue(previousInsertionDate + " > " + currentInsertionDate,
						previousInsertionDate.compareTo(currentInsertionDate) > 0);
				previousInsertionDate = currentInsertionDate;
			}
		}.execute();
		
		resetLocation();
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
