package com.personal.ninja.fibonaccifactor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

	public long gcd(long number1, long number2) {
		long divisor ;
		long dividend ;
		
		if(number1 < number2) {
			divisor = number1;
			dividend = number2;
		}
		else {
			divisor = number2;
			dividend = number1;
		}
		
		while(divisor > 0) {
			long temp = divisor;
			divisor = dividend % divisor;
			dividend = temp;
		}
		
		return dividend;
	}
	
	private boolean primalityCheck(long n) {
		
		if(n == 2)
			return true;
		
		long v = (long)Math.sqrt(n) + 1;
		
		for (int i = 2; i <= v; i++) {
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public void process(long N) {

		long fib = 1L;
		long prev = 1L;

		while (fib < Long.MAX_VALUE) {
			long t = fib;

			// Generate next fib
			fib = prev + fib;
			prev = t;

			long value = gcd(fib, N);
			if(value > 1 && primalityCheck(value)) {
				System.out.println(fib + " " + value);
				break;
			}

		}
	}

	public static void main(String[] args) {

		Solution solution = new Solution();

		Scanner sc = null;
		BufferedInputStream stream = null;
		try {
			stream = new BufferedInputStream(System.in);
			sc = new Scanner(stream);

			int numberOfTestCases = Integer.parseInt(sc.nextLine());

			while (numberOfTestCases-- > 0) {

				long integer = Long.parseLong(sc.nextLine().trim());
				solution.process(integer);
			}
		} catch (Exception e) {
			System.out
					.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if(stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (sc != null) {
				sc.close();
			}
		}
	}

}