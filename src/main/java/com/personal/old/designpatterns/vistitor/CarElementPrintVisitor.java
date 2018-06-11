package com.personal.old.designpatterns.vistitor;

public class CarElementPrintVisitor implements Visitor {

	public CarElementPrintVisitor() {
		System.out.println("\nCarElementPrintVisitor is performing");
	}
	

	@Override
	public void visit(ICarElement carElement) {
		System.out.println("Visiting " + (carElement instanceof Wheel ? ((Wheel)carElement).getName() + " " : "") + carElement.getClass().getSimpleName());
	}

}
