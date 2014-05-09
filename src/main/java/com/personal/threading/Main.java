package com.personal.threading;

import com.personal.threading.model.DataModel;
import com.personal.threading.thread.ConsumerThread;
import com.personal.threading.thread.ProducerThread;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataModel model = new DataModel();

		Thread producer = new Thread(new ProducerThread(model), "Producer");
		producer.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread consumer = new Thread(new ConsumerThread(model), "Consumer");
		consumer.start();

	}

}
