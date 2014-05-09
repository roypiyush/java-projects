package com.personal.servlets;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ServletPublisherMain {

	public static void main(String[] args) {

		try {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(
					"localhost", 9000), 500);

			HttpContext context = httpServer.createContext("/test");
			context.setHandler(new DefaultHttpHandler());

			httpServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
