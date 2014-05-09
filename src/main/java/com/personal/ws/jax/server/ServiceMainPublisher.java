package com.personal.ws.jax.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ServiceMainPublisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 500, 50,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100),
				new ThreadPoolExecutor.CallerRunsPolicy());

		Endpoint endpoint = Endpoint
				.create(new ServiceEndpointImplementation());

		Endpoint endpoint2 = Endpoint.create(new ServiceEndpointSecondImpl());

		HttpServer httpServer;
		try {
			httpServer = HttpServer.create(new InetSocketAddress("localhost",
					8910), 500);

			HttpContext context = httpServer.createContext("/hello");
			HttpContext context2 = httpServer.createContext("/hi");

			httpServer.setExecutor(poolExecutor);

			endpoint.publish(context);
			endpoint2.publish(context2);

			httpServer.start();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
