package com.personal.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierMain {

	public static void main(String[] args) {

		int coreProcessorsCount = Runtime.getRuntime().availableProcessors();
		int nThreads = coreProcessorsCount * 2;

		BarrierObject barrierObject = new BarrierObject(coreProcessorsCount);

		for (int i = 0; i < nThreads; i++) {
			new Thread(() -> {
				try {
					barrierObject.perform(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
		
		System.out.println("Parties waiting: " + barrierObject.getBarrier().getParties());

	}

}
