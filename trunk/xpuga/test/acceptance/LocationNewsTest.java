import java.util.HashMap;
import java.util.Map;

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
		final String filePath = tempDir + "/fixtures/pippo/pluto";
		createNewsLocationByPost(filePath);
		assertNewsLocationCreated("modified news location", filePath);
		
		assertEquals("test relative and absolute path", filePath, doGetContentFromUrl("/news/location"));
		assertEquals("test absolute path", filePath, doGetContentFromUrl("/news/location/path"));
		
		createNewsLocationByPost(DEFAULT_LOCATION);
		assertNewsLocationCreated("default news location", DEFAULT_LOCATION);
		
	}

	private String doGetContentFromUrl(String url) throws Exception {
		return executeDoGet(url).getContent();
	}

	public void testSetRelativeLocation() throws Exception {
		createNewsLocationByPost("fixtures/news/oneNews");
		assertNewsLocationCreated("modified news location", "fixtures/news/oneNews");
		
		createNewsLocationByPost(DEFAULT_LOCATION);
		assertNewsLocationCreated("default news location", DEFAULT_LOCATION);
	}

	private void assertNewsLocationCreated(final String errorMessage, final String expected) throws Exception {
		DoGet doGet = executeDoGet("/news/location");
		
		assertEquals(HTTP_OK, doGet.getStatus());
		assertEquals(errorMessage, expected, doGet.getContent());
	}

	private void createNewsLocationByPost(final String location) throws Exception {

		/*
		new DoPost(urlFor("/news/location")) {
			public void prepare() throws Exception {
				addParameter("location", location);
			}
		}.execute();
		*/
		
		Map parameters = new HashMap();
		parameters.put("location", location);
		
		executeDoPost("/news/location", parameters);
		
	}
	

}
