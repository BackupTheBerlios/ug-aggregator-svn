package it.xpug.aggregator;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import junit.framework.TestCase;

public class InitServletTest extends TestCase {

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

	public void testShoulUseADefaultValueForDirNameWhenItIsNotFoundInWebXml() throws Exception {
		InitServlet servlet = new InitServlet();
		System.setProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR,"");
		
		FakeServletConfig myConfig = new FakeServletConfig();		
		servlet.init(myConfig);
		assertEquals("newsdb", System.getProperty(NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR));
	}

}
