package essap.sample.lib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Errors {

	List messages = new ArrayList();
	Set invalidAttributes = new HashSet();
	
	public int size() {
		return messages.size();
	}

	public void add(String field, String message) {
		messages.add(message);
		invalidAttributes.add(field);
	}

	public List getMessages() {
		return messages;
	}

	public boolean invalidAttribute(String attr) {
		return invalidAttributes.contains(attr);
	}

}
