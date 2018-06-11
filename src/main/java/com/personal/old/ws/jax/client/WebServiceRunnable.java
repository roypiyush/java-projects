package com.personal.old.ws.jax.client;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class WebServiceRunnable implements Runnable {

	private String namespaceUri;
	private String endpoint;
	private String service;
	private String servicePort;
	private String operationName;
	private String prefix;
	private String parameterName;
	private String webserviceParam;

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getNamespaceUri() {
		return namespaceUri;
	}

	public void setNamespaceUri(String namespaceUri) {
		this.namespaceUri = namespaceUri;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	public String getWebserviceParam() {
		return webserviceParam;
	}

	public void setWebserviceParam(String webserviceParam) {
		this.webserviceParam = webserviceParam;
	}

	public void run() {

		try {

			String result = makeWSCall();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String makeWSCall() throws SOAPException {
		QName serviceQN = new QName(namespaceUri, service);
		QName portQN = new QName(namespaceUri, servicePort);

		Service service = Service.create(serviceQN);
		service.addPort(portQN, SOAPBinding.SOAP11HTTP_BINDING, endpoint);

		Dispatch<SOAPMessage> dispatch = service.createDispatch(portQN,
				SOAPMessage.class, Service.Mode.MESSAGE);

		SOAPMessage request = createRequestSoapMessage(operationName, prefix,
				parameterName, SOAPConstants.SOAP_1_1_PROTOCOL);

		/** Invoke the service endpoint. **/
		SOAPMessage response = dispatch.invoke(request);

		SOAPBody soapBody = response.getSOAPBody();
		return soapBody.getElementsByTagName("return").item(0).getTextContent();
	}

	/**
	 * Create SOAPMessage request.
	 * */
	private SOAPMessage createRequestSoapMessage(String operationName,
			String prefix, String parameterName, String soapProtocol)
			throws SOAPException {

		// compose a request message
		MessageFactory mf = MessageFactory.newInstance(soapProtocol);

		SOAPMessage request = mf.createMessage();
		SOAPPart part = request.getSOAPPart();

		// Obtain the SOAPEnvelope and header and body elements.
		SOAPEnvelope env = part.getEnvelope();
		SOAPBody body = env.getBody();

		SOAPElement operation = body.addChildElement(operationName, prefix,
				namespaceUri);
		SOAPElement value = operation.addChildElement(parameterName);
		value.addTextNode(webserviceParam);
		request.saveChanges();
		return request;
	}

}
