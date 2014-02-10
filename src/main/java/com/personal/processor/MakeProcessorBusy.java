package com.personal.processor;

import java.util.Date;

public class MakeProcessorBusy {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Date date = new Date(2013, 9, 16, 18, 38);
		
		long timeToStop = date.getTime();
				
		int processors = Runtime.getRuntime().availableProcessors();
		
		for (int i = 0; i < processors; i++) {
			ProcessorBusyThread runner1 = new ProcessorBusyThread(timeToStop );
			Thread t1 = new Thread(runner1);
			t1.start();
		}
		
				

	}

}
