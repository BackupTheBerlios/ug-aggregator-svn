package it.xpug.aggregator;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class JSPDesignTest extends TestCase {

	ServletRunner sr;

	public JSPDesignTest(String arg0) throws Exception {
		super(arg0);
		sr = new ServletRunner();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//server.stop();
	}


	    public void testNewsListPage() throws MalformedURLException, IOException, SAXException
		{	
	    	NewsCollection newsCollection=new NewsCollection();
	    	News news1=new News("Titolo news\n" + 
	    			"Descrizione news\n" +
	    			"2006/03/14 234412\n" + 
	    			"2010/04/25\n", "milano-xpug");
	    	News news2=new News("Titolo news2\n" + 
	    			"Descrizione news2\n" +
	    			"2006/02/14 234412\n" + 
	    			"2010/04/25\n", "milano-xpug");
	    	newsCollection.addNews(news1);
	    	newsCollection.addNews(news2);
	    	FakeNewsList.add(newsCollection);
	    	ServletUnitClient sc = sr.newClient();
		    WebRequest request1   = new PostMethodWebRequest( "http://localhost:8080/jsp/listNews.jsp" );
		    WebResponse response1 = sc.getResponse( request1 );
		    assertNotNull( "No response received", response1 );
		    assertEquals( "content type", "text/html", response1.getContentType() );
		    assertTrue(response1.getText().indexOf("<h2>Titolo news</h2>") > -1);	
		    assertTrue(response1.getText().indexOf("Titolo news2</h2>") > -1);	
		    assertTrue(response1.getText().indexOf("Titolo news</h2>")<response1.getText().indexOf("Titolo news2</h2>"));
			}

	    
		

}
