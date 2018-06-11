package com.personal.old.designpatterns.mediator;

import java.util.Observable;
import java.util.Observer;

public class BookSearchEvent extends Observable {

	public BookSearchEvent() {
		super();
		System.out.println("Throwing book search event.");
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		setChanged();
	}
}
