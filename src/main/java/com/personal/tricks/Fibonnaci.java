package com.personal.tricks;

import java.math.BigInteger;

public class Fibonnaci {

	public static void main(String[] args) {
		
		
		BigInteger fib = new BigInteger("1");
		BigInteger prev = new BigInteger("1");
		
		while (fib.compareTo(new BigInteger("1000000000000000000")) < 0) {
			BigInteger t = fib;
			
			// Generate next fib
			fib = prev.add(fib);
			prev = t;
			

			// Finding factor of Fib
			System.out.println(fib.toString());
			
		}
		
	}

}
