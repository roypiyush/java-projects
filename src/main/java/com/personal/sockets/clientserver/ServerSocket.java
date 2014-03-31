package com.personal.sockets.clientserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket socket = null;
		java.net.ServerSocket serverSocket = null;
		try {
			serverSocket = new java.net.ServerSocket(9999, 2);

			socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();

			StringBuffer buffer = readData(inputStream);

			System.out.println(buffer.toString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	private static StringBuffer readData(InputStream inputStream)
			throws IOException {
		byte[] b;

		StringBuffer buffer = new StringBuffer("");

		while (inputStream.read(b = new byte[2]) != -1) {
			// System.out.println(new String(b));
			String str = new String(b);
			buffer.append(str);
		}
		return buffer;
	}

}
