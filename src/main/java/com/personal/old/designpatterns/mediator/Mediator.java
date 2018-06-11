package com.personal.old.designpatterns.mediator;

public interface Mediator {

	void register(BookView bookView);

	void register(BookSearch bookSearch);

	void viewBook();
	
	void searchBook(String bookName);
}
