package com.personal.concurrency.latch;

import java.util.concurrent.CountDownLatch;

//don't let them run yet
// let all threads proceed
// wait for all to finish
class Worker implements Runnable {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	private final long initialStartTime;
	
	Worker(CountDownLatch startSignal, CountDownLatch doneSignal, long initialStartTime) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.initialStartTime = initialStartTime;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} // return;
	}

	private void doWork() {
		System.out.printf("%s Doing my work at %d \n", Thread.currentThread().getName(), System.currentTimeMillis() - initialStartTime);

	}
}

public class CountdownLatchMain {

	void mainRunner(long initialStartTime) throws InterruptedException {
		
		int N = 7;
		
		CountDownLatch startSignal = new CountDownLatch(0);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal, initialStartTime)).start();

		doSomethingElse(initialStartTime);
		startSignal.countDown();
		doSomethingElse(initialStartTime);
		doneSignal.await();
		doSomethingElse(initialStartTime);
	}

	private void doSomethingElse(long initialStartTime) {
		System.out.printf("%s -> Doing something else at %d \n", Thread.currentThread().getName(), System.currentTimeMillis() - initialStartTime);

	}

	public static void main(String[] args) throws InterruptedException {
		System.out.printf("Hey, I'm %s\n" , Thread.currentThread().getName());
		new CountdownLatchMain().mainRunner(System.currentTimeMillis());

	}

}
