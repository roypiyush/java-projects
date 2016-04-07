/**
 * 
 */
package com.personal.ws.rest;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author piyush
 *
 */
@Path(value = "/service")
public class RestWebService {

	static AtomicInteger integer = new AtomicInteger(0);
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String process(@Context HttpServletRequest request) {
		request.getSession().setAttribute("myattribute", request.getSession().getId());
		return Thread.currentThread().getName() + " Request No. " + integer.incrementAndGet();
	}
	
}
