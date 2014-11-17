package com.personal.designpatterns.bridge;

public class RectangleDrawer implements DrawingApi {

	@Override
	public void draw() {
		System.out.println("Drawing Rectangle.");
	}

}
