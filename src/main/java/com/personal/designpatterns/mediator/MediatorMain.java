package com.personal.designpatterns.mediator;

public class MediatorMain {

	public static void main(String[] args) {
		
		ConcreteMediator mediator = new ConcreteMediator();
		mediator.register(new BookView(mediator));
		mediator.register(new BookSearch(mediator));
		
		mediator.throwEvent(new BookViewEvent());
		mediator.throwEvent(new BookSearchEvent());

	}

}
