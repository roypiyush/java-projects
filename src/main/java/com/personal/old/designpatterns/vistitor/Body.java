package com.personal.old.designpatterns.vistitor;

public class Body implements ICarElement {
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
