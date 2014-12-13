package com.personal.concurrency.cyclicbarrier;

public class CyclicBarrierMain {

	public static void main(String[] args) {

		int nThreads = Runtime.getRuntime().availableProcessors();

		BarrierObject barrierObject = new BarrierObject();

		for (int i = 0; i < nThreads; i++) {
			new Thread(new BarrierRunnable(barrierObject)).start();
		}
		
		System.out.println("Parties waiting: " + barrierObject.getBarrier().getParties());

	}

}
