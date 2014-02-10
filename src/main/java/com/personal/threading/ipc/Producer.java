package com.personal.threading.ipc;

public class Producer implements Runnable {

	private DataModel model;

	public Producer(DataModel model) {
		this.model = model;
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			synchronized (model) {
				model.setData(i);
				System.out.println("Produced : " + model.getData());
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
