package com.personal.ws.jax.client;

import javax.xml.namespace.QName;



public class WebServiceRunnable implements Runnable {

	private String wsdlDocumentLocation;
	private QName serviceName;
	private QName portName;
	private String webserviceParam;

	public WebServiceRunnable(String wsdlDocumentLocation, QName serviceName,
			QName portName, String webserviceParam) {
		this.wsdlDocumentLocation = wsdlDocumentLocation;
		this.serviceName = serviceName;
		this.portName = portName;
		this.webserviceParam = webserviceParam;
	}

	public void run() {

//		try {
//			javax.xml.ws.Service service = javax.xml.ws.Service.create(new URL(
//					wsdlDocumentLocation), serviceName);
//			ServiceEndpointImpl endpointImpl = (ServiceEndpointImpl) service
//					.getPort(portName, ServiceEndpointImpl.class);
//
//			// Printing result
//			String result = endpointImpl.getHelloWorldAsString(webserviceParam);
//			if (result != null && result.endsWith(webserviceParam))
//				System.out.println(result);
//			else
//				System.err.println(result);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
	}

}
