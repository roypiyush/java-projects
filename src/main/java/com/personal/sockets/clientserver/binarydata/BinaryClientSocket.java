package com.personal.sockets.clientserver.binarydata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BinaryClientSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

			OutputStream outputStream = socket.getOutputStream();

			FileInputStream fileInputStream = new FileInputStream(
					"/home/piyush/Desktop/1.png");

			outputStream.write(readData(fileInputStream));

			fileInputStream.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static byte[] readData(InputStream inputStream) {

		byte[] b = null;
		try {

			b = new byte[inputStream.available()];

			// Read all the bytes
			inputStream.read(b);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

}
