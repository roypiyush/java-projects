package com.personal.concurrency.semaphore;

public class MyRunnable implements Runnable {

	private SharedObject sharedObject;

	public MyRunnable(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
	}

	@Override
	public void run() {
		try {

			sharedObject.semaphore.acquire();
			sharedObject.perform(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName()
					+ " I wanna go to sleep\n");
			Thread.sleep(3000);
			sharedObject.semaphore.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
