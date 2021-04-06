package com.personal.old.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleHttpRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final String host = args[0];
			Socket s = new Socket(host, Integer.parseInt(args[1]));
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			String request = String.format("GET http://%s/ HTTP/1.1\n", host) +
					"User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n" +
					"Host: localhost\n" +
					"Accept-Language: en-us\n" +
					"Accept-Encoding: gzip, deflate\n" +
					"Connection: Keep-Alive\n\n";
			byte buf[] = request.getBytes();
			out.write(buf);
			byte readBuf[] = new byte[8192];
			int retryCount = 0;
			int MAX_RETRY  = 3;
			while (true) {
				if (in.available() > 0) {
					in.read(readBuf);
					System.out.print(new String(readBuf));
				} else {
					if (retryCount > MAX_RETRY) {
						break;
					} else {
						retryCount++;
						Thread.sleep(100);
					}
				}
			}
			s.close();
		} catch (UnknownHostException | InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
