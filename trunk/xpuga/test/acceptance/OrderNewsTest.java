import org.w3c.dom.*;

public class OrderNewsTest extends HttpTestCase {

	public OrderNewsTest(String name) {
		super(name);
	}
	
	public void testFewNewsOrdered() throws Exception {
		setLocation("/fixtures/news/fewNews");
		assertNumberOfNewsOnPage(4);

		new ProcessOrderedNews() {
			public void forEachNewsOnPage() throws Exception {
				assertPreviousNewsInsertedBeforeCurrentNews();
			}
		}.execute();
	}
	
	class ProcessOrderedNews extends ProcessDocument {

		protected String previousInsertionDate = "99999999999999";
		protected String currentInsertionDate = "00000000000000";

		public ProcessOrderedNews() throws Exception {
			super(urlFor("/news"));
		}


		public void all(Document document) throws Exception {
			select("//*[@class='news']");
		}

		public void each(Node current) throws Exception {
			currentInsertionDate = evalAsString(current, ".//*[@class='insertion-date']/@title");
			forEachNewsOnPage();
			previousInsertionDate = currentInsertionDate;
		}

		protected void forEachNewsOnPage() throws Exception { }

		protected void assertPreviousNewsInsertedBeforeCurrentNews() throws Exception {
			assertTrue(previousInsertionDate + " > " + currentInsertionDate,
					previousInsertionDate.compareTo(currentInsertionDate) > 0);
			
		}

	}

}
