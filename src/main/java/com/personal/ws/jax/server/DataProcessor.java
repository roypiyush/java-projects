package com.personal.ws.jax.server;

import java.util.concurrent.Callable;

public class DataProcessor implements Callable<String> {

	private String result = null;

	private String param;

	public DataProcessor(String param) {
		this.param = param;
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
		return result;
	}

}
