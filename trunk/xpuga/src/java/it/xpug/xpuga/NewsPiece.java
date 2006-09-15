package it.xpug.xpuga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class NewsPiece {

	private String title;
	private String body;
	private Date insertionDate;
	private Date expirationDate;
	private String groupName;
	
	public void load(String filename) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
        title = in.readLine();
        body = in.readLine();
        insertionDate = XDate.create(in.readLine());
        expirationDate = XDate.create(in.readLine());
        groupName = in.readLine();
        in.close();		
	}

	public String getTitle() {
		return title;
	}
	
	public String getBody() {
		return body;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public String getInsertionDateAsCode() {
		return XDate.getCode(insertionDate);
	}

	public String getExpirationDateAsCode() {
		return XDate.getCode(expirationDate);
	}

}
