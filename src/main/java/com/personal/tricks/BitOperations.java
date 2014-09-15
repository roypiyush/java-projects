package com.personal.tricks;

public class BitOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 67, b = 123;

		System.out.println(a + "    = " + Integer.toBinaryString(a));
		System.out.println(b + "   = " + Integer.toBinaryString(b));
		System.out.println("a ^ b = " + Integer.toBinaryString((a ^ b)) + ": " + (a ^ b));
		
		System.out.println("a << 1  " + Integer.toBinaryString(a << 1) + " : " + (a << 1) );  
		System.out.println("a >> 1  " + Integer.toBinaryString(a >> 2) + " : " + (a >> 2) );
		
		

		int shift = a << 1;
		System.out.println(shift + " : " + Integer.toBinaryString(a << 1));
		System.out.println(Integer.toBinaryString(a) + " a>>>1 means " + Integer.toBinaryString(a >>> 2) + " : "+ (a >>> 2));
		System.out.println((2147483647 << 20) + " : "
				+ Integer.toBinaryString(2147483647 << 20));

	}

}
