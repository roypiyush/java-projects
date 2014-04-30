package com.personal.test;

import java.math.BigInteger;

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

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		long l1 = 999983;
		
		System.out.println(l1 * l1);
		System.out.println(new BigInteger("999983").mod(new BigInteger("999983")));
		
		
		
		

	}

}
