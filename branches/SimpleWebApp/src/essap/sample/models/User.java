package essap.sample.models;

import java.util.Arrays;
import java.util.Map;

import essap.sample.lib.Errors;

public class User {

	private static final String EMAIL_REGEX = ".+@(.+\\.)+.+";
	
	private String firstName, lastName, email;
	
	public User(Map attributes) {
		firstName = getAttribute(attributes, "first_name");
		lastName = getAttribute(attributes, "last_name");
		email = getAttribute(attributes, "email");
	}

	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	private String getAttribute(Map attributes, String name) {
		Object obj = attributes.get(name);
		if (obj instanceof String) {
			return (String) obj;			
		}
		if (obj instanceof String[]) {
			String[] params = (String[]) obj;
			return params[0];
		}
		return null;
	}

	public String firstName() {
		return firstName;
	}

	public String lastName() {
		return lastName;
	}

	public String email() {
		return email;
	}

	public Errors errors() {
		return validate();
	}

	private Errors validate() {
		Errors result = new Errors();
		if (nullOrEmpty(firstName)) result.add("first_name", "First name is missing");
		if (nullOrEmpty(lastName)) result.add("last_name", "Last name is missing");
		if (invalidEmail(email)) result.add("email", "Email is invalid");
		return result;
	}

	static boolean invalidEmail(String email) {
		return !email.matches(EMAIL_REGEX);
	}

	private boolean nullOrEmpty(String s) {
		return null == s || 0 == s.length();
	}

	public String toString() {
		return Arrays.asList(new Object[] {
			firstName, lastName, email	
		}).toString();
	}

}
