package com.personal.designpatterns.builder;

import com.personal.designpatterns.vistitor.Body;
import com.personal.designpatterns.vistitor.Engine;
import com.personal.designpatterns.vistitor.Wheel;

public class CarBuilder<T> implements Builder{

	private PersonalCar personalCar;
	
	@Override
	public void build() {
		personalCar = new PersonalCar();
		Wheel[] wheels = { 
				new Wheel("Front Left"),
				new Wheel("Front Right"),
				new Wheel("Back Left"),
				new Wheel("Back Right")
		};
		Body body = new Body();
		Engine engine = new Engine();
		
		personalCar.setWheels(wheels);
		personalCar.setBody(body);
		personalCar.setEngine(engine);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getResult() {
		return (T) personalCar;
	}

}
