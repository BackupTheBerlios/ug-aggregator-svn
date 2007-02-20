package it.xpug.xpuga;

import java.text.ParseException;


public class NewsPieceCreator {

  public static NewsPiece valid() {
    try {
      NewsPiece news = new NewsPiece();
      news.setTitle("nuova news");
      news.setBody("abbiamo una nuova news");
      news.setGroupName("milano-xpug");
      news.setInsertionDate("20060920200104");
      news.setExpirationDate("20070920200104");
      return news;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  
}
