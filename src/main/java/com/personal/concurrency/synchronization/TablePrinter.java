package com.personal.concurrency.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TablePrinter {

	private int tableOf;

	Lock lock = new ReentrantLock(false);
	
	public int getTableOf() {
		return tableOf;
	}

	public void setTableOf(int tableOf) {
		this.tableOf = tableOf;
	}

	public void printTable() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(String.format("%d X %d = %d", tableOf, i, i
					* tableOf));
		}
		System.out.println();
	}

}
