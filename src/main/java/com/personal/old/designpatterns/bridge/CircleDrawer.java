package com.personal.old.designpatterns.bridge;

public class CircleDrawer implements DrawingApi {

	@Override
	public void draw() {
		System.out.println("Drawing Circle.");
	}

}
