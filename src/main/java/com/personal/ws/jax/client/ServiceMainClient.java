package com.personal.ws.jax.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class ServiceMainClient {

	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws MalformedURLException,
			ServiceException, RemoteException, InterruptedException {

		long startTime = System.currentTimeMillis();

		int threadCount = 10000;
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

		Thread[] threadArray = new Thread[threadCount];
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i] = new Thread(runnable[i]);

			threadArray[i].start();

		}

		// This code will make main thread to wait until all threads are done
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i].join();
		}

		long endTime = System.currentTimeMillis();

		long timeDiff = endTime - startTime;

		System.out.println(String.format("Time difference %dms", timeDiff));

	}

}
