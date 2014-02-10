package com.personal.threading.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThreadClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TablePrinter printer = new TablePrinter();

		int threads = Runtime.getRuntime().availableProcessors();

		ExecutorService executor = Executors.newFixedThreadPool(threads);

		TablePrintRunnerThread[] runner = new TablePrintRunnerThread[threads];

		for (int i = 0; i < runner.length; i++) {
			runner[i] = new TablePrintRunnerThread(printer, i + 1);
		}

		for (int i = 0; i < runner.length; i++) {
			executor.execute(runner[i]);
		}

		executor.shutdown();

	}

}
