import java.util.Map;

public class InsertNewsWithNotEmptyFieldsTest extends HttpTestCase {

	public InsertNewsWithNotEmptyFieldsTest(String name) {
		super(name);
	}

	private void insertInvalidNewsWithEmptyFields(final String[] emptyFields) throws Exception {
		final Map news = InsertNews.validNews();
		
		for (int i=0; i<emptyFields.length; i++)
			news.put(emptyFields[i], "");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(emptyFields);
			}
		}.execute();
	}

	public void testInsertNewsWithEmptyTitle() throws Exception {
		setLocation("/insert/newsWithEmptyTitle");
		insertInvalidNewsWithEmptyFields(new String[] { "title" });
	}

	public void testInsertNewsWithEmptyBody() throws Exception {
		setLocation("/insert/newsWithEmptyBody");
		insertInvalidNewsWithEmptyFields(new String[] { "body" });
	}

	public void testInsertNewsWithEmptyUserGroup() throws Exception {
		setLocation("/insert/newsWithEmptyUserGroup");
		insertInvalidNewsWithEmptyFields(new String[] { "user-group" });
	}

	public void testInsertNewsWithEmptyExpirationDate() throws Exception {
		setLocation("/insert/newsWithEmptyExpirationDate");
		insertInvalidNewsWithEmptyFields(new String[] { "expiration-date" });
	}

	public void testInsertNewsWithEmptyTitleAndExpirationDate() throws Exception {
		setLocation("/insert/newsWithEmptyTitleAndExpirationDate");
		insertInvalidNewsWithEmptyFields(
				new String[] { "title", "expiration-date" });

	}

}
