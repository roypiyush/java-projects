package com.personal.test;

public class BitOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 67, b = 123;

		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(a ^ b);

		int shift = a << 3;
		System.out.println(shift + " : " + Integer.toBinaryString(shift)
				+ " : " + (7 >>> 1));
		System.out.println((2147483647 << 20) + " : "
				+ Integer.toBinaryString(2147483647 << 20));

	}

}
