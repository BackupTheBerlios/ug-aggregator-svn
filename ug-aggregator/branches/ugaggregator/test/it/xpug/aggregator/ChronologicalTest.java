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
		newsCollection.addNews(NewsBuilder.withInsertionDate("2006/03/12 234412"));

		assertEquals(1, newsCollection.count());
	}

	public void testDoubleDate() {
		newsCollection.addNews(NewsBuilder.withInsertionDate("2006/03/12 234412"));
		newsCollection.addNews(NewsBuilder.withInsertionDate("2006/03/18 234412"));
		assertEquals(2, newsCollection.count());
		newsCollection.addNews(NewsBuilder.withInsertionDate("2006/03/12 234412"));
		assertEquals(3, newsCollection.count());
			
	}

	public void testRightSorting() {

		News secondDate = NewsBuilder.withInsertionDate("2006/03/18 234412");
		newsCollection.addNews(secondDate);
		News thirdDate = NewsBuilder.withInsertionDate("2006/03/21 234412");
		newsCollection.addNews(thirdDate);
		News firstDate = NewsBuilder.withInsertionDate("2006/03/12 234412");
		newsCollection.addNews(firstDate);

		Iterator i = newsCollection.iterator();
		assertNotNull(i);

		assertTrue(i.hasNext());

		assertEquals(firstDate, ((News) (i.next())));
		assertEquals(secondDate, ((News) (i.next())));
		assertEquals(thirdDate, ((News) (i.next())));

	}
	

	public void testDoubleDateSorting() {
//		when dates are equals lastest inserted one is put as the last of the day.

		News thirdDate = NewsBuilder.withInsertionDate("2006/03/21 234412");
		newsCollection.addNews(thirdDate);
		News firstDate = NewsBuilder.withInsertionDate("2006/03/18 234412");
		newsCollection.addNews(firstDate); 
		News secondDate = NewsBuilder.withInsertionDate("2006/03/18 234412");
		newsCollection.addNews(secondDate);
		
		Iterator i = newsCollection.iterator();

		assertEquals(firstDate, ((News) (i.next())));
		assertEquals(secondDate, ((News) (i.next())));
		assertEquals(thirdDate, ((News) (i.next())));
		assertFalse(i.hasNext());
	}	

	protected void setUp() throws Exception {
		super.setUp();
		newsCollection = new NewsCollection();
	}

}
