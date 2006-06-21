package it.xpug.aggregator;

public class NewsBuilder {

	public static News with(String expirationDate) {
		return new News("Titolo news\n" + 
		"Descrizione news\n" +
		"2006/04/14 234412\n" + 
		expirationDate + "\n", "XPUGMI");
	}

	public static News withInsertionDate(String insertionDate) {
		return new News("Titolo news\n" + "Descrizione news\n" + insertionDate
				+ "\n2006/07/12", "XPUGMI");
	}

	public static News withAllFields(String newsAsString) {
		return new News(newsAsString, "XPUGMI");
	}

}
