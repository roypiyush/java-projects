package com.personal.designpatterns.bridge;

public class BridgeMain {

	public static void main(String[] args) {
		
		DrawingApi circleApi = new CircleDrawer();
		DrawingApi rectangleApi = new RectangleDrawer();
		
		ShapeCreator creator = new ConcreteShaperCreator(circleApi);
		creator.createShape();
		
		creator = new ConcreteShaperCreator(rectangleApi);
		creator.createShape();
	}

}
