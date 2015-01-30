package com.personal.tricks;

public class StringMain {

	public static void main(String[] args) {
		
		String s1 = "Hi";
		String s2 = new String("Hi");
		String s3 = "Hi";
		
		System.out.println(s1 == s2 ? "Should not reached here" : "s1 and s2 are different objects.");
		System.out.println(s1 == s3 ? "s1 and s3 are from String Pool." : "Should not reached here");
		

	}

}
