package it.xpug.xpuga;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

public class NewsPieceTest extends TestCase {

  public void testConstructor() throws Exception {
    NewsPiece news = new NewsPiece();
    news.load("test/fixtures/news/oneNews/20060902173559");
    assertEquals("Test News Title", news.getTitle());
    assertEquals("Test News Description", news.getBody());
    assertEquals(XDate.create(2006, 9, 2, 17, 35, 59), news.getInsertionDate());
    assertEquals(XDate.create(2006, 10, 2, 17, 35, 59), news.getExpirationDate());
    assertEquals("milano-xpug", news.getGroupName());
  }
  
  public void testXDate() throws Exception {
    assertEquals(XDate.create(2006, 9, 2, 17, 35, 59), XDate.create("20060902173559"));
  }
  
  public void testFindNews() throws Exception {
    NewsManager nm = new NewsManager("test/fixtures/news/oneNews");
    List l = nm.getNewsList();
    assertEquals("ho avuto" + l, 1, l.size());
    NewsPiece news = (NewsPiece) l.get(0);
    assertEquals("Test News Title", news.getTitle());

    nm = new NewsManager("test/fixtures/news/fewNews");
    l = nm.getNewsList();
    assertEquals("ho avuto" + l, 4, l.size());
  }
  
  public void testDateHelpers() throws IOException, ParseException {
    NewsPiece news = new NewsPiece();
    news.load("test/fixtures/news/oneNews/20060902173559");
    assertEquals("20060902173559", news.getInsertionDateAsCode());
  }
  
  public void testOrder() throws Exception {
    NewsManager nm = new NewsManager("test/fixtures/news/fewNews");
    List news = nm.getNewsList();
    NewsPiece n0 = (NewsPiece) news.get(0);
    NewsPiece n3 = (NewsPiece) news.get(3);
    assertEquals("20060903120002", n0.getInsertionDateAsCode());
    assertEquals("20060902173559", n3.getInsertionDateAsCode());
  }
  
  public void testInsertNews() throws Exception {
    String fileName="20060920200104";
    NewsPiece news = NewsPieceCreator.valid();
    news.save(fileName);
    File file=new File(fileName);
    assertTrue("il file non e' stato creato",file.exists());
    NewsPiece readNews = new NewsPiece();
    readNews.load(fileName);
    assertEquals(news, readNews);
    file.delete();
  }

  private NewsPiece getNews(String title, String body, String groupName, String insertionDate, String expirationDate) throws ParseException {
    NewsPiece news = new NewsPiece();
    
    news.setTitle(title);
    news.setBody(body);
    news.setGroupName(groupName);
    news.setInsertionDate(insertionDate);
    news.setExpirationDate(expirationDate);
    return news;
  }
  
  public void testNewsValidation() throws Exception {
    NewsPiece news=new NewsPiece();
    assertFalse(news.isValid());
  }
  
  
  public void testNewsTitleValidation() throws Exception {
    NewsPiece news = NewsPieceCreator.valid();
    news.setTitle(null);
    assertFalse(news.isTitleValid());
    assertFalse(news.isValid());
    
    news.setTitle("");
    assertFalse(news.isTitleValid()); 
    assertFalse(news.isValid());
  }
  
  public void testNewsBodyValidation() throws Exception {
    NewsPiece news = getNews("newstitle", null, "milano-xpug", "20060920200104", "20070920200104");
    assertFalse(news.isBodyValid());
    
    news = getNews("newstitle", "", "milano-xpug", "20060920200104", "20070920200104");
    assertFalse(news.isBodyValid());
    
    assertFalse(news.isValid());
  }
  
  public void testNewsGroupValidation() throws Exception {
    NewsPiece news = getNews("newstitle", "newsbody", null, "20060920200104", "20070920200104");
    assertFalse(news.isGroupValid());
    
    news = getNews("newstitle", "newsbody", "", "20060920200104", "20070920200104");
    assertFalse(news.isGroupValid());
    
    assertFalse(news.isValid());
  }
  
  public void testNewsInvalidWhenInsertionDateAfterExpirationDate() throws Exception {
    NewsPiece news= NewsPieceCreator.valid();
    news.setInsertionDate("20060920200104");
    news.setExpirationDate("20010920200104");    
    assertFalse(news.isValid());
  }
  
  public void testNullInsertionDate() throws Exception {
    NewsPiece news = new NewsPiece();
    news.setTitle("newstitle");
    news.setBody("newsbody");
    news.setGroupName("mi-xpug");
    news.setExpirationDate("20070920200104");
    assertFalse(news.isInsertionDateValid());
    
    assertFalse(news.isValid());
  }
  
  public void testNullExpirationDateValidation() throws Exception {
    NewsPiece news = new NewsPiece();
    news.setTitle("newstitle");
    news.setBody("newsbody");
    news.setGroupName("mi-xpug");
    news.setInsertionDate("20060920200104");
    assertFalse(news.isExpirationDateValid());
    
    assertFalse(news.isValid());
  }
  
  public void testNewsInvalidWhenWrongUserGroup() throws Exception {
    NewsPiece news= NewsPieceCreator.valid();
    news.setGroupName("dbkbkzxbkbkd");
    assertFalse(news.isValid());
  }
  
  public void testNewsValidWhenAllFieldsOK() throws Exception {
    NewsPiece news=new NewsPiece();
    assertFalse(news.isValid());

    news = NewsPieceCreator.valid();
    
    assertTrue(news.isValid());
  }
}
