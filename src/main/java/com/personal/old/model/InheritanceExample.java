package com.personal.old.model;

public class InheritanceExample {

	@SuppressWarnings("unused")
	private String variable1;
	String variable2;
	protected String variable3;
	public String variable4;
	
}


class InheritSuper extends InheritanceExample
{
	// No warning or message with all modifiers
	public String variable1;
	
	// No warning or message with all modifiers
	public String variable2;
	
	// No warning or message with all modifiers
	public String variable3;
	
	
	public String variable4;
}