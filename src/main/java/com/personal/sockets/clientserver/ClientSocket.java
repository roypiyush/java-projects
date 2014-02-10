package com.personal.sockets.clientserver;

public class ClientSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SocketThread thread1 = new SocketThread("First");
		SocketThread thread2 = new SocketThread("Second");

		System.out.println(thread1.hashCode());
		System.out.println(thread2.hashCode());

		Thread t1 = new Thread(thread1);
		Thread t2 = new Thread(thread2);

		t1.start();
		t2.start();

	}

}
