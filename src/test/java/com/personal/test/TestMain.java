package com.personal.test;

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

		new TestMain();

		// Runtime runtime = Runtime.getRuntime();
		// runtime.addShutdownHook(new Thread(){
		// @Override
		// public void run() {
		// System.out.println("Running Shutdown hook");
		// }
		// });
		//
		// System.out.println("Running main");

		String s1 = "Piyush";
		String s2 = new String("Piyush");

		System.out.println(s1 == s2);

		System.out.println(s1.hashCode() + " : " + s2.hashCode());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
