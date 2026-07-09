package com.personal.old.designpatterns.vistitor;


public interface IVisitor {

	// Each of below methods can go to its corresponding interface
	void visit(Car car);
	void visit(Wheel wheel);
	void visit(Body body);
	void visit(Engine engine);
}
