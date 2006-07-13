package essap.sample.models;

import java.util.Map;
import java.util.HashMap;

import essap.sample.lib.Errors;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	public void testConstructFromMap() throws Exception {
		Map map = new HashMap();
		map.put("first_name", "Pino");
		map.put("last_name", "Topolino");
		map.put("email", "pino@topolino.org");
		
		User actual = new User(map);
		
		assertEquals("errors", 0, actual.errors().size());
		assertEquals("Pino", actual.firstName());
		assertEquals("Topolino", actual.lastName());
		assertEquals("pino@topolino.org", actual.email());
	}
	
	public void testConstructFromServletParameterMap() throws Exception {
		Map map = new HashMap();
		map.put("first_name", new String[] { "Joe" });
		map.put("last_name", new String[] { "Hill" });
		map.put("email", new String[] { "culture@heroes.net" });
		
		User actual = new User(map);
		
		assertEquals("errors", 0, actual.errors().size());
		assertEquals("Joe", actual.firstName());
		assertEquals("Hill", actual.lastName());
		assertEquals("culture@heroes.net", actual.email());
	}
	
	public void testValidationErrors() {
		Map map = new HashMap();
		map.put("first_name", new String[] { "" });
		map.put("email", new String[] { "foobarz" });
		
		User user = new User(map);
		Errors errors = user.errors();
		
		assertEquals("errors", 3, errors.size());
		assertTrue("firstName invalid", errors.invalidAttribute("first_name"));
		assertTrue("lastName invalid", errors.invalidAttribute("last_name"));
		assertTrue("email invalid", errors.invalidAttribute("email"));
		assertEquals("First name is missing", errors.getMessages().get(0));
		assertEquals("Last name is missing", errors.getMessages().get(1));
		assertEquals("Email is invalid", errors.getMessages().get(2));
	}
	
	public void testInvalidEmail() throws Exception {
		assertFalse("foo",  User.invalidEmail("foo@bar.com"));
		assertTrue("bar",  User.invalidEmail("bar.com"));
		assertTrue("baz",  User.invalidEmail("foo@baz"));
	}
}
