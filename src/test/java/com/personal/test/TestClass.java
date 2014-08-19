package com.personal.test;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class TestClass {

	public static void main(String[] args) {

		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedInputStream(System.in));

			int numberOfTestCases = sc.nextInt();
			int count = 0;
			
			while (count < numberOfTestCases) {
				String data = sc.nextLine().trim();
				int N = Integer.parseInt(data.split(" ")[0]);
				int K = Integer.parseInt(data.split(" ")[1]);
				
				String stringArray = sc.nextLine().trim();
				String[] array = stringArray.split(" ");
			}

		} catch (Exception e) {
			System.out
					.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}
