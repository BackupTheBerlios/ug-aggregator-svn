package essap.sample.acceptance;

import essap.sample.acceptance.lib.AcceptanceTestCase;
import essap.sample.models.UserDao;

public class InsertUserAcceptanceTest extends AcceptanceTestCase {
	public void testInputForm() throws Exception {
		get("/user/new");
		assertValidXhtml();
		assertExpectedTitle("Insert new user");
		assertExpectedFormControl("first_name", "");
		assertExpectedFormControl("last_name", "");
		assertExpectedFormControl("email", "");
	}

	public void testHappyPath() throws Exception {
		assertUserCountIs(0);

		get("/user/new");
		setFormControl("first_name", "Gino");
		setFormControl("last_name", "Ginotti");
		setFormControl("email", "gino@ginotti.com");
		submit();		
//		assertRedirected("/user/list");
		
		assertUserCountIs(1);		
	}
	
	private void assertUserCountIs(int expected) {
		assertEquals("bad user count", expected, UserDao.count());
	}

	public void testShouldRejectBadEmail() throws Exception {
		get("/user/new");
		setFormControl("first_name", "");
		setFormControl("last_name", "Ginotti");
		setFormControl("email", "xyz");
		submit();
		assertUserCountIs(1);		
// assertSuccess
		assertExpectedContent("First name is missing");
		assertExpectedContent("Email is invalid");
	}
}
