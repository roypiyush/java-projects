package com.personal.designpatterns.bridge;

public class ConcreteShaperCreator extends ShapeCreator {

	
	public ConcreteShaperCreator(DrawingApi drawingApi) {
		super(drawingApi);
	}

	@Override
	public void createShape() {
		getDrawingApi().draw();
	}

}
