package com.personal.ws.jax.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MySoapHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("Inside handlerMessage method");
		SOAPMessage message = context.getMessage();
		SOAPPart soapPart = message.getSOAPPart();
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			java.io.StringWriter sw = new java.io.StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(new DOMSource(soapPart.getDocumentElement()),
					sr);
			String string = sr.getWriter().toString();
			System.out.println(string);
			if (string.contains("my.server.jax.ws.personal.com")) {
				return false;
			}
		} catch (TransformerFactoryConfigurationError e){
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("Inside handlerFault method");
		return false;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Inside close method");

	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("Inside getHeaders method");
		return null;
	}

}
