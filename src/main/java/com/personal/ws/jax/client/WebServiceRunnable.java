package com.personal.ws.jax.client;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.ParameterMode;

@SuppressWarnings("unused")
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

	private static String endpoint = "http://localhost:8910/hello";
	private static String qnameService = "ServiceEndpointImplementationService";
	private static String qnamePort = "ServiceEndpointImplPort";

	private static String BODY_NAMESPACE_VALUE = "http://server.jax.ws.personal.com/";
	private static String ENCODING_STYLE_PROPERTY = "javax.xml.rpc.encodingstyle.namespace.uri";
	private static String NS_XSD = "http://www.w3.org/2001/XMLSchema";
	private static String URI_ENCODING = "http://schemas.xmlsoap.org/soap/encoding/";

	public void run() {

		try {

			ServiceFactory factory = ServiceFactory.newInstance();
			Service service = factory.createService(new QName(qnameService));

			QName port = new QName(qnamePort);

			Call call = service.createCall(port);
			call.setTargetEndpointAddress(endpoint);

			call.setProperty(Call.SOAPACTION_USE_PROPERTY, new Boolean(true));
			call.setProperty(Call.SOAPACTION_URI_PROPERTY, "");
			call.setProperty(ENCODING_STYLE_PROPERTY, URI_ENCODING);

			QName QNAME_TYPE_STRING = new QName(NS_XSD, "string");
			call.setReturnType(QNAME_TYPE_STRING);

			call.setOperationName(new QName(BODY_NAMESPACE_VALUE,
					"getHelloWorldAsString"));
			call.addParameter("String_1", QNAME_TYPE_STRING, ParameterMode.IN);
			String[] params = { "Duke!" };

			Object invoke = call.invoke(params);
			String result = (String) invoke;
			System.out.println(result);

			// Printing result
//			String result = endpointImpl.getHelloWorldAsString(webserviceParam);
//			if (result != null && result.endsWith(webserviceParam))
//				System.out.println(result);
//			else
//				System.err.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
