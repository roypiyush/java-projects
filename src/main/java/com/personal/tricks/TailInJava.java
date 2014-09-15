package com.personal.tricks;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TailInJava {

	public static void main(String[] args) {
		FileInputStream fis = null;
		BufferedInputStream stream = null;
		try {
			fis = new FileInputStream("/tmp/Output");
			stream = new BufferedInputStream(fis);

			while (true) {
				int b;
				while ((b = stream.read()) != -1) {
					System.out.print((char)b);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null)
				fis.close();
				
				if(stream != null)
					stream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}