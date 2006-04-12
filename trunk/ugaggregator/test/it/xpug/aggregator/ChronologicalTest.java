/*
 * Created on 12-apr-2006
 */
package it.xpug.aggregator;

import junit.framework.TestCase;

public class ChronologicalTest extends TestCase {
  
  public void testSimple() {
    NewsCollection newsCollection = new NewsCollection();
    News news01 = new News("pippo\n" +
                           "pluto\n" +
                           "2006/04/23" +
                           "2006/07/12"
                           );
    assertEquals(0, newsCollection.count());
    newsCollection.addNews(news01);
    
    assertEquals(1, newsCollection.count());
  }

}
