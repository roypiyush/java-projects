package com.personal.ws.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RestClient {

	private static String URI_TEMPLATE = "http://localhost:9090/service";

	public static void main(String[] args) {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		final WebTarget target = client.target(getBaseURI());

		Thread[] threads = new Thread[1];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {

					long time = System.currentTimeMillis();
					Response response = target.request(
							MediaType.APPLICATION_JSON_TYPE).get();
					System.out.println(String.format(
							"Status: %s  Length:%s  Data:%s Time:%sms",
							response.getStatus(), response.getLength(),
							response.readEntity(String.class), (System.currentTimeMillis() - time)));
				}

			});
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		System.out.printf("Time take to start all threads %dms \n", System.currentTimeMillis() - start);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(URI_TEMPLATE).build();
	}
}
