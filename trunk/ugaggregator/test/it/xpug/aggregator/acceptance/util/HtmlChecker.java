package it.xpug.aggregator.acceptance.util;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.*;

public class HtmlChecker {

	private String itsHtml;

	public HtmlChecker(String html) {
		itsHtml = html;
	}

	
    public static Document parseXmlFile(InputStream is) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		return factory.newDocumentBuilder().parse(is);
    }
    
	public boolean existsClass(String classTag) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		Document doc = parseXmlFile(new StringBufferInputStream(itsHtml));
		NodeList nodelist = org.apache.xpath.XPathAPI.selectNodeList(doc, "//*[@class='" + classTag + "']");
		
		return nodelist.getLength() > 0;
	}

}
