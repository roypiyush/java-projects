package com.personal.ws.jax.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="http://server.jax.ws.personal.com/")
@SOAPBinding(style = Style.DOCUMENT)
public interface ServiceEndpointInterface {

	@WebMethod
	@WebResult(name = "result")
	public String getHelloWorldAsString(@WebParam(name = "name", targetNamespace = "http://server.jax.ws.personal.com/", partName = "name", mode=Mode.IN) String name);

}