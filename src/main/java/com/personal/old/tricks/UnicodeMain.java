package com.personal.old.tricks;

import java.io.UnsupportedEncodingException;

public class UnicodeMain {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		int limit = 65535;
		for (int i = 0; i <= limit; i++) {

			String number = Integer.toHexString(i);

			String unicode = "\\u" + number;

			// System.out.println(unicode);

			System.out
					.println(new String(unicode.getBytes("UNICODE"), "UTF-8"));
		}

	}

}
