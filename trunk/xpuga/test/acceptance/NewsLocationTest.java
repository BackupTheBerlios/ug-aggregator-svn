

public class NewsLocationTest extends HttpTestCase {

	public NewsLocationTest(String name) {
		super(name);
	}

	public void testDefaultLocation() throws Exception {
		assertNewsLocation("default news location", "/data/news");
	}

	public void testSetLocation() throws Exception {
		postNewsLocation("/fixtures/news/oneNews");
		assertNewsLocation("modified news location", "/fixtures/news/oneNews");
		
		postNewsLocation("/data/news");
		assertNewsLocation("default news location", "/data/news");
	}

	private void assertNewsLocation(final String message, final String expected) throws Exception {
		new DoGet("http://localhost:8080/xpuga/news/location") {
			public void process() throws Exception {
				assertEquals(200, status);
				assertEquals(message, expected, content);
			}
		}.execute();
	}

	private void postNewsLocation(final String location) throws Exception {
		new DoPost("http://localhost:8080/xpuga/news/location") {
			public void prepare() throws Exception {
				parameter("location", location);
			}
		}.execute();
	}

}
