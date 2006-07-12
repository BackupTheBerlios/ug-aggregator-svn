package it.xpug.aggregator;

import java.util.GregorianCalendar;
import java.util.Iterator;

public class NewsFilter {
	
	private GregorianCalendar referenceDate;

	public NewsFilter() {
		this(new GregorianCalendar());		
	}
	
	public NewsFilter(GregorianCalendar referenceDate) {
		this.referenceDate = referenceDate;
	}
	
	public NewsCollection filter(NewsCollection newsList2Filter) {
		NewsCollection newsListFiltered = new NewsCollection();
		
		for (Iterator i = newsList2Filter.newsList.iterator(); i.hasNext(); ) {
			News currNews = (News)i.next();
			
			if (!currNews.expirationDate().before(referenceDate))
				newsListFiltered.addNews(currNews);
		}
		
		return newsListFiltered;
	}

}
