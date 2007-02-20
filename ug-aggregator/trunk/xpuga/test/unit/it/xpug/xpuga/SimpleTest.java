package it.xpug.xpuga;

import junit.framework.TestCase;

public class SimpleTest extends TestCase {

	public SimpleTest(String name) {
		super(name);
	}

	public void testSimple() {
		assertNotNull("can create Simple instance", new Simple());
	}
}
