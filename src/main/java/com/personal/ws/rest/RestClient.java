package com.personal.ws.rest;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;

public class RestClient {

	private static String URI_TEMPLATE = "http://localhost:9090/service";

	public static void main(String[] args) throws Exception {

		int count = 11000;
		HttpClient httpClient = new HttpClient();
		httpClient.setFollowRedirects(false);
		
		httpClient.setMaxRequestsQueuedPerDestination(count);
		httpClient.start();

		for (int i = 0; i < count; i++) {
			try {

				httpClient.newRequest(URI_TEMPLATE).send(new BufferingResponseListener(8 * 1024)
		        {
		            @Override
		            public void onComplete(Result result)
		            {
		                    byte[] responseContent = getContent();
		                    System.out.println("Response " + new String(responseContent));
		            }
		        });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
