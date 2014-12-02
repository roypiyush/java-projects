package com.personal.ws.jax.server;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jws.WebService;

@WebService
public class ServiceEndpointImplementation implements ServiceEndpointInterface {

	public ServiceEndpointImplementation() {
	}
	
	private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(50,
			50, 50, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public String getHelloWorldAsString(String param) {

		DataProcessor dataProcessor = new DataProcessor(param);
		Future<String> future = poolExecutor.submit(dataProcessor);

		String result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return result;
	}

}
