package com.personal.designpatterns.bridge;

public abstract class ShapeCreator {

	private DrawingApi drawingApi;
	
	public ShapeCreator(DrawingApi drawingApi) {
		this.drawingApi = drawingApi;
	}
	
	public abstract void createShape();

	public DrawingApi getDrawingApi() {
		return drawingApi;
	}
	
}
