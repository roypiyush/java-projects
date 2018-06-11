package com.personal.old.designpatterns.mediator;

import java.util.Observable;
import java.util.Observer;

public class BookView implements Observer {

	private Mediator mediator;
	
	public BookView(Mediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void update(Observable observable, Object resultParam) {
		if(observable instanceof BookViewEvent) {
			System.out.println("Handling book view event with parameter: " + resultParam);
			mediator.viewBook();
		}
	}

	public void view() {
		String[] books = {"Book1", "Book2", "Book3", "Book4", "Book5",};
		for (int i = 0; i < books.length; i++) {
			System.out.println("Listing book: " + books[i]);
		}
		
	}

}
