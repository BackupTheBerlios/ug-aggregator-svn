package essap.sample.lib;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class ErrorsTest extends TestCase {

	public void testEmpty() throws Exception {
		Errors errors = new Errors();
		assertEquals(0, errors.size());
	}
	
	public void testAddError() throws Exception {
		Errors errors = new Errors();
		String msg0 = "Il campo Frobniz ha un valore sbagliato";
		String msg1 = "Il campo Blaniz pure";
		
		errors.add("frobniz", msg0);
		
		assertEquals("errors", 1, errors.size());
		assertEquals(list(msg0), errors.getMessages());
		assertTrue("frobniz invalid", errors.invalidAttribute("frobniz"));
		assertFalse("zork valid", errors.invalidAttribute("zork"));

		errors.add("zork", msg1);

		assertEquals("errors", 2, errors.size());
		assertEquals(list(msg0, msg1), errors.getMessages());		
		assertTrue("frobniz invalid", errors.invalidAttribute("frobniz"));
		assertTrue("zork invalid", errors.invalidAttribute("zork"));
	}

	private List list(Object a) {
		return Collections.singletonList(a);
	}
	
	private List list(Object a, Object b) {
		return Arrays.asList(new Object[] { a, b });
	}
	
}
