package essap.sample.helloworld;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.w3c.tidy.Tidy;

import com.meterware.servletunit.ServletTestCase;

import essap.sample.helloworld.HelloWorldServlet;

public class HelloWorldServletTest extends ServletTestCase {

	HelloWorldServlet servlet;

	public HelloWorldServletTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		servlet = new HelloWorldServlet();
	}
	
	public void testAnswerShouldContainHelloWorld() throws Exception {
		StringWriter writer = new StringWriter();
		servlet.doHelloWorld(writer);
		assertTrue("hello world not found", writer.getBuffer().toString().contains("Hello world!"));		
	}
	
	public void testShouldRespondWithValidXHtml() throws Exception {
		Tidy tidy = new Tidy();

		StringWriter writer = new StringWriter();
		servlet.doHelloWorld(writer);
		
		tidy.setXmlTags(true);
		tidy.setQuiet(true);
		String output = writer.toString();
		tidy.parse(new ByteArrayInputStream(output.getBytes("utf-8")), null);
		assertEquals("errori nel xhtml", 0, tidy.getParseErrors());
		assertEquals("warning nel xhtml", 0, tidy.getParseWarnings());
	}
}
