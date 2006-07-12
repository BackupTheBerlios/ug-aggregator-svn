package it.xpug.aggregator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import junit.framework.TestCase;

import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class XpugServletTest extends TestCase {

	public class FakeServletConfig implements ServletConfig {

		private String myValue;
		private String myName;
		
		// Fake
		public void setProperty(String name, String value) {
			myName = name;
			myValue = value;
		}
		
		// ServletConfig
		public String getInitParameter(String arg0) {
			return arg0 == myName ? myValue : null;
		}

		public Enumeration getInitParameterNames() {
			return null;
		}

		public ServletContext getServletContext() {
			return null;
		}

		public String getServletName() {
			return null;
		}
	}

	private ServletRunner sr;

	protected void setUp() throws Exception {
		super.setUp();
		//server.start();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//server.stop();
	}

	public XpugServletTest(String arg0) throws Exception {
		super(arg0);
		sr = new ServletRunner();
	    sr.registerServlet( "myServlet", XpugServlet.class.getName() );
	    
		/*server = new Server();
		server.addListener(":8081");
		ServletHttpContext context = (ServletHttpContext) server
				.getContext("/");
		context.addServlet("jettyservlet", "/jettyservlet/*",
				"it.fabiobeta.JettyServlet");*/
	}

	public void testDoGetHttpServletRequestHttpServletResponse() throws MalformedURLException, IOException, SAXException
	{
		/*WebConversation wc = new WebConversation();
		WebRequest req = new GetMethodWebRequest(
				"http://localhost:8081/jettyservlet/pippo.html");
		WebResponse resp = wc.getResponse(req);
		resp.getInputStream();
		assertTrue(resp.getText().indexOf("This is a simple Servlet!") > -1);
	*/
	    ServletUnitClient sc = sr.newClient();
	    WebRequest request   = new PostMethodWebRequest( "http://it.xpug.aggregator/myServlet" );
	    WebResponse response = sc.getResponse( request );
	    assertNotNull( "No response received", response );
	    assertEquals( "content type", "text/html", response.getContentType() );
	    assertTrue(response.getText().indexOf("This is a simple Servlet!") > -1);

	}
	
	public void testInitParameterShouldBeCopiedToSystemProperty() throws Exception {
		XpugServlet servlet = new XpugServlet();
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR,"");
		
		FakeServletConfig myConfig = new FakeServletConfig();
		myConfig.setProperty(XpugServlet.NEWSDB_DIR_PARAM, "piciopacio");
		
		servlet.init(myConfig);		
		assertEquals("piciopacio", System.getProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR));
	}
	
	public void testShoulUseADefaultValueForDirNameWhenItIsNotFoundInWebXml() throws Exception {
		XpugServlet servlet = new XpugServlet();
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR,"");
		
		FakeServletConfig myConfig = new FakeServletConfig();		
		servlet.init(myConfig);
		assertEquals("newsdb", System.getProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR));
	}

}
