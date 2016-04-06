/**
 * 
 */
package com.personal.ws.rest;

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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String process(@Context HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		return Thread.currentThread().getName();
	}
	
}
