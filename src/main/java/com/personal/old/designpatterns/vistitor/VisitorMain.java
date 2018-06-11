package com.personal.old.designpatterns.vistitor;

public class VisitorMain {

	public static void main(String[] args) {
		ICarElement car = new Car();
        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
	}

}
