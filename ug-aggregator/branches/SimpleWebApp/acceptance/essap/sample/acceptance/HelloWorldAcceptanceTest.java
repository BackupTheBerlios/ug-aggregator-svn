package essap.sample.acceptance;

import essap.sample.acceptance.lib.AcceptanceTestCase;

public class HelloWorldAcceptanceTest extends AcceptanceTestCase {
	public void testHelloWorld() throws Exception {
		get("/hello");
		assertExpectedTitle("Essap Sample Application");
		assertExpectedContent("Hello world!");
	}

}
