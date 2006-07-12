package it.xpug.aggregator;

import java.util.Iterator;

public class FakeNewsList {

	private static String titolo;
	private static Object testo;
	private static NewsCollection newsCollection = new NewsCollection();
	

	public static Iterator getIterator()
	{
		return newsCollection.iterator();
	}

	public static void setNewsCollection(NewsCollection newsCollection2) {
		newsCollection=newsCollection2;
	}

}
