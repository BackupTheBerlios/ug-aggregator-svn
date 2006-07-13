package essap.sample.models;

import essap.sample.lib.PersistenceException;

public class UserDao {

	static int count = 0;
	
	public static int count() {
		return count;
	}

	public static void save(User user) throws PersistenceException {
		if (user.errors().size() > 0) {
			throw new PersistenceException("user " + user + " invalid");
		}
		count++;
	}

}
