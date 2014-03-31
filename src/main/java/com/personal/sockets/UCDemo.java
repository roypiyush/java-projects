package com.personal.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UCDemo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int c;
		URL hp = new URL("https://www.google.com");
		URLConnection hpCon = hp.openConnection();
		// get date
		long d = hpCon.getDate();
		if (d == 0)
			System.out.println("No date information.");
		else
			System.out.println("Date: " + new Date(d));
		// get content type
		System.out.println("Content-Type: " + hpCon.getContentType());
		// get expiration date
		d = hpCon.getExpiration();
		if (d == 0)

			System.out.println("No expiration information.");
		else
			System.out.println("Expires: " + new Date(d));
		// get last-modified date
		d = hpCon.getLastModified();
		if (d == 0)
			System.out.println("No last-modified information.");
		else
			System.out.println("Last-Modified: " + new Date(d));
		// get content length
		int len = hpCon.getContentLength();
		if (len == -1)
			System.out.println("Content length unavailable.");
		else
			System.out.println("Content-Length: " + len);
		if (len != 0) {
			System.out.println("=== Content ===");
			try {
				InputStream input = hpCon.getInputStream();
				// int i = len;
				while (((c = input.read()) != -1)) { // && (--i > 0)) {
					System.out.print((char) c);
				}
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No content available.");
		}

	}

}
