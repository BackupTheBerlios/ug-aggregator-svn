import org.w3c.dom.*;
import org.apache.xpath.*;


public class ProcessDocument {

	protected Document document;
	protected NodeList selection = null;
	
	public ProcessDocument(String uri) throws Exception {
		this(new DoGet(uri).execute().document());
	}

	public ProcessDocument(Document doc) {
		document = doc;
	}

	public void all(Document document) throws Exception { }

	public void selection(NodeList list) throws Exception { }

	public void each(Node current) throws Exception { }

	public void select(String xpath) throws Exception {
		selection = evalAsNodeList(document, xpath);
	}

	public ProcessDocument execute() throws Exception {
		all(document);
		if (selectedNodes())
			processSelectedNodes();
		return this;
	}

	public int evalAsInteger(Node root, String xpath) throws Exception {
		return (int) XPathAPI.eval(root, xpath).num();
	}

	public boolean evalAsBoolean(Node root, String xpath) throws Exception {
		return XPathAPI.eval(root, xpath).bool();
	}

	public String evalAsString(Node root, String xpath) throws Exception {
		return XPathAPI.eval(root, xpath).str();
	}

	public NodeList evalAsNodeList(Node root, String xpath) throws Exception {
		return XPathAPI.selectNodeList(root, xpath);
	}

	private boolean selectedNodes() {
		return selection != null;
	}

	private void processSelectedNodes() throws Exception {
		selection(selection);
		for (int i=0; i<selection.getLength(); i++) {
			each(selection.item(i));
		}
	}
}
