package com.personal.test;

public class BitOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 67, b = 123;

		System.out.println(a + " = " + Integer.toBinaryString(a));
		System.out.println("a << 1  " + Integer.toBinaryString(a << 1) + " : " + (a << 1) );  
		System.out.println("a >> 1  " + Integer.toBinaryString(a >> 1) + " : " + (a >> 1) );
		System.out.println("b     = " + Integer.toBinaryString(b));
		System.out.println("a ^ b = " + (a ^ b));

		int shift = a << 1;
		System.out.println(shift + " : " + Integer.toBinaryString(a << 1));
		System.out.println(Integer.toBinaryString(7) + " 7 >>>1 " + (7 >>> 1));
		System.out.println((2147483647 << 20) + " : "
				+ Integer.toBinaryString(2147483647 << 20));

	}

}
