package com.personal.test;

import java.io.IOException;
import java.io.InputStream;

public class CommandExecutionMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("ls");

			InputStream inputStream = process.getInputStream();

			byte[] b;
			while (inputStream.read(b = new byte[10]) != -1)
				System.out.print(new String(b));

			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
