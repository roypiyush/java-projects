package com.personal.old.tricks;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class XMLTagsEvent extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Found start of document.");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("Found end of document.");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		int totalAttr = attributes.getLength();
		for (int i = 0; i < totalAttr; i++) {
			System.out.println("Attribute " + attributes.getQName(i) + " " + attributes.getValue(i));
		}
		
		System.out.println(String.format("Start of element-> uri: %s, localName: %s, qName: %s", uri, localName, qName));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		System.out.println(String.format("End of element-> uri: %s, localName: %s, qName: %s", uri, localName, qName));
	}

}

public class JAXPMain {

	public static void main(String[] args) {

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();

			File file = new File(
					"src/main/resources/Bookstore.xml");

			saxParser.parse(file, new XMLTagsEvent());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
