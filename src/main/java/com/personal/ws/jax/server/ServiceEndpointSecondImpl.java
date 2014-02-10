package com.personal.ws.jax.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("restriction")
@WebService
public class ServiceEndpointSecondImpl implements ServiceEndpointInterface {

	@WebMethod
	public String getHelloWorldAsString(String name) {
		return "Test String " + name;
	}

}
