package com.personal.tricks;


public class HeapDumpMain {

	public static void heapError() {
		throw new OutOfMemoryError("Test");
	}
	
	public static void main(String[] args) {

		heapError();
	}

}
