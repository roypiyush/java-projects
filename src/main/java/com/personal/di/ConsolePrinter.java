package com.personal.di;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;

@Service(name="simple-console")
@Singleton
public class ConsolePrinter implements Printer {

	@Override
	public void print(String name) {
		System.out.println(name);
	}

}
