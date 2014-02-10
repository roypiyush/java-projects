package com.personal.threading.ipc;

public class IPCMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataModel model = new DataModel();

		Thread producer = new Thread(new Producer(model));
		producer.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;

		Thread consumer = new Thread(new Consumer(model));
		consumer.start();

	}

}
