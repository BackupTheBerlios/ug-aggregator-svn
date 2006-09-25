
import java.util.*;
import java.text.*;

import org.w3c.dom.*; 

import junit.framework.*;

public class InsertNews extends HttpTestCase {

	public InsertNews(String name) {
		super(name);
	}

	static class Implementation extends DoPost {
		protected String title;
		protected String expirationDate;
		protected String userGroup;
		protected String body;

		public Implementation(Map news) throws Exception {
			super(urlFor("/news"));
			title = (String)news.get("title");
			body = (String)news.get("body");
			userGroup = (String)news.get("user-group");
			expirationDate = (String)news.get("expiration-date");
		}

		public void prepare() throws Exception {
			parameter("title", title);
			parameter("expiration-date", expirationDate);
			parameter("user-group", userGroup);
			parameter("body", body);
		}

		protected void assertNewsCreated() {
			assertEquals("201 means news created", 201, status);
		}

		protected void assertInvalidFields(final String[] invalidFields) throws Exception {
			new ProcessDocument(document()) {
				public void all(Document document) throws Exception {
					for (int i=0; i<invalidFields.length; i++)
						assertTrue(evalAsBoolean(document, "//*[@class='error'][@title='" + invalidFields[i] + "']"));
				}
			}.execute();
		}

	}

	public static Map validNews() {
		Map news = new HashMap();
		news.put("title", "news title");
		news.put("body", "news body");
		news.put("user-group", "milano-xpug");
		news.put("expiration-date", InsertNews.nextMonth());
		return news;
	}

	public static String nextMonth() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
	}

}
