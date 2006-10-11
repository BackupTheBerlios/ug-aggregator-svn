import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;


public class DoPost extends DoGet {

	private Map parameters;

	public DoPost(String uri) {
		super(uri);
		parameters = new HashMap();
	}
	
	public void fetch() throws Exception {
		HttpClient client = new HttpClient();
		request = new PostMethod(uri);
		Iterator it = parameters.keySet().iterator();
		while (it.hasNext()) {
			String key = (String)it.next();
			String value = (String)parameters.get(key);
			((PostMethod)request).addParameter(key, value);
		}
		status = client.executeMethod(request);
		content = _content();
	}

	public void prepare() throws Exception { }

	public void parameter(String name, String value) throws Exception {
		parameters.put(name, value);
	}

	public DoGet execute() throws Exception {
		try {
			prepare();
			super.execute();

		} finally {
			end();

		}
		return this;
	}
}
