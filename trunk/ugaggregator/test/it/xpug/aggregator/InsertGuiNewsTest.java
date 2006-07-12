package it.xpug.aggregator;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.Button;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import junit.framework.TestCase;

public class InsertGuiNewsTest extends TestCase {
	
	private ServletRunner runner;
	private WebResponse response;

	public InsertGuiNewsTest() {
		runner = new ServletRunner();
	}

	public void testTheJspShouldExist() throws Exception {
		
		int responseCode = response.getResponseCode();
		assertEquals(200, responseCode);
	}

	public void setUp() {
		ServletUnitClient sc = runner.newClient();
		WebRequest request = new PostMethodWebRequest("http://nonmiinteressa/jsp/insertnews.jsp");
		try {
			response = sc.getResponse(request);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public void testShouldContainAForm() throws Exception {
		WebForm[] forms = response.getForms();
		assertEquals(1, forms.length);
	}
	
	public void testShouldContainInputFields() throws Exception {
		HTMLElement newsTitle = response.getElementWithID("newsTitle");
		HTMLElement expirationDate = response.getElementWithID("expirationDate");
		HTMLElement content = response.getElementWithID("content");
		Button[] buttons = response.getForms()[0].getButtons();
		assertNotNull(newsTitle);
		assertNotNull(expirationDate);
		assertNotNull(content);
		System.out.println("Nome del bottone: '"+buttons[0].getName()+"', id del bottone '"+buttons[0].getID()+"'.");
		System.out.println(response.getText());
		assertEquals("There should be two buttons on page",	2, buttons.length);
	}
	
	public void testShouldHaveAFormWithInsertNewsAction() throws Exception {
		String action = response.getForms()[0].getAction();
		assertEquals("insertNews", action);
	}
	
	
}
