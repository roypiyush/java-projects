package com.personal.old.designpatterns.vistitor;

public class CarElementPrintVisitor implements IVisitor {

	public CarElementPrintVisitor() {
		System.out.println("CarElementPrintVisitor is printing");
	}

	public void visit(Wheel wheel) {
		System.out.println("Printing my " + wheel.getName() + " wheel");
	}

	public void visit(Engine engine) {
		System.out.println("Printing my engine by calling Engine Method");
	}

	public void visit(Body body) {
		System.out.println("Printing my body by calling Body Method");
	}

	public void visit(Car car) {
		System.out.println("Printing my car by calling Car Method");
	}
}
