package com.personal.ws.jax.client;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class WebServiceRunnable implements Runnable {

	private String webserviceParam;

	public WebServiceRunnable(String webserviceParam) {
		this.webserviceParam = webserviceParam;
	}

	private static String endpoint = "http://localhost:8910/hello";
	private static String qnameService = "ServiceEndpointImplementationService";
	private static String qnamePort = "ServiceEndpointImplPort";

	private static String NAMESPACE_URI = "http://server.jax.ws.personal.com/";

	public void run() {

		try {

			QName serviceName = new QName(NAMESPACE_URI, qnameService);
			QName portName = new QName(NAMESPACE_URI, qnamePort);

			Service service = Service.create(serviceName);
			service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpoint);

			Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
					SOAPMessage.class, Service.Mode.MESSAGE);

			/** Create SOAPMessage request. **/
			// compose a request message
			MessageFactory mf = MessageFactory
					.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);

			SOAPMessage request = mf.createMessage();
			SOAPPart part = request.getSOAPPart();

			// Obtain the SOAPEnvelope and header and body elements.
			SOAPEnvelope env = part.getEnvelope();
			SOAPBody body = env.getBody();

			String operationName = "getHelloWorldAsString";
			String prefix = "ns";
			String parameterName = "arg0";

			SOAPElement operation = body.addChildElement(operationName, prefix,
					NAMESPACE_URI);
			SOAPElement value = operation.addChildElement(parameterName);
			value.addTextNode(webserviceParam);
			request.saveChanges();

			/** Invoke the service endpoint. **/
			SOAPMessage response = dispatch.invoke(request);
			SOAPBody soapBody = response.getSOAPBody();
			System.out.println(soapBody.getElementsByTagName("return").item(0)
					.getTextContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
