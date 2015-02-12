package com.personal.designpatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


enum SingletonEnum {
	INSTANCE;
	
	SingletonEnum () {
		StaticClass staticClass = new StaticClass();
		System.out.println("Static Class hashCode " + staticClass.hashCode());
		
		StaticClass staticClass1 = new StaticClass();
		System.out.println("Static Class 1 hashCode " + staticClass1.hashCode());
		
		InnerClass innerClass = new InnerClass();
		System.out.println("Inner Class hashCode " +innerClass.hashCode());
		
		InnerClass innerClass1 = new InnerClass();
		System.out.println("Inner Class 1 hashCode " +innerClass1.hashCode());
	}
	
	private int state = 0;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	static class StaticClass {
		public StaticClass() {
			
		}
		
	}
	
	class InnerClass {
		public InnerClass() {
			
		}
		
	}
}

public class EnumSingletonMain {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
		System.out.println("Singleton hashCode : " + SingletonEnum.INSTANCE);
		System.out.println("Singleton hashCode : " + SingletonEnum.INSTANCE.hashCode());
		System.out.println("Singleton hashCode : " + SingletonEnum.INSTANCE.hashCode());
		
		Class<?> class1 = Class.forName("com.personal.designpatterns.singleton.SingletonEnum");
		Constructor<?>[] constructors = class1.getConstructors();
		System.out.println("Constructors for Enum: " + constructors.length);
		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true);
			Object newInstance = constructor.newInstance();
			SingletonEnum enum1 = (SingletonEnum) newInstance;
			System.out.println("Singleton hashCode using reflexion : " + enum1.hashCode());
		}

	}

}
