package com.personal.tricks;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JDOMMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();

		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();

			File file = new File("src/main/resources/Bookstore.xml");

			Document document = documentBuilder.parse(file);

			Element element = document.getDocumentElement();
			printDocument(element, "");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	static void printDocument(Element element, String indentation) {
		String nodeName = element.getNodeName();
		System.out.println(indentation + "<" + nodeName + ">");
		if (element.hasChildNodes()) {

			NodeList childNodes = element.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if (node.getNodeType() == Element.ELEMENT_NODE)
					printDocument((Element) node, indentation + " ");
				else if(node.getNodeType() == Element.TEXT_NODE) {
					if(!node.getTextContent().trim().isEmpty())
						System.out.println(indentation + " " + node.getTextContent().trim());
				}
			}
		}
		System.out.println(indentation + "</" + nodeName + ">");
	}

}
