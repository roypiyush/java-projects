package com.personal.ws.jax.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;



//import com.personal.ws.jax.server.ServiceEndpointImpl;

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

		String wsdlDocumentLocation = "http://localhost:8910/hello?wsdl";

		QName serviceName = new QName("http://server.jax.ws.personal.com/",
				"ServiceEndpointImplementationService");
		QName portName = new QName("http://server.jax.ws.personal.com/",
				"ServiceEndpointImplPort");

		int threadCount = 500;
		WebServiceRunnable[] runnable = new WebServiceRunnable[threadCount];

		for (int i = 0; i < runnable.length; i++) {
			String param = "param_" + i;
			runnable[i] = createRunnableInstance(param, wsdlDocumentLocation,
					serviceName, portName);
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

		System.out.println(String.format("%d  %s", timeDiff,
				new Float(timeDiff) / 1000));

	}

	private static WebServiceRunnable createRunnableInstance(
			String webserviceParam, String wsdlDocumentLocation,
			QName serviceName, QName portName) {

		WebServiceRunnable runnable1 = new WebServiceRunnable(
				wsdlDocumentLocation, serviceName, portName, webserviceParam);
		return runnable1;
	}

	static void test() throws MalformedURLException {
		URL wsdlDocumentLocation = new URL("http://localhost:8910/hello?wsdl");
		QName serviceName = new QName("http://server.jax.ws.personal.com/",
				"ServiceEndpointImplementationService");

		javax.xml.ws.Service service = javax.xml.ws.Service.create(
				wsdlDocumentLocation, serviceName);

		QName portName = new QName("http://server.jax.ws.personal.com/",
				"ServiceEndpointImplPort");

//		ServiceEndpointImpl endpointImpl = (ServiceEndpointImpl) service
//				.getPort(portName, ServiceEndpointImpl.class);
//		System.out.println(endpointImpl.getHelloWorldAsString("Piyush"));
	}
}
