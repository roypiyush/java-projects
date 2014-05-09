package com.personal.threading.thread;

import com.personal.threading.model.DataModel;

public class ConsumerThread extends AbstractRunner {

	public ConsumerThread(DataModel model) {
		super(model);
	}

	public void run() {

		synchronized (model) {

			for (int i = 1; i <= 5; i++) {

				try {
					model.wait(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				model.consumeData();
				System.out.println("Consumed : " + model.getData());
				model.notifyAll();
			}

		}

	}

}
