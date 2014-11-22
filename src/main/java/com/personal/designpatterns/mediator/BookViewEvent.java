package com.personal.designpatterns.mediator;

import java.util.Observable;
import java.util.Observer;

public class BookViewEvent extends Observable {

	public BookViewEvent() {
		super();
		System.out.println("Throwing book view event.");
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		setChanged();
	}
}
