
package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;


public class NewsValidator {
	
	public static final String ERROR_EXPIRATION_DATE_BEFORE_TODAY = "Data scadenza antecedente data odierna";
	public static final String ERROR_EMPTY_TITLE = "Titolo Obbligatorio";
	public static final String ERROR_INVALID_REGISTRATION_DATE_FORMAT = "Formato data registrazione non valido";
	public static final String ERROR_INVALID_EXPIRATION_DATE_FORMAT = "Formato data scadenza non valido";
	
	
	private final int EXPIRATION_DATE = 3;
	private final int REGISTRATION_DATE = 2;
	private final int TITLE = 0;
	
	private boolean valid = true;
	private String errMsg = "";
	
	public NewsValidator(String newsAsString) {

		//TODO : Provare ad utilizzare il pattern Decorator.
		
		String[] tokens = newsAsString.split("\n");
				
		appendError(checkDateFormat(tokens[REGISTRATION_DATE]), ERROR_INVALID_REGISTRATION_DATE_FORMAT);
		if (!valid) return;
		
		appendError(checkDateFormat(tokens[EXPIRATION_DATE]), ERROR_INVALID_EXPIRATION_DATE_FORMAT);
		if (!valid) return;
	
		appendError(
				checkRegistrationDateBeforeExpirationDate(
						getDateFromString(tokens[REGISTRATION_DATE]), 
						getDateFromString(tokens[EXPIRATION_DATE])), 
				ERROR_EXPIRATION_DATE_BEFORE_TODAY);
		if (!valid) return;
			
		appendError(checkTitle(tokens), ERROR_EMPTY_TITLE);
		if (!valid) return;

	}

	private void appendError(boolean condition, String errorDescription) {
		valid = valid && condition;
		errMsg = errMsg + (condition ? "" : errorDescription);
	}

	private GregorianCalendar getDateFromString(String date) {
		try
		{
			return parseDate(date);			
		}
		catch (ParseException e) {
			return null;
		}	
	}

	private boolean checkDateFormat(String date) {

		return getDateFromString(date) != null;
	}

	private boolean checkRegistrationDateBeforeExpirationDate(GregorianCalendar registrationDate, GregorianCalendar expirationDate) {
		return registrationDate.before(expirationDate);			
	}

	private boolean checkTitle(String[] tokens) {
		return !tokens[TITLE].equals("");
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

	
}
