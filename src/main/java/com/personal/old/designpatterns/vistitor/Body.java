package com.personal.old.designpatterns.vistitor;

public class Body implements ICarElement {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
