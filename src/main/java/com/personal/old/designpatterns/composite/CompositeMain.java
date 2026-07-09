package com.personal.old.designpatterns.composite;

public class CompositeMain {
	
	public static void main(String[] args) {
        //Initialize four ellipses
        Ellipse ellipse1 = new Ellipse(1);
        Ellipse ellipse2 = new Ellipse(2);
        Ellipse ellipse3 = new Ellipse(3);
        Ellipse ellipse4 = new Ellipse(4);
 
        //Initialize three composite graphics
        CompositeGraphic graphic = new CompositeGraphic();
        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();
 
        //Composes the graphics
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);
 
        graphic2.add(ellipse4);
 
        graphic.add(graphic1);
        graphic.add(graphic2);
 
        //Prints the complete graphic (four times the string "Ellipse").
        graphic.print();
    }

}
