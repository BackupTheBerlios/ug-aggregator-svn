

public class NewsListAsIndexPageTest extends XpugaTestCase {

	public NewsListAsIndexPageTest(String name) {
		super(name);
	}

	public void testRedirectToNewsList() throws Exception {
		DoGet get = executeDoGet("/");
		assertTrue("get from / should be redirect to /news", get.path().endsWith("/news"));
	}

}
