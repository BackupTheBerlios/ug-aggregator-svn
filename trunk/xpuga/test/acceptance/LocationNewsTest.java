



public class LocationNewsTest extends XpugaTestCase {

	public LocationNewsTest(String name) {
		super(name);
	}

	/*
	 * Per testare manualmente il settaggio della locazione: 
	 * wget --post-data 'location=/tmp/picio/pacio' http://localhost:8080/xpuga/news/location
	 */
	public void testSetAbsoluteLocation() throws Exception {
		String tempDir = getSystemTempDir();
		final String path = tempDir + "/fixtures/pippo/pluto";
		postNewsLocation(path);
		assertNewsLocation("modified news location", path);
		
		assertEquals(path, get("/news/location"));
		assertEquals(path, get("/news/location/path"));
		
		postNewsLocation(DEFAULT_LOCATION);
		assertNewsLocation("default news location", DEFAULT_LOCATION);
	}

	private String get(String url) throws Exception {
		DoGet get = new DoGet(urlFor(url)) {
			public void process() throws Exception {
				assertEquals(200, status);
			}
		};
		get.execute();
		return get.content();
	}

	public void testSetRelativeLocation() throws Exception {
		postNewsLocation("fixtures/news/oneNews");
		assertNewsLocation("modified news location", "fixtures/news/oneNews");
		
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
