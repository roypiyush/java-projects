package com.personal.sockets.clientserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketThread implements Runnable {

	private String name;

	public SocketThread(String name) {
		this.name = name;
	}

	public void run() {
		
		Socket socket = null;
		
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9999);

			OutputStream outputStream = socket.getOutputStream();

			System.out.println(String.format("[%s] isBound : %s", name,
					socket.isBound()));
			System.out.println(String.format("[%s] isConnected : %s", name,
					socket.isConnected()));

			String data = String.format(
					"Hello There, I[%s] send you some data", name);

			outputStream.write(data.getBytes());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}
