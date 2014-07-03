package com.personal.ws.jax.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT)
// optional
public interface ServiceEndpointInterface {

	@WebMethod
	String getHelloWorldAsString(String name);

}