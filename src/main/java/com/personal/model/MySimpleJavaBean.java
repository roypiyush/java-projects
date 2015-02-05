package com.personal.model;


public class MySimpleJavaBean implements Cloneable {
	
	
	private String name;
	private String value;
	
	 /*
	 * Default no-arg constructor required for Serialization
	 */
	public MySimpleJavaBean() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	@Override
	public String toString() {
		return name + " " + value + " " + hashCode();
	}
}
