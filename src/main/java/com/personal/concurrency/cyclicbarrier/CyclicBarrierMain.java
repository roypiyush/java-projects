package com.personal.concurrency.cyclicbarrier;

public class CyclicBarrierMain {

	public static void main(String[] args) {

		int coreProcessorsCount = Runtime.getRuntime().availableProcessors();
		int nThreads = coreProcessorsCount * 2;

		BarrierObject barrierObject = new BarrierObject(coreProcessorsCount);

		for (int i = 0; i < nThreads; i++) {
			new Thread(new BarrierRunnable(barrierObject)).start();
		}
		
		System.out.println("Parties waiting: " + barrierObject.getBarrier().getParties());

	}

}
