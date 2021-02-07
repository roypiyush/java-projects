package com.personal.old.designpatterns.vistitor;

public class Engine implements ICarElement {
	
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
