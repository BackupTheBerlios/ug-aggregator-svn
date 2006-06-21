package it.xpug.aggregator;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import junit.framework.TestCase;

public class InsertGuiNewsTest extends TestCase {
	
	private ServletRunner runner;

	public InsertGuiNewsTest() {
		runner = new ServletRunner();
	}

	public void testTheJspShouldExist() throws Exception {
		
		ServletUnitClient sc = runner.newClient();
		System.out.println("user dir= "+System.getProperty("user.dir"));
		WebRequest request = new PostMethodWebRequest("http://nonmiinteressa/jsp/insertnews.jsp");
		WebResponse response = sc.getResponse(request);
		int responseCode = response.getResponseCode();
		assertEquals(200, responseCode);
		
	}
	
	public void testShouldContainAForm() throws Exception {
		ServletUnitClient sc = runner.newClient();
		System.out.println("user dir= "+System.getProperty("user.dir"));
		WebRequest request = new PostMethodWebRequest("http://nonmiinteressa/jsp/insertnews.jsp");
		WebResponse response = sc.getResponse(request);
		WebForm[] forms = response.getForms();
		assertEquals(1, forms.length);
	}
	
}
