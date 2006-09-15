

public class LocationNewsTest extends HttpTestCase {

	public LocationNewsTest(String name) {
		super(name);
	}

	public void testDefaultLocation() throws Exception {
		assertNewsLocation("default news location", DEFAULT_LOCATION);
	}

	public void testSetLocation() throws Exception {
		postNewsLocation("/fixtures/news/oneNews");
		assertNewsLocation("modified news location", "/fixtures/news/oneNews");
		
		postNewsLocation(DEFAULT_LOCATION);
		assertNewsLocation("default news location", DEFAULT_LOCATION);
	}

	private void assertNewsLocation(final String message, final String expected) throws Exception {
		new DoGet(urlFor("/news/location")) {
			public void process() throws Exception {
				assertEquals(200, status);
				assertEquals(message, expected, content);
			}
		}.execute();
	}

	private void postNewsLocation(final String location) throws Exception {
		new DoPost(urlFor("/news/location")) {
			public void prepare() throws Exception {
				parameter("location", location);
			}
		}.execute();
	}

}
