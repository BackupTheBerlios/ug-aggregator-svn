package it.xpug.aggregator.acceptance;

import it.xpug.aggregator.InsertNewsServlet;
import it.xpug.aggregator.acceptance.util.AcceptanceTestCase;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class InsertNewsAcceptanceTest extends AcceptanceTestCase {

	public void testShouldReturnTheNewsListIfTheNewsIsValid() throws Exception {
		ServletRunner sr = new ServletRunner();
	    sr.registerServlet( "insertNews", InsertNewsServlet.class.getName() );
	    ServletUnitClient sc = sr.newClient();
	    WebRequest request   = new PostMethodWebRequest( "http://iddoknow/insertNews" );
	    String random = "News " + Math.random();
	    request.setParameter( "title",  random);
	    request.setParameter( "content", "Il contenuto della news" );
	    request.setParameter( "expirationDate", "10/08/2006" );
	    WebResponse response = sc.getResponse( request );
	    assertFalse(response.getText().indexOf(random)==-1);
	}
	
}
