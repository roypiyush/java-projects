package com.personal.ws.jax.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8910);

			while (true) {
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(
						inputStream);
				int size = 1024;
				byte[] b = new byte[size];
				bufferedInputStream.read(b);
				String referent = new String(b);
				WeakReference<String> reference = new WeakReference<String>(
						referent);
				System.out.print(reference.get());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
