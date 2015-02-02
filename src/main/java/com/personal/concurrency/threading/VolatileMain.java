package com.personal.concurrency.threading;

import java.util.Calendar;

class VolatileDemo {
	public volatile int value = 0;
}

class VolatileRunnable implements Runnable {
	private VolatileDemo volatileDemo;
	
	public VolatileRunnable(VolatileDemo volatileDemo) {
		super();
		this.volatileDemo = volatileDemo;
	}


	@Override
	public void run() {
		
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " Put: " + (++volatileDemo.value));
		}
	}
	
}
public class VolatileMain {

	public static void main(String[] args) throws InterruptedException {
		
		Calendar calendar = Calendar.getInstance();
		
		int sec = calendar.get(Calendar.SECOND);
		calendar.set(Calendar.SECOND, sec + 1);
		calendar.getTimeInMillis();
		
		VolatileDemo volatileVariable = new VolatileDemo();
		VolatileRunnable runnable = new VolatileRunnable(volatileVariable);
		
		int nThreads = Runtime.getRuntime().availableProcessors();
		int factor = 1000;
		
		Thread thread[] = new Thread[nThreads * factor];
		for (int i = 0; i < nThreads * factor; i++) {
			thread[i] = new Thread(runnable, "Thread " + (i + 1));
			thread[i].start();
		}
		
		for (int i = 0; i < nThreads * factor; i++) {
			thread[i].join();
		}
		
		System.out.printf("Expected : %d, Actual : %d \n", nThreads * factor * 10, volatileVariable.value);
		System.out.println("Reached End of Program");
	}

}
