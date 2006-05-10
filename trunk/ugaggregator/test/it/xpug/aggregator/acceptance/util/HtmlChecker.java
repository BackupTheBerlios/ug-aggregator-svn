package it.xpug.aggregator.acceptance.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HtmlChecker {

	private Document dom;

	public HtmlChecker(String html) throws SAXException, IOException, ParserConfigurationException {
		dom = parseXmlFile(new StringBufferInputStream(html));
	}

	private NodeList findAll(String xpath) throws TransformerException {
		return org.apache.xpath.XPathAPI.selectNodeList(dom, xpath);
	}
	
    public static Document parseXmlFile(InputStream is) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		return factory.newDocumentBuilder().parse(is);
    }
    
	public boolean existsElementWithClass(String elementClass) throws Exception {
		return getNumberOfElementsWithClass(elementClass) > 0;
	}

	public NodeList getElementsWithClass(String elementClass) throws Exception {
		String xpath = "//*"+classFilter(elementClass);
		return findAll(xpath);
	}
	
	public List getElementContents(String elementClass) throws Exception {
		List list = new ArrayList();
		NodeList nodes = getElementsWithClass(elementClass);
		for (int i=0; i<nodes.getLength(); i++) {
			list.add(nodes.item(i).getFirstChild().getNodeValue());
		}
		return list;
	}

	public int getNumberOfElementsWithClass(String elementClass) throws Exception {
		return getElementsWithClass(elementClass).getLength();
	}

	private String classFilter(String classTag) {
		return "[@class='" + classTag + "']";
	}

	public boolean existsElementWithClassAndContent(String elementClass, String value) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		String xpath = "//*"+classFilter(elementClass) + valueFilter(value);
		NodeList nodelist = findAll(xpath);
		return nodelist.getLength() > 0;
	}
	
	public boolean existsElementWithClassAndContent(String ancestorClass, String elementClass, String value) throws Exception {
		String xpath = "//*" + classFilter(ancestorClass) + "//*" + classFilter(elementClass) + valueFilter(value);
		NodeList nodelist = findAll(xpath);
		return nodelist.getLength() > 0;
	}

	private String valueFilter(String value) {
		return "[contains(., '" + value.replaceAll("'", "&#27;") + "')]";
	}
}
