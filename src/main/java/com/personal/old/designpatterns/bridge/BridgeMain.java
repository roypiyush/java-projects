package com.personal.old.designpatterns.bridge;

public class BridgeMain {

	public static void main(String[] args) throws InterruptedException {
		
		final DrawingApi circleApi = new CircleDrawer();
		final DrawingApi rectangleApi = new RectangleDrawer();
		
		Thread t1 = new Thread() {

			@Override
			public void run() {
				super.run(); // Thread is actually started at this point.
				ShapeCreator creator = new ConcreteShaperCreator(circleApi);
				creator.createShape();
			}
			
		};
		
		Thread t2 = new Thread() {

			@Override
			public void run() {
				super.run(); // Thread is actually started at this point.
				ShapeCreator creator = new ConcreteShaperCreator(rectangleApi);
				creator.createShape();
			}
			
		};
		
		t1.start();
		t2.start();
		
		System.out.println("Started Inner Threads");
		t1.join();
		t2.join();
		System.out.println("Inner Threads Completed Execution. Existing Main thread.");
		
		
	}

}
