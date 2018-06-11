package com.personal.old.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class WhoisDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int c;
		try {
			Socket s = new Socket("google.com", 80);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			String str = (args.length == 0 ? "google.com" : args[0]) + "\n";
			byte buf[] = str.getBytes();
			out.write(buf);
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
