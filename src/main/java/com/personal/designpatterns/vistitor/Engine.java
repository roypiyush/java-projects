package com.personal.designpatterns.vistitor;

public class Engine implements ICarElement {
	
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
