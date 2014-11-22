package com.personal.designpatterns.mediator;

public class ConcreteMediator implements Mediator {

	private BookView bookView;
	private BookSearch bookSearch;
	
	public void throwEvent(BookViewEvent bookViewEvent) {
		if(bookView == null) {
			System.out.println("Cannot thow event with unregistered observer");
			return;
		}
		bookViewEvent.addObserver(bookView);
		bookViewEvent.notifyObservers("yahoo!");
	}
	
	public void throwEvent(BookSearchEvent bookSearchEvent) {
		if(bookSearchEvent == null) {
			System.out.println("Cannot thow event with unregistered observer");
			return;
		}
		bookSearchEvent.addObserver(bookSearch);
		bookSearchEvent.notifyObservers("Book6");
	}
	
	@Override
	public void register(BookView bookView) {
		this.bookView = bookView;
	}

	@Override
	public void register(BookSearch bookSearch) {
		this.bookSearch = bookSearch;
	}

	@Override
	public void viewBook() {
		bookView.view();
	}

	@Override
	public void searchBook(String bookName) {
		bookSearch.search(bookName);
	}

}
