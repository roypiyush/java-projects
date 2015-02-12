package com.personal.concurrency.timer;

import java.util.Timer;
import java.util.TimerTask;

class Task extends TimerTask {

	@Override
	public void run() {
		System.out.println("Hey, I am executing in timer task.");
	}
	
}

public class TimerMain {

	
	public static void main(String[] args) {
		
		// Creating timer thread
		Timer timer = new Timer();
		timer.schedule(new Task(), 1000);
		
		
	}
}
