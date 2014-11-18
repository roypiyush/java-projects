package com.personal.concurrency.latch;

import java.util.concurrent.CountDownLatch;

//don't let them run yet
// let all threads proceed
// wait for all to finish
class Worker implements Runnable {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
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
		System.out.println("Doing my work.");

	}
}

public class CountdownLatchMain {

	void mainRunner() throws InterruptedException {
		
		int N = 7;
		
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		doSomethingElse();
		startSignal.countDown();
		doSomethingElse();
		doneSignal.await();
	}

	private void doSomethingElse() {
		System.out.println("Doing something else.");

	}

	public static void main(String[] args) throws InterruptedException {
		
		new CountdownLatchMain().mainRunner();

	}

}
