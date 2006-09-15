package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NewsManager {

	private File dir; 
	
	public NewsManager(String dirName) {
		dir = new File(dirName);
	}

	public List getNewsList() throws IOException, ParseException  {
		List result = new ArrayList();
	    String[] children = dir.list();
	    if (children != null) {
	        for (int i=0; i<children.length; i++) {	 
	        	if (!children[i].startsWith(".")) {
	        		NewsPiece n = new NewsPiece();
	        		n.load(dir.getPath() + File.separator + children[i]);
	        		result.add(0, n);
	        	}
	        }
	    }
		return result;
	}
}
