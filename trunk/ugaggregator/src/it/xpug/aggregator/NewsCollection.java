/*
 * Created on 12-apr-2006
 */
package it.xpug.aggregator;

import java.text.ParseException;
import java.util.*;

public class NewsCollection {

  protected SortedSet newsList = new TreeSet(createNewsComparator());
  
  public void addNews(News news) {
    newsList.add(news);
  }

  private Comparator createNewsComparator() {
    return new Comparator(){

      public int compare(Object o1, Object o2) {
        try {
          return (int) (((News)o1).insertionDate().getTimeInMillis() - 
                       ((News)o2).insertionDate().getTimeInMillis());
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }
        
      }
    };
  }


  public int count() {
    return newsList.size();
  }

}
