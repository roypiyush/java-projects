package com.personal.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class SharedObject {

	Semaphore semaphore;
	
	public SharedObject() {
		semaphore = new Semaphore(2);
	}
	
	public void perform(String string) throws InterruptedException {
		System.out.println(string + " Performing task on shared resource.");
	}
}
