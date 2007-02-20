
import org.w3c.dom.Document;

public class ViewNewsTest extends XpugaTestCase {

	public ViewNewsTest(String name) {
		super(name);
	}

	public void testNoNews() throws Exception {
		setLocation("fixtures/news/noNews");
		assertNumberOfNewsOnPage(0);
	}

	public void testOneNews() throws Exception {
		setLocation("fixtures/news/oneNews");
		assertNumberOfNewsOnPage(1);
	}

	public void testFewNews() throws Exception {
		setLocation("fixtures/news/fewNews");
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
