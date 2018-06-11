package com.personal.old.sockets.clientserver.binarydata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class BinaryServerSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			java.net.ServerSocket serverSocket = new java.net.ServerSocket(9999);

			Socket socket = serverSocket.accept();

			InputStream inputStream = socket.getInputStream();

			byte[] dataBytes = readData(inputStream);

			inputStream.close();

			writeData(dataBytes);

			socket.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void writeData(byte[] dataBytes) {

		try {
			FileOutputStream fileOutputStream = new FileOutputStream("Test.png");
			fileOutputStream.write(dataBytes);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
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
