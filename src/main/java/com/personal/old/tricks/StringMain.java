package com.personal.old.tricks;

public class StringMain {

	public static void main(String[] args) {
		
		String s1 = "Hi";
		String s2 = new String("Hi");
		String s3 = "Hi";
		String s4 = new String("Hi");
		
		System.out.printf("s1 hashcode = %d\n", s1.hashCode());
		System.out.printf("s2 hashcode = %d\n", s2.hashCode());
		System.out.printf("s3 hashcode = %d\n", s3.hashCode());
		System.out.printf("s4 hashcode = %d\n", s4.hashCode());
		
		System.out.println(s1 == s2 ? "Should not reached here" : "s1 (in pool) and s2 (in heap) are different objects.");
		System.out.println(s1 == s3 ? "s1 and s3 are from String Pool." : "Should not reached here");
		System.out.println(s2 == s4 ? "Belong to same heap location " : "s2 and s4 are different objects in heap.");
		

	}

}
