package com.personal.tricks;

import java.math.BigInteger;

public class Fibonnaci {

	/** function to generate nth fibonacci number **/
	public void genFib(long n) {
		BigInteger arr1[][] = { { BigInteger.ONE, BigInteger.ONE },
				{ BigInteger.ONE, BigInteger.ZERO } };
		if (n == 0)
			System.out.println("\nFirst Fibonacci number = 0");
		else {
			power(arr1, n - 1);
			System.out.println(n + 1 + "th Fibonacci number = "
					+ arr1[0][0]);
		}
	}

	/** function raise matrix to power n recursively **/
	private void power(BigInteger arr1[][], long n) {
		if (n == 0 || n == 1)
			return;
		BigInteger arr2[][] = { { BigInteger.ONE, BigInteger.ONE },
				{ BigInteger.ONE, BigInteger.ZERO } };
		power(arr1, n / 2);
		multiply(arr1, arr1);
		if (n % 2 != 0)
			multiply(arr1, arr2);
	}

	/** function to multiply two 2 d matrices **/
	private void multiply(BigInteger arr1[][], BigInteger arr2[][]) {
		BigInteger x = (arr1[0][0].multiply(arr2[0][0])).add(arr1[0][1]
				.multiply(arr2[1][0]));
		BigInteger y = (arr1[0][0].multiply(arr2[0][1])).add(arr1[0][1]
				.multiply(arr2[1][1]));
		BigInteger z = (arr1[1][0].multiply(arr2[0][0])).add(arr1[1][1]
				.multiply(arr2[1][0]));
		BigInteger w = (arr1[1][0].multiply(arr2[0][1])).add(arr1[1][1]
				.multiply(arr2[1][1]));
		arr1[0][0] = x;
		arr1[0][1] = y;
		arr1[1][0] = z;
		arr1[1][1] = w;
	}

	/** Main function **/
	public static void main(String[] args) {
		long n = 10;
		long start = System.currentTimeMillis();
		Fibonnaci fg = new Fibonnaci();
		fg.genFib(n - 1);
		System.out.printf("Program executed in %dms\n", (System.currentTimeMillis() - start));
	}

}
