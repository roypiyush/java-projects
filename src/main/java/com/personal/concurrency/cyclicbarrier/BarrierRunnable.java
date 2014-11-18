package com.personal.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;

public class BarrierRunnable implements Runnable {

	BarrierObject barrierObject;
	
	public BarrierRunnable(BarrierObject barrierObject) {
		this.barrierObject = barrierObject;
	}
	
	@Override
	public void run() {
		try {
			barrierObject.perform(Thread.currentThread().getName());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
