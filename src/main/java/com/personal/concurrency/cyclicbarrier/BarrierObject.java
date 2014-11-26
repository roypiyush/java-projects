package com.personal.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierObject {

	CyclicBarrier barrier = new CyclicBarrier(4);
	Random random = new Random();
	
	public void perform(String threadName) throws InterruptedException, BrokenBarrierException {
		int time = random.nextInt(8) + 8;
		
		System.out.println(threadName + " Performing action on this object");
		System.out.println(threadName + " I'm going to take rest for " + time + " secs.");
		Thread.sleep(time * 1000);
		System.out.println(threadName + " I just woke up. Let me resume my duty.");
		barrier.await();
		System.out.println(threadName + " Exiting!\n");
	}
}
