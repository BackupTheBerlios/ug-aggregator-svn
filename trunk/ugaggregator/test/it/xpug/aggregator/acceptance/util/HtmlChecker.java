package it.xpug.aggregator.acceptance.util;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.*;

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
    
	public boolean existsElementWithClass(String classTag) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		return getNumberOfElementsWithClass(classTag) > 0;
	}


	public boolean existsElementWithClassAndContent(String classTag, String contentTag) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		String xpath = "//*[@class='" + classTag + "'][contains(., '" + contentTag.replaceAll("'", "&#27;") + "')]";
		NodeList nodelist = findAll(xpath);
		return nodelist.getLength() > 0;
	}

	public int getNumberOfElementsWithClass(String classTag) throws TransformerException {
		String xpath = "//*[@class='" + classTag + "']";
		return findAll(xpath).getLength();
	}

}
