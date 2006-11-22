package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsManager {

	private File dir; 
	private Date today;
	
	public NewsManager(String dirName) {
		dir = new File(dirName);
		this.today = new Date();
	}
	
	public NewsManager(String dirName, Date date) {
		dir = new File(dirName);
		this.today = date;
	}

	public List getNewsList() throws IOException, ParseException  {
		List result = new ArrayList();
	    String[] children = dir.list();
	    if (children != null) {
	        for (int i=0; i<children.length; i++) {	 
	        	if (!children[i].startsWith(".")) {
	        		String filename = dir.getPath() + File.separator + children[i];
					NewsPiece newsPiece = new NewsPiece(filename);
	        		if (isCurrent(newsPiece)) {
	        			result.add(0, newsPiece);
	        		}
	        	}
	        }
	    }
		return result;
	}

	private boolean isCurrent(NewsPiece n) {
		Date expirationDate = n.getExpirationDate();
		return today.before(new Date(expirationDate.getTime() + 24 * 60 * 60 * 1000));
	}
}
