package com.personal.designpatterns.vistitor;

public class Car implements ICarElement {
    ICarElement[] elements;
 
    /**
     * This constructor internally create ICarElements and stores them in array.
     */
    public Car() {
        //create new Array of elements
        this.elements = new ICarElement[] { new Wheel("front left"),
            new Wheel("front right"), new Wheel("back left") ,
            new Wheel("back right"), new Body(), new Engine() };
    }
 
    /**
     * {@inheritDoc}
     * 
     * Accepts the visitor and calls it internally
     */
    public void accept(Visitor visitor) {    
        for(ICarElement elem : elements) {
            elem.accept(visitor);
        }
        visitor.visit(this);    
    }

}
