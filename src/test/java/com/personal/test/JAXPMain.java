package com.personal.test;

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
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

}

public class JAXPMain {

	public static void main(String[] args) {

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();

			File file = new File(
					"/home/piyush/SeamlessWorkspace/se-vt/se-vt-reports/src/main/reports/Finance/VT_BIR_1001.rptdesign");

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
