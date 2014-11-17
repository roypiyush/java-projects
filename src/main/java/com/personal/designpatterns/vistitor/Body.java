package com.personal.designpatterns.vistitor;

public class Body implements ICarElement {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
