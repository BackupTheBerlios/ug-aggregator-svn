

import java.io.*;
import java.util.*;
import java.text.*;

import org.w3c.dom.*; 

public class InsertNewsTest extends HttpTestCase {

	private String locationNewsCreated;
	private Map newsToInsert;

	public InsertNewsTest(String name) {
		super(name);
	}

	public void testCreateLocationIfNotExists() throws Exception {
		setLocation("/insert/noNews");
		new DoGet(urlFor("/news/location/path")) {
			public void process() throws Exception {
				assertEquals(200, status);
				assertTrue("created news location: " + content(),
						new File(content()).isDirectory());
			}
		}.execute();
	}

	public void testInsertOneNews() throws Exception {
		setLocation("/insert/oneNews");

		newsToInsert = InsertNews.validNews();
		new InsertNews.Implementation(newsToInsert) {
			public void process() throws Exception {
				assertNewsCreated();
				locationNewsCreated = header("Location");
			}
		}.execute();

		newsToInsert.put("insertion-date", extractNewsIdFromNewsLocation(locationNewsCreated));
		new ProcessNews.Implementation() {
			public void all(Document document) throws Exception {
				assertNumberOfNewsOnPage(1);
				assertNewsOnPage(newsToInsert);
			}
		}.execute();
	}

	private String extractNewsIdFromNewsLocation(String newsLocation) {
		return locationNewsCreated.substring(locationNewsCreated.lastIndexOf('/')+1);
	}

}
