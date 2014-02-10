package com.personal.threading.synchronization;

public class TablePrinter {

	private int tableOf;

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
	}

}
