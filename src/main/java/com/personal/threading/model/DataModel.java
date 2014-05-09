package com.personal.threading.model;

public class DataModel {

	private int data = 0;

	public Object producerLock = new Object();
	public Object consumerLock = new Object();

	public int getData() {
		return data;
	}

	public synchronized void setData(int data) {
		this.data = data;
	}

	public synchronized void produceData() {
		data++;
	}

	public synchronized void consumeData() {
		data--;
	}

}
