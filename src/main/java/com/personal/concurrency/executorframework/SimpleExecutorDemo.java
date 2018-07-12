package com.personal.concurrency.executorframework;

import com.personal.old.ws.jax.server.DataProcessor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

public class SimpleExecutorDemo {

	
	public static void main(String[] args) {
		
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
		DataProcessor dataProcessor = new DataProcessor("My Sample Data");
		FutureTask<String> task = new FutureTask<String>(dataProcessor);
		// You can cast to executor
		executorService.execute(task);
		String result;
		try {
			result = task.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();

	}

}
