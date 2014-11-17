package com.personal.designpatterns.vistitor;

public class CarElementDoVisitor implements Visitor {
	
	public CarElementDoVisitor() {
		System.out.println("\nCarElementDoVisitor is performing");
	}
	
    private void visit(Wheel wheel) {
        System.out.println("Kicking my " + wheel.getName() + " wheel");
    }
 
    private void visit(Engine engine) {
        System.out.println("Starting my engine");
    }
 
    private void visit(Body body) {
        System.out.println("Moving my body");
    }
 
    private void visit(Car car) {
        System.out.println("Starting my car");
    }

	@Override
	public void visit(ICarElement carElement) {
		
		if(carElement instanceof Wheel) {
			visit((Wheel)carElement);
		} else if(carElement instanceof Engine) {
			visit((Engine)carElement);
		} else if (carElement instanceof Body) {
			visit((Body)carElement);
		} else if (carElement instanceof Car) {
			visit((Car)carElement);
		} else {
			throw new UnsupportedOperationException(carElement.getClass().getSimpleName() + " Not yet supported!");
		}
		
	}

}
