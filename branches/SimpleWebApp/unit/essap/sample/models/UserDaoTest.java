package essap.sample.models;

import essap.sample.lib.PersistenceException;
import junit.framework.TestCase;

public class UserDaoTest extends TestCase {

	public void testEmpty() throws Exception {
		assertEquals(0, UserDao.count());
	}

	public void testInsert() throws Exception {
		User user = new User("Gino", "Bramieri", "foo@bar.com");
		UserDao.save(user);
		assertEquals(1, UserDao.count());
	}
	
	public void testInsertInvalidUser() {
		User user = new User("", "Bla", "foo@bar.com");
		try {
			UserDao.save(user);
			fail("didn't throw");
		} catch (PersistenceException e) {
			assertEquals("user [, Bla, foo@bar.com] invalid", e.getMessage());
		}		
	}
	
	public void testFindUser() {
		fail("unfinished");
	}
}
