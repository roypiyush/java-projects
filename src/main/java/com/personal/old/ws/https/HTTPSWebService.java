package com.personal.old.ws.https;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

@SuppressWarnings("restriction")
public class HTTPSWebService {

	public static void main(String[] args) {

		httpsEndpointPublish();

	}

	@SuppressWarnings("unused")
	private static void httpsEndpointPublish() {
		HttpsServer server;
		try {
			server = HttpsServer.create(new InetSocketAddress(9999), 1);

			SSLContext context = SSLContext.getInstance("TLS");

			char[] passphrase = "piyush".toCharArray();
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(
					"/home/piyush/workspace/java-keytool/piyush"), passphrase);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, passphrase);

			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			tmf.init(ks);

			SSLContext.setDefault(context);

			context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			HttpsConfigurator httpsConfigurator = new HttpsConfigurator(context) {
				public void configure(HttpsParameters params) {
					// get the remote address if needed
					InetSocketAddress remote = params.getClientAddress();

					SSLContext c = getSSLContext();

					// get the default parameters
					SSLParameters sslparams = c.getDefaultSSLParameters();

					params.setSSLParameters(sslparams);
					// statement above could throw IAE if any params invalid.
					// eg. if app has a UI and parameters supplied by a user.

				}
			};

			server.setHttpsConfigurator(httpsConfigurator);

			// Create context to publish
			HttpContext httpContext = server.createContext("/myweb");

			Endpoint endpoint = Endpoint.create(new SimpleWebServiceImpl());
			endpoint.publish(httpContext);

			server.setExecutor(null); // creates a default executor

			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void httpEndpointPublish() {
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(9999), 1);

			HttpContext context = server.createContext("/myweb");

			Endpoint endpoint = Endpoint.create(new SimpleWebServiceImpl());

			endpoint.publish(context);

			server.setExecutor(null); // creates a default executor

			server.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
