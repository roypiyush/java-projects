package com.personal.designpatterns.mediator;

import java.util.Observable;
import java.util.Observer;

public class BookSearch  implements Observer {

	private Mediator mediator;
	
	public BookSearch(Mediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void update(Observable observable, Object resultParam) {
		if(observable instanceof BookSearchEvent) {
			System.out.println("Handling book search event with parameter: " + resultParam);
			mediator.searchBook(resultParam.toString());
		}
	}

	public void search(String bookName) {
		System.out.println("Performing a search on " + bookName);
	}

}
