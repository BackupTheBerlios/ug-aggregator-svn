package essap.sample.acceptance.lib;

import java.io.File;
import java.util.Arrays;

import junit.framework.TestCase;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import com.nutrun.xhtml.validator.XhtmlValidator;

public class AcceptanceTestCase extends TestCase {

	private ServletUnitClient client;
	private WebResponse response;
	private WebForm[] forms;
	
	protected void setUp() throws Exception {
		super.setUp();
		ServletRunner sr = new ServletRunner(new File("conf/web.xml"));
        client = sr.newClient();       
	}
	
	public void testTruth() throws Exception {}
	
	protected void get(String url) throws Exception {
		response = client.getResponse("http://localhost" + url);
		forms = response.getForms();
	}
	
	protected void assertExpectedContent(String content) throws Exception {
		assertTrue("content <" + content + "> not found", response.getText().contains(content));
	}
	
	protected void assertExpectedTitle(String title) throws Exception {
		assertEquals(title, response.getTitle());
	}
	
	protected void assertExpectedFormControl(String name, String value) throws Exception {
		assertTrue("no forms found", forms.length > 0);
		WebForm form = forms[0]; 
		assertTrue("control " + name + " not found", Arrays.asList(form.getParameterNames()).contains(name));
		assertEquals(value, form.getParameterValue(name));
	}

	protected void assertValidXhtml() throws Exception {
		XhtmlValidator validator = new XhtmlValidator();
		boolean isValid = validator.isValid(response.getInputStream());
		String errors = Arrays.asList(validator.getErrors()).toString();
		assertTrue(errors, isValid);
	}

	protected void submit() throws Exception {
		forms[0].submit();
	}

	protected void setFormControl(String name, String value) {
		forms[0].setParameter(name, value);
	}
}
