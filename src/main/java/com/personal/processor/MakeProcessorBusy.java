package com.personal.processor;

import java.util.Calendar;
import java.util.Date;

public class MakeProcessorBusy {

	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.setTimeInMillis(date.getTime() + 10000);
		
		
		long timeToStop = calendar.getTimeInMillis();
				
		int processors = Runtime.getRuntime().availableProcessors();
		
		for (int i = 0; i < processors; i++) {
			ProcessorBusyThread runner1 = new ProcessorBusyThread(timeToStop );
			Thread t1 = new Thread(runner1);
			t1.start();
		}
		
				

	}

}
