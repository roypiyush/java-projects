package com.personal.threading.thread;

import com.personal.threading.model.DataModel;

public abstract class AbstractRunner implements Runnable {

	protected DataModel model;

	public AbstractRunner(DataModel model) {
		this.model = model;
	}

}
