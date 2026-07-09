package com.personal.concurrency.semaphore;

public class MainThread {

	public static void main(String[] args) {

		int nThreads = Runtime.getRuntime().availableProcessors();
		
		SharedObject sharedObject = new SharedObject();
		
		for (int i = 0; i < nThreads; i++) {
			new Thread(new MyRunnable(sharedObject)).start();
		}

	}

}
