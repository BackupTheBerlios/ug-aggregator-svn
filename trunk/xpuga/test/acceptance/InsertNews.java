
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;

public class InsertNews extends XpugaTestCase {

	public InsertNews(String name) {
		super(name);
	}

	static class PostInsertNews extends DoPost {
		protected String title;
		protected String expirationDate;
		protected String userGroup;
		protected String body;

		public PostInsertNews(Map news) throws Exception {
			super(urlFor("/news"));
			title = (String)news.get("title");
			body = (String)news.get("body");
			userGroup = (String)news.get("user-group");
			expirationDate = (String)news.get("expiration-date");
		}

		public void prepare() throws Exception {
			addParameter("title", title);
			addParameter("expiration-date", expirationDate);
			addParameter("user-group", userGroup);
			addParameter("body", body);
		}

		public void assertNewsCreated() {
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

		protected void assertValidFieldsNotSignaledAsWrong(final Map news, final String[] emptyFields) throws Exception {
			
			new ProcessDocument(document()) {
				public void all(Document document) throws Exception {
					Set newsSet = news.keySet();
					for (int i = 0; i < emptyFields.length; i++) {
						newsSet.remove(emptyFields[i]);
					}
					Iterator newsFieldIterator=news.keySet().iterator();
					while (newsFieldIterator.hasNext()){
						String field=(String) newsFieldIterator.next();
						assertFalse(evalAsBoolean(document, "//*[@class='error'][@title='" + field + "']"));
					}
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
