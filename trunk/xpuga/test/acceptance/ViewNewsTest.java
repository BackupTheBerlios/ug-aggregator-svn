
import org.w3c.dom.*;

public class ViewNewsTest extends HttpTestCase {

	public ViewNewsTest(String name) {
		super(name);
	}

	public void testNoNewsWith() throws Exception {
		setLocation("/fixtures/news/noNews");
		assertNumberOfNewsOnPage(0);
	}

	public void testOneNewsWith() throws Exception {
		setLocation("/fixtures/news/oneNews");
		assertNumberOfNewsOnPage(1);
	}

	public void testFewNewswith() throws Exception {
		setLocation("/fixtures/news/fewNews");
		new ProcessNewsList() {
			public void all(Document document) throws Exception {
				assertNumberOfNewsOnPage(4);
				assertNumberOfNewsFromGroup(2, "milano-xpug");
				assertNumberOfNewsFromGroup(1, "cagliari-xpug");
				assertNumberOfNewsFromGroup(1, "roma-xpug");
			}
		}.execute();
	}

	// TODO: extract superclass ProcessNews
	class ProcessNewsList extends ProcessDocument {

		public ProcessNewsList() throws Exception {
			super(urlFor("/news"));
		}

		public void assertNumberOfNewsFromGroup(
				final int expected, final String group) throws Exception {
			assertEquals(expected, evalAsInteger(document,
						"count(//*[@class='user-group'][@title='" + group + "'])"));
		}

	}

}
