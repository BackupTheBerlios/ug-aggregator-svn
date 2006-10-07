import java.io.*;
import java.util.*;
import java.text.*;

import org.w3c.dom.*; 

public class InsertNewsWithValidFieldsValueTest extends HttpTestCase {

	public InsertNewsWithValidFieldsValueTest(String name) {
		super(name);
	}

	public void testInsertInvalidUserGroup() throws Exception {
		setLocation("/insert/newsWithInvalidUserGroup");

		Map news = InsertNews.validNews();
		news.put("user-group", "some-strange-group");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "user-group" });
			}
		}.execute();
	}

	public void testInsertionDateBeforeExpirationDate() throws Exception {
		setLocation("/insert/newsWithInvalidExpirationDate");

		Map news = InsertNews.validNews();
		news.put("expiration-date", "20050101000000");

		new InsertNews.Implementation(news) {
			public void process() throws Exception {
				assertEquals(422, status);
				assertInvalidFields(new String[] { "expiration-date" });
			}
		}.execute();
	}

}
