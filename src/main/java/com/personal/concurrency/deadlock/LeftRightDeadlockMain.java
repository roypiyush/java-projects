package com.personal.concurrency.deadlock;

public class LeftRightDeadlockMain {

	private final Object left = new Object();
	private final Object right = new Object();

	public void leftRight() {
		synchronized (left) {
			doSomething();
			synchronized (right) {
			}
		}
	}

	public void rightLeft() {
		synchronized (right) {
			synchronized (left) {
				doSomething();
			}
		}
	}

	private void doSomething() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Doing something.");
	}

	public static void main(String[] args) {
		
		final LeftRightDeadlockMain deadlockMain = new LeftRightDeadlockMain();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				deadlockMain.leftRight();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				deadlockMain.rightLeft();
			}
		});

		t1.start();
		t2.start();
	}

}
