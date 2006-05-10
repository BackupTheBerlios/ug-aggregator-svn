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
		return new Comparator() {

			public int compare(Object o1, Object o2) {

				long d1 = ((News) o1).insertionDate().getTimeInMillis();
				long d2 = ((News) o2).insertionDate().getTimeInMillis();
				if (d1 == d2) {
					return 1;
				} else {
					return (int) (d1 - d2);
				}
			}
		};
	}

	public int count() {
		return newsList.size();
	}

	public Iterator iterator() {

		return newsList.iterator();
	}

}
