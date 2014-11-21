package com.personal.embedded_jetty.soapforward;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MainRunner extends AbstractHandler {

	
	public void handle(String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		String publishUrlPattern = "/test";
		baseRequest.setContextPath(publishUrlPattern);
		
		int contentLength = baseRequest.getContentLength();
		byte[] data = new byte[contentLength];
		InputStream inputStream = baseRequest.getInputStream();
		// Read all of data at once
		inputStream.read(data);
		

        try {
			SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = factory.createConnection();
			
			URL client = new URL("http://localhost:1234/provisioninginterceptor/provisioninginterceptor");
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			MimeHeaders headers = new MimeHeaders();
			headers.addHeader("SOAPAction", "\"HSSV900R009C02SPC100#ADD_HHDAINF\"");
			SOAPMessage soapMessage = messageFactory.createMessage(headers, new ByteArrayInputStream(data));
			
			// Calling intended server
			SOAPMessage soapResponseMessage = connection.call(soapMessage, client);

			SOAPPart soapPart = soapResponseMessage.getSOAPPart();
			Element documentElement = soapPart.getDocumentElement();
			String prettyPrint = prettyPrint(documentElement.getOwnerDocument());
			System.out.println(prettyPrint);
			response.getWriter().println(prettyPrint);
			
			response.setContentType("text/xml;charset=utf-8");
	        response.setStatus(HttpServletResponse.SC_OK);
	        baseRequest.setHandled(true);
	        
		} catch (UnsupportedOperationException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} catch (SOAPException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
	
	public static String prettyPrint(Document document) {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			Writer out = new StringWriter();
			transformer.transform(new DOMSource(document),
					new StreamResult(out));
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
        server.setHandler(new MainRunner());
  
        server.start();
        server.join();
	}

}
