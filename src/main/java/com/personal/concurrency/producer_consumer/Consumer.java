package com.personal.concurrency.producer_consumer;

public class Consumer implements Runnable {

	private ModelData modelData;

	public Consumer(ModelData modelData) {
		this.modelData = modelData;
	}

	public void run() {

		while (true) {

			modelData.consume(); 

		}

	}

}
