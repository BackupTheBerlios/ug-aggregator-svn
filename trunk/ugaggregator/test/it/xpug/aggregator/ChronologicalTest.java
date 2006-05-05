/*
 * Created on 12-apr-2006
 */
package it.xpug.aggregator;

import junit.framework.TestCase;

public class ChronologicalTest extends TestCase {
  
  protected NewsCollection newsCollection;
  
  public void testSimple() {
    assertEquals(0, newsCollection.count());
    newsCollection.addNews(createNews("2006/03/12"));
    
    assertEquals(1, newsCollection.count());
  }

  private News createNews(String insertionDate) {
    News news01 = new News("pippo\n" +
                           "pluto\n" +
                           insertionDate + "\n" +
                           "2006/07/12\n"
                           );
    return news01;
  }
  
  public void testDoubleDate() {
    newsCollection.addNews(createNews("2006/03/12"));
    newsCollection.addNews(createNews("2006/03/18"));    
    assertEquals(2, newsCollection.count());
    newsCollection.addNews(createNews("2006/03/12"));
    //TODO Il test seguente fallira': ripartire da qui
    //assertEquals(2, newsCollection.count());    
  }

  protected void setUp() throws Exception {
    super.setUp();
    newsCollection = new NewsCollection();
  }

}
