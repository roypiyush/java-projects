package com.personal.ws.jax.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ServiceEndpointInterface {

	@WebMethod
	String getHelloWorldAsString(@WebParam(name = "param") String name);

}