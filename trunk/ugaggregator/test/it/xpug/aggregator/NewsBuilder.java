package it.xpug.aggregator;

public class NewsBuilder {

	public static News with(String expirationDate) {
		return new News(("Titolo news\n" + 
		"Descrizione news\n" +
		"2006/04/14\n" + 
		expirationDate +
		"\n"));
	}

	public static News withInsertionDate(String insertionDate) {
		return new News("pippo\n" + "pluto\n" + insertionDate
				+ "\n2006/07/12");
	}

}
