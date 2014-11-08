package com.personal.threading;

import java.util.Calendar;

class VolatileDemo {
	public volatile int value = 0;
}

class VolatileRunnable implements Runnable {
	private VolatileDemo volatileDemo;
	private long future;

	
	public VolatileRunnable(VolatileDemo volatileDemo, long future) {
		super();
		this.volatileDemo = volatileDemo;
		this.future = future;
	}


	@Override
	public void run() {
		while(Calendar.getInstance().getTimeInMillis() < future) {
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
		
		VolatileRunnable runnable = new VolatileRunnable(new VolatileDemo(), calendar.getTimeInMillis());
		
		Thread thread1 = new Thread(runnable, "Thread 1");
		Thread thread2 = new Thread(runnable, "Thread 2");
		Thread thread3 = new Thread(runnable, "Thread 3");
		Thread thread4 = new Thread(runnable, "Thread 4");
		Thread thread5 = new Thread(runnable, "Thread 5");
		Thread thread6 = new Thread(runnable, "Thread 6");
		Thread thread7 = new Thread(runnable, "Thread 7");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
		thread7.join();
	}

}
