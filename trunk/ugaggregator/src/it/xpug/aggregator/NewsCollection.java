/*
 * Created on 12-apr-2006
 */
package it.xpug.aggregator;

import java.text.ParseException;
import java.util.*;

public class NewsCollection {

  protected SortedMap newsList = new TreeMap();
    
  public void addNews(News news) {
    try {
      newsList.put(news.insertionDate(), news);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public int count() {
    return newsList.size();
  }

}
