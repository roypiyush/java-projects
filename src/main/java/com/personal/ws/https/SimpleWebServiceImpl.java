package com.personal.ws.https;

import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService
public class SimpleWebServiceImpl {

	@Resource
	WebServiceContext context;

	@SuppressWarnings("unused")
	public String helper() {
		String name = "Piyush";
		String str = "Roy";

		MessageContext messageContext = context.getMessageContext();

		Set<Entry<String, Object>> entrySet = messageContext.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			// System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		return String.format("name: %s | str: %s", name, str);
	}
}
