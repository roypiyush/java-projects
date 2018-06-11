package com.personal.old.designpatterns.builder;

public class BuilderMain {

	public static void main(String[] args) {
		Builder builder = new CarBuilder<PersonalCar>();
		builder.build();
		PersonalCar personalCar = builder.getResult();
		personalCar.performCarOperation();
	}

}
