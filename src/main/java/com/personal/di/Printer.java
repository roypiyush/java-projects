package com.personal.di;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface Printer {
	public void print(String name);
}
