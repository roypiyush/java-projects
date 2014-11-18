package com.personal.concurrency.producer_consumer;

public class ProducerConsumerMain {

	public static void main(String[] args) throws InterruptedException {

		ModelData modelData = new ModelData();

		Thread t1 = new Thread(new Producer(modelData));
		Thread t2 = new Thread(new Consumer(modelData));

		t2.start();
		t1.start();
		
	}

}
