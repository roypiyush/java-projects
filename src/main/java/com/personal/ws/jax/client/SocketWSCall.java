package com.personal.ws.jax.client;

public class SocketWSCall {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		int threadCount = 3;
		WebServiceRunnable[] runnable = new WebServiceRunnable[threadCount];

		for (int i = 0; i < runnable.length; i++) {
			String param = "param_" + i;
			runnable[i] = new WebServiceRunnable();
			runnable[i].setEndpoint("http://localhost:8910/hello");
			runnable[i].setNamespaceUri("http://server.jax.ws.personal.com/");
			runnable[i].setServicePort("ServiceEndpointImplPort");
			runnable[i].setService("ServiceEndpointImplementationService");
			runnable[i].setOperationName("getHelloWorldAsString");
			runnable[i].setPrefix("ns");
			runnable[i].setParameterName("arg0");
			runnable[i].setWebserviceParam(param);
		}

		for (int i = 0; i < runnable.length; i++) {
			runnable[i].run();

		}

		long endTime = System.currentTimeMillis();

		long timeDiff = endTime - startTime;

		System.out.println(String.format("Time difference %dms", timeDiff));

	}

}
