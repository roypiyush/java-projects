package com.personal.old.processor;

public class ProcessorBusyThread implements Runnable {

	private long timeToStop;
	
	public ProcessorBusyThread(long timeToStop)
	{
		this.timeToStop = timeToStop;
	}
	
	public void run() {
		
		while(System.currentTimeMillis() < timeToStop)
		{
		}

	}

}
