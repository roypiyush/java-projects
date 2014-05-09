package com.personal.threading.synchronization;

public class TablePrintRunnerThread implements Runnable {
	private TablePrinter printer;
	private int tableOf;

	public TablePrintRunnerThread(TablePrinter printer, int tableOf)
	// public TablePrintRunnerThread(TablePrinter printer)
	{
		this.printer = printer;
		this.tableOf = tableOf;
	}

	public void run() {
		synchronized (printer) {
			printer.setTableOf(tableOf);
			printer.printTable();
		}

	}

}
