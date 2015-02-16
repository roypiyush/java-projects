package com.personal.ws.jax.server;

import javax.jws.WebService;

@WebService
public class ServiceEndpointImplementation implements ServiceEndpointInterface {

	public ServiceEndpointImplementation() {
	}
	
	public String getHelloWorldAsString(String param) {
		DataProcessor dataProcessor = new DataProcessor(param);
		return dataProcessor.call();
	}

}
