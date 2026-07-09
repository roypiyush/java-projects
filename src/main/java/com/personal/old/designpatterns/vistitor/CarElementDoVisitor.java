package com.personal.old.designpatterns.vistitor;

public class CarElementDoVisitor implements IVisitor {
	
	public CarElementDoVisitor() {
		System.out.println("CarElementDoVisitor is working");
	}
	
    public void visit(Wheel wheel) {
        System.out.println("Kicking my " + wheel.getName() + " wheel");
    }

    public void visit(Engine engine) {
        System.out.println("Starting my engine by calling Engine Method");
    }
 
    public void visit(Body body) {
        System.out.println("Moving my body by calling Body Method");
    }
 
    public void visit(Car car) {
        System.out.println("Starting my car by calling Car Method");
    }

}
