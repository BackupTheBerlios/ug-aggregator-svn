
package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;


public class NewsValidator {
	
	private static final String ERROR_EXPIRATION_DATE_BEFORE_TODAY = "Data scadenza antecedente data odierna";
	private static final String ERROR_EMPTY_TITLE = "Titolo Obbligatorio";
	
	private final int EXPIRATION_DATE = 3;
	private final int REGISTRATION_DATE = 2;
	private final int TITLE = 0;
	
	private boolean valid = false;
	private String errMsg = "";
	
	public NewsValidator(String newsAsString) {

		String[] tokens = newsAsString.split("\n");
		
		try
		{
			valid = parseDate(tokens[REGISTRATION_DATE]).before(parseDate(tokens[EXPIRATION_DATE]));			
		}
		catch (ParseException e) {
			valid = false;
		}
		errMsg = errMsg + (valid ? "" : ERROR_EXPIRATION_DATE_BEFORE_TODAY);
		
//		valid = !tokens[TITLE].equals("");
//		
//		errMsg = errMsg + (valid ? "" : ERROR_EMPTY_TITLE);
		

	}
	
	public boolean isValid() {
		return valid;
	}

	public String errorMessage() {
		return errMsg;
	}
	
	private GregorianCalendar parseDate(String dateAsString) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parse = format.parse(dateAsString);
		GregorianCalendar instance = (GregorianCalendar) GregorianCalendar.getInstance();
		instance.setTime(parse);
		return instance;
	}

	
	
	/*
	public static boolean validate(News news) {
		boolean isValid = true;
		isValid &= !news.title().equals("");
		isValid &= news.insertionDate().before(news.expirationDate()); 
		return isValid;
	}

	public static boolean validateNewsString(String newsAsString) {
		
		
		
	}
	*/
}
