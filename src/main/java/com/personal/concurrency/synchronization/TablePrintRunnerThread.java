package com.personal.concurrency.synchronization;

public class TablePrintRunnerThread implements Runnable {
	private TablePrinter printer;
	private int tableOf;

	public TablePrintRunnerThread(TablePrinter printer, int tableOf) {
		this.printer = printer;
		this.tableOf = tableOf;
	}

	public void run() {
		try {
			/*
			 *  Check out previous revision for synchronized keyword usage
			 *  
			 *  Using below locking policy you can easily allow threads to 
			 *  run in order in which they visit
			 */
			
			printer.lock.lock();
			printer.setTableOf(tableOf);
			printer.printTable();
		} finally {
			printer.lock.unlock();
		}
	}

}
