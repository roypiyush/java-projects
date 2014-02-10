package com.personal.ws.jax.server;

import java.util.concurrent.Callable;

public class DataProcessor implements
// Runnable,
		Callable<String> {

	private String result = null;

	private String param;

	// private Thread thread;

	public DataProcessor(String param) {
		this.param = param;
		// thread = new Thread(this);
		// thread.start();
	}

	public void run() {
		perform();
	}

	private void perform() {
		result = "Hello World JAX-WS " + param;
	}

	public String call() {
		perform();
		return result;
	}

	public String getResult() {
		// while(thread.isAlive()) ;

		return result;
	}

}
