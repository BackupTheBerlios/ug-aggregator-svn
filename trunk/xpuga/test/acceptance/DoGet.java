import java.io.*;

import org.w3c.dom.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import javax.xml.parsers.*;


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
			return this;

		}
	}

	public Document document() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		return factory.newDocumentBuilder()
			.parse(new ByteArrayInputStream(content.getBytes()));
	}

	public String content() {
		return content;
	}

	private String _content() throws Exception {
		String line = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(request.getResponseBodyAsStream()));
		while ((line = reader.readLine()) != null)
			buffer.append(line).append("\n");
		return buffer.toString().trim();
	}

}
