package com.personal.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierObject {

	private CyclicBarrier barrier;

	Random random = new Random();

	public BarrierObject(int barrierCount) {
		barrier = new CyclicBarrier(barrierCount);
	}

	public void perform(String threadName) throws InterruptedException, BrokenBarrierException {
		int time = random.nextInt(60);

		System.out.println(threadName + " Performing action on this object");
		System.out.println(threadName + " I'm going to take rest for " + time + " secs.");
		Thread.sleep(time * 1000);
		System.out.println(threadName + " I just woke up. Let me resume my duty.");
		System.out.println(threadName + " Is barrier broken " + (barrier.isBroken() ? "Yes" : "No"));
		barrier.await();
		System.out.println(threadName + " Is barrier broken after await() " + (barrier.isBroken() ? "Yes" : "No"));
		System.out.println(threadName + " Exiting!\n");
	}
	
	public CyclicBarrier getBarrier() {
		return barrier;
	}
}
