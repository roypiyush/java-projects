package com.personal.old.servlets.util;

import java.io.IOException;
import java.io.InputStream;

public class Read {
	public static void show(InputStream is) {

		byte[] b;
		try {
			while (is.read(b = new byte[512]) > -1) {
				System.out.print(new String(b));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}