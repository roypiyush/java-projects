package com.personal.designpatterns.builder;

public interface Builder {
	
	public void build();
	public <T> T getResult();

}
