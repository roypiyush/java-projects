package com.personal.di;

import org.jvnet.hk2.annotations.Service;

@Service
public class FooImpl implements Foo {

	@Override
	public void print(String name) {
		System.out.println(name);
	}

}
