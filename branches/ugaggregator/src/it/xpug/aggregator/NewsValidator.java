
package it.xpug.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


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
				
		appendError(checkRegistrationDateFormat(tokens[REGISTRATION_DATE]), ERROR_INVALID_REGISTRATION_DATE_FORMAT);
		if (!valid) return;
		
		appendError(checkExpirationDateFormat(tokens[EXPIRATION_DATE]), ERROR_INVALID_EXPIRATION_DATE_FORMAT);
		if (!valid) return;
	
	
		try {
			appendError(
					checkRegistrationDateBeforeExpirationDate(
						 DateParser.parseRegistration(tokens[REGISTRATION_DATE]), 
						 DateParser.parseExpiration(tokens[EXPIRATION_DATE])), 
					ERROR_EXPIRATION_DATE_BEFORE_TODAY);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!valid) return;
			
		appendError(checkTitle(tokens), ERROR_EMPTY_TITLE);
		if (!valid) return;

	}

	private void appendError(boolean condition, String errorDescription) {
		valid = valid && condition;
		errMsg = errMsg + (condition ? "" : errorDescription);
	}


	private boolean checkRegistrationDateFormat(String date) {

		try
		{
			DateParser.parseRegistration(date);			
		}
		catch (ParseException e) {
			return false;
		}	

		return true;		
	}

	private boolean checkExpirationDateFormat(String date) {

		try
		{
			DateParser.parseExpiration(date);			
		}
		catch (ParseException e) {
			return false;
		}	

		return true;		
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
	
}
