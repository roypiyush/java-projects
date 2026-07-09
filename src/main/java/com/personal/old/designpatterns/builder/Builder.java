package com.personal.old.designpatterns.builder;

public interface Builder {
	
	public void build();
	public <T> T getResult();

}
