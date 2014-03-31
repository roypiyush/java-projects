package com.personal.test;

import java.io.UnsupportedEncodingException;

public class ReflectionMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {

			Class<?> class1 = Class.forName("com.personal.test.UnicodeMain");
			System.out.println(class1.getCanonicalName());

			Object object = class1.newInstance();
			UnicodeMain Uni = (UnicodeMain) object;
			Uni.main(null);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
