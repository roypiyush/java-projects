package com.personal.threading.ipc;

public class Producer implements Runnable {

	private ModelData modelData;

	public Producer(ModelData modelData) {
		this.modelData = modelData;
	}

	public void run() {

		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			modelData.produce(); 

		}

	}

}
