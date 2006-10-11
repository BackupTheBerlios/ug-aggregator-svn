import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;


public class DoGet {

	protected int status;
	protected String uri;
	protected HttpMethod request;

	protected String content;
	
	public DoGet(String uri) {
		this.uri = uri;	
	}

	public void fetch() throws Exception {
		HttpClient client = new HttpClient();
		request = new GetMethod(uri);
		status = client.executeMethod(request);
		content = _content();
	}

	public void process() throws Exception { }

	public void end() throws Exception {
		request.releaseConnection();
	}

	public DoGet execute() throws Exception {
		try {
			fetch();
			process();

		} finally {
			end();

		}
		return this;
	}

	public Document document() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		return factory.newDocumentBuilder()
			.parse(new ByteArrayInputStream(content().getBytes()));
	}

	public String content() {
		return content;
	}

	public String header(String name) {
		Header header = request.getResponseHeader(name);
		if (header == null)
			return "";
		return header.getValue();
	}

	protected String _content() throws Exception {
		String line = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(request.getResponseBodyAsStream()));
		while ((line = reader.readLine()) != null)
			buffer.append(line).append("\n");
		return buffer.toString().trim();
	}

}
