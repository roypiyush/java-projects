package com.personal.old.tricks;

public class TestMain {

	public TestMain() {
		System.out.println("Constructor called");
	}

	{
		System.out.println("Intance Initializer Block");
	}

	static {
		System.out.println("static Block");
	}

	static void method() {
		System.out.println("Static Method");
	}

	public void finalize() {
		System.out.println("Calling finalize");
	}

	private char defaultChar;
	public void printDefaultChar() {
		System.out.println("Printing default char: " + defaultChar);
	}
	
	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("Running from extra thread");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	Thread thread = new Thread(new MyRunnable());
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Inside Add Shutdown Hook");
			}
		});
		
		TestMain main = new TestMain();

		String s1 = "Piyush";
		String s2 = new String("Piyush");

		System.out.println(s1 == s2);
		System.out.println(s1.hashCode() + " : " + s2.hashCode());
		
		main.printDefaultChar();
		
		try {
			main.thread.start();  // Wait for this thread to die
			main.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myLabelDemo();
		
		timeDifferenceDemo();
	}

	@SuppressWarnings("unused")
	private static void myLabelDemo() {
		MyLabel : {
			int value = -11;
			System.out.println(Integer.toBinaryString(value));
			System.out.println(Integer.toBinaryString(value << 2));
			System.out.println(Integer.toBinaryString(value >> 2));
			System.out.println(Integer.toBinaryString(value >>> 2));
		}
	}
	
	
	public static void timeDifferenceDemo() {
		
		try {
			
			long diffTime = 10;
			
			long startTime = System.currentTimeMillis();
			Thread.sleep(diffTime);
			long endTime = System.currentTimeMillis();
			
			System.out.println(String.format("Delayed time by %d", endTime - startTime));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
