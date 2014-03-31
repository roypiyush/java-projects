package com.personal.threading.ipc;

public class ModelData {
	
	private int data = 0;
	
	// Used as a purpose of GUARDED BLOCKS
	public static boolean isProduced = false;
	
	public synchronized void produce()
	{
		
		if (isProduced) {
			try {
				wait();
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
		
		data = (int)(Math.random() * 100);
		System.out.println("Produced : " + data);
		
		isProduced = true;
		notifyAll();
		
	}
	
	public synchronized void consume(){
		
		if (!isProduced) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		data = 0;
		System.out.println("Consumed : " + data);
		
		isProduced = false;
		notifyAll();
		
		
	}
	
	

}
