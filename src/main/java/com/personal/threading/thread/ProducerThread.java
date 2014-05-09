package com.personal.threading.thread;

import com.personal.threading.model.DataModel;

public class ProducerThread extends AbstractRunner {

	public ProducerThread(DataModel model) {
		super(model);

	}

	public void run() {

		for (int i = 1; i <= 5; i++) {

			synchronized (model) {

				model.produceData();

				System.out.println("Produced : " + model.getData());

				model.notifyAll();

				try {
					model.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
