package com.personal.old.designpatterns.composite;

public class Ellipse implements Graphic {

	final private int number;

	public Ellipse(int number) {
		this.number = number;
	}
	@Override
	public void print() {
		System.out.printf("Ellipse %d\n", number);
	}

}
