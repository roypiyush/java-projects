package com.personal.threading.ipc;

public class Consumer implements Runnable {

	private DataModel model;

	public Consumer(DataModel model) {
		this.model = model;
	}

	public void run() {
		synchronized (model) {
			while (model.getData() < 10) {
				System.err.println("Consumed : " + model.getData());
				model.notify();
				try {
					model.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
