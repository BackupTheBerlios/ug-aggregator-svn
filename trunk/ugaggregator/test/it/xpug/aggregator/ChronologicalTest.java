/*
 * Created on 12-apr-2006
 */
package it.xpug.aggregator;

import java.util.Iterator;

import junit.framework.TestCase;

public class ChronologicalTest extends TestCase {

	protected NewsCollection newsCollection;

	public void testSimple() {
		assertEquals(0, newsCollection.count());
		newsCollection.addNews(createNews("2006/03/12"));

		assertEquals(1, newsCollection.count());
	}

	private News createNews(String insertionDate) {
		return NewsBuilder.withInsertionDate(insertionDate);
	}

	public void testDoubleDate() {
		newsCollection.addNews(createNews("2006/03/12"));
		newsCollection.addNews(createNews("2006/03/18"));
		assertEquals(2, newsCollection.count());
		newsCollection.addNews(createNews("2006/03/12"));
		assertEquals(3, newsCollection.count());
			
	}

	public void testRightSorting() {

		News secondDate = createNews("2006/03/18");
		newsCollection.addNews(secondDate);
		News thirdDate = createNews("2006/03/21");
		newsCollection.addNews(thirdDate);
		News firstDate = createNews("2006/03/12");
		newsCollection.addNews(firstDate);

		Iterator i = newsCollection.iterator();
		assertNotNull(i);

		assertTrue(i.hasNext());

		assertEquals(firstDate, ((News) (i.next())));
		assertEquals(secondDate, ((News) (i.next())));
		assertEquals(thirdDate, ((News) (i.next())));

	}
	

	public void testRightDoubleDateSorting() {
//		when dates are equals lastest inserted one is put as the last of the day.

		News thirdDate = createNews("2006/03/21");
		newsCollection.addNews(thirdDate);
		News firstDate = createNews("2006/03/18");
		newsCollection.addNews(firstDate); 
		News secondDate = createNews("2006/03/18");
		newsCollection.addNews(secondDate);
		
		Iterator i = newsCollection.iterator();
		assertNotNull(i);

		assertTrue(i.hasNext());

		assertEquals(firstDate, ((News) (i.next())));
		assertEquals(secondDate, ((News) (i.next())));
		assertEquals(thirdDate, ((News) (i.next())));

	}	

	protected void setUp() throws Exception {
		super.setUp();
		newsCollection = new NewsCollection();
	}

}
