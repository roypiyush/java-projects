package com.personal.old.designpatterns.builder;

import com.personal.old.designpatterns.vistitor.Body;
import com.personal.old.designpatterns.vistitor.Engine;
import com.personal.old.designpatterns.vistitor.Wheel;

public class PersonalCar {
	
	private Wheel[] wheels;
	private Body body;
	private Engine engine;
	
	public Wheel[] getWheels() {
		return wheels;
	}
	public void setWheels(Wheel[] wheels) {
		this.wheels = wheels;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void performCarOperation() {
		for (int i = 0; i < wheels.length; i++) {
			System.out.println("Calling " + wheels[i].getName());
		}
		System.out.println("Calling " + body.toString().substring(body.toString().lastIndexOf(".") + 1));
		System.out.println("Calling " + engine.toString().substring(engine.toString().lastIndexOf(".") + 1));
	}
	
}
