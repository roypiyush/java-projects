package com.personal.old.designpatterns.observer;

public class ObserverDesignMain {

	public static void main(String[] args) {
		
        // create an event source - reads from stdin
        final EventSource eventSource = new EventSource();
 
        // create an observer
        final ResponseHandler responseHandler = new ResponseHandler();
 
        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler);
 
        // starts the event thread
        Thread thread = new Thread(eventSource);
        thread.start();

	}

}
