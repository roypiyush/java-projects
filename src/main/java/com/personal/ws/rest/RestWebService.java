/**
 * 
 */
package com.personal.ws.rest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

	static AtomicLong totalMillis = new AtomicLong(0);
	static AtomicInteger integer = new AtomicInteger(0);
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String process(@Context HttpServletRequest request) {
		long start = System.currentTimeMillis();
		String resultMessage = Thread.currentThread().getName() + " Request No. " + integer.incrementAndGet();
		request.getSession().setAttribute("myattribute", request.getSession().getId());
		long end = System.currentTimeMillis();
		
		long v = totalMillis.addAndGet(end - start);
		double d = v/integer.get();
		System.out.printf("Time take to execute: %dms, Avg: %f\n", (end - start), d);
		return resultMessage;
	}
	
}
