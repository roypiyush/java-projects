package com.personal.di;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;

@Service(name="formatted-console")
@Singleton
public class FormattedConsolePrinter implements Printer {

	@Override
	public void print(String text) {
		System.out.println(String.format("******* %s *******",text));
	}

}
