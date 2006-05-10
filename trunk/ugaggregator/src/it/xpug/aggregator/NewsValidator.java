
package it.xpug.aggregator;

import java.util.regex.Pattern;


public class NewsValidator {
	
	public static boolean validate(News news) {
		boolean isValid = true;
		isValid &= !news.title().equals("");
		isValid &= news.insertionDate().before(news.expirationDate()); 
		return isValid;
	}

	public static boolean validateNewsString(String newsAsString) {
		
		//TODO :
		//		creazione pattern di validazione stringa che rappresenta la news.
		
		return false;
		//return newsAsString.matches("\\n{4}$");

	}
	
}
