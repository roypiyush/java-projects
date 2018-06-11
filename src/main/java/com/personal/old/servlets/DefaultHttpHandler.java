package com.personal.old.servlets;

import com.personal.old.servlets.util.Read;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("restriction")
public class DefaultHttpHandler implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {

		try {

			InputStream is = t.getRequestBody();
			Read.show(is); // .. read the request body

			Headers headers = t.getRequestHeaders();

			Set<Entry<String, List<String>>> entrySet = headers.entrySet();

			for (Entry<String, List<String>> entry : entrySet) {

				List<String> values = entry.getValue();
				for (String string : values) {
					System.out.println(entry.getKey() + " ==> " + string);
				}
			}

			 sendStringResponse(t);
			// sendFile(t);
//			fetchExternalLink(t);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void sendStringResponse(HttpExchange t) throws IOException {
		String response = "<head><style>"
				+ "body {background-color:lightgray} "
				+ "h1   {color:blue} "
				+ "p    {color:lightgreen}"
				+ "</style></head>"
				+ "<body>"
				+ "<h1>This is a heading</h1>"
				+ "<p>This is a paragraph.</p>"
				+ "</body>";
		System.out.println(t.getRequestMethod());
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	@SuppressWarnings("resource")
	void sendFile(HttpExchange t) throws IOException {

		File file = new File("/home/piyush/Documents/Piyush_Roy_Resume.pdf");

		Headers h = t.getResponseHeaders();
		h.add("Content-Type", "application/pdf");
		// h.add( "Content-Disposition", "attachment;filename=" +
		// file.getName());

		byte[] bytearray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bis.read(bytearray, 0, bytearray.length);

		// ok, we are ready to send the response.
		t.sendResponseHeaders(200, file.length());
		OutputStream os = t.getResponseBody();
		os.write(bytearray, 0, bytearray.length);
		os.close();
	}

	void fetchExternalLink(HttpExchange t) throws IOException {
		URL hp = new URL("https://www.google.co.in");
		URLConnection hpCon = hp.openConnection();
		InputStream input = hpCon.getInputStream();

		int length = input.available();
		byte[] data = new byte[length];

		input.read(data);
		input.close();

		t.sendResponseHeaders(200, length);
		OutputStream os = t.getResponseBody();
		os.write(data);
		os.close();
	}
}
