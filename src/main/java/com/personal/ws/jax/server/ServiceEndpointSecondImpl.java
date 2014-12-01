package com.personal.ws.jax.server;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService
@HandlerChain(file = "../handler/Handlers.xml")
public class ServiceEndpointSecondImpl implements ServiceEndpointInterface {

	@Resource
	private WebServiceContext mWSContext;

	static {
		System.out.println("Loading class1");
	}

	static {
		System.out.println("Loading class2");
	}

	{
		System.out.println("About to initialize constructor 1");
	}

	{
		System.out.println("About to initialize constructor 2");
	}

	public ServiceEndpointSecondImpl() {
		System.out.println("Constructor Called");
	}

	/**
	 * Initializes the web service.
	 */
	@PostConstruct
	@WebMethod(exclude = true)
	public void init() {
		System.out.println("Web service initialized");
	}

	@WebMethod
	public String getHelloWorldAsString(String name) {

		// Message Context can only be referred during request processing
		MessageContext messageContext = mWSContext.getMessageContext();
		for (String key : messageContext.keySet()) {
			System.out.println(String.format("Key= %s, Value=%s", key,
					messageContext.get(key)));
		}
		Object header = messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		System.out.println(header);

		return "Test String " + name;
	}

}
