package it.xpug.aggregator;

import java.text.ParseException;
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

		if (newsList2Filter == null)
			throw new IllegalArgumentException("News to filter is null");
		
		NewsCollection newsListFiltered = new NewsCollection();
		
		for (Iterator i = newsList2Filter.newsList.iterator(); i.hasNext(); ) {
			News currNews = (News)i.next();
			
			//TODO : News exirationDate non dovrebbe rilanciare un eccezione
			//		 Rimuovere il try/catch dopo la modifica

			try {
				if (!currNews.exirationDate().before(referenceDate))
					newsListFiltered.addNews(currNews);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return newsListFiltered;
	}

}
