/**
 * 
 */
package com.personal.tricks;

interface RequestProcessor<R> {
	public void processRequest();

	public R getResult();
}

// abstract class AbstractRequestProcessorImpl<R> implements RequestProcessor<R>
// {
//
// private R result;
//
// public R getResult() {
// return result;
// }
//
// }

class RequestHandler<R> implements Runnable {

	private RequestProcessor<R> processor;

	public RequestHandler(RequestProcessor<R> processor) {
		this.processor = processor;
	}

	public void run() {
		processor.processRequest();
	}
}

public class TestCollectionsAndGenericsMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
