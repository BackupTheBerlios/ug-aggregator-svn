package it.xpug.xpuga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

  public String getInsertionDateAsFormatted() {
	  return  XDate.getFormatted(insertionDate);
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setExpirationDate(String expirationDate) throws ParseException {
    this.expirationDate = XDate.create(expirationDate);
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void setInsertionDate(String insertionDate) throws ParseException {
    this.insertionDate = XDate.create(insertionDate);
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  public void save(String filename) {
    try {
          BufferedWriter out = new BufferedWriter(new FileWriter(filename));
          out.write(getTitle()+"\n");
          out.write(getBody()+"\n");
          out.write(getInsertionDateAsCode()+"\n");
          out.write(getExpirationDateAsCode()+"\n");
          out.write(getGroupName()+"\n");
          out.close();
      } catch (IOException e) {
      }
    
  }

  public boolean isValid() {
    if ((isTitleValid())&&
        (isBodyValid())&&
        (isInsertionDateValid())&&
        (isExpirationDateValid()) &&
        (isGroupValid())) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isTitleValid() {
    if((title==null) || "".equals(title)) {
    return false;
    }
    return true;
  }

  public boolean isBodyValid() {
    if ((body==null) || "".equals(body)) {
    return false;
    }
    return true;
  }

  public boolean isGroupValid() {
    return "milano-xpug".equalsIgnoreCase(groupName);
  }

  
  public boolean isInsertionDateValid() {
    if(insertionDate==null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean isExpirationDateValid() {
    if(expirationDate==null) {
      return false;
    } else {
      return insertionDate.before(expirationDate);
    }
  }

  public boolean equals(Object obj) {
    try {
      NewsPiece other = ((NewsPiece)obj);
      if (other.getTitle().equals(getTitle()) && 
          other.getBody().equals(getBody()) &&
          other.getGroupName().equals(getGroupName()) &&
          other.getInsertionDate().equals(getInsertionDate()) &&
          other.getExpirationDate().equals(getExpirationDate()))  {
        return true;
      } else {
        return false;
      }
    } catch (RuntimeException e) {
      return false;
    }    
  }

  public int hashCode() {
    return hash(getTitle()) + hash(getBody());
  }

  private int hash(Object obj) {
    if (obj == null) {
      return -1;
    } else {
      return obj.hashCode();
    }
  }
  
  
}
