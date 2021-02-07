package com.personal.old.designpatterns.vistitor;

public class VisitorMain {

	public static void main(String[] args) {
		ICarElement car = new Car();
        car.accept(new CarElementPrintVisitor());
		System.out.println();
        car.accept(new CarElementDoVisitor());
	}
}
