package com.personal.concurrency.producer_consumer;

import java.util.concurrent.atomic.AtomicBoolean;

public class ModelData {
	
	private int data = 0;
	
	// Used as a purpose of GUARDED BLOCKS
	public static AtomicBoolean isProduced = new AtomicBoolean(false);
	
	public synchronized void produce()
	{
		
		if (isProduced.get()) {
			try {
				wait();
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
		
		data = (int)(Math.random() * 100);
		System.out.println("Produced : " + data);
		
		isProduced.set(true);
		notifyAll();
		
	}
	
	public synchronized void consume(){
		
		if (!isProduced.get()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		data = 0;
		System.out.println("Consumed : " + data);
		
		isProduced.set(false);
		notifyAll();
		
		
	}
	
	

}
