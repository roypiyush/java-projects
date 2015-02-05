package com.personal.serialization;

public class NonSerializableSuperClass {
	
	private String internalField;
	
	public NonSerializableSuperClass() {
		super();
	}
	
	/*
	 * If only this constructor is present then will throw InvalidClassException
	 */
	public NonSerializableSuperClass(String test) {
		super();
	}

	public String getInternalField() {
		return internalField;
	}

	public void setInternalField(String internalField) {
		this.internalField = internalField;
	}
	
	

}
