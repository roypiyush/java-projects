package com.personal.test;

import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	
	public void process(int N) {
		
		if(N < 2 || N > 1000000 )
			return;
		
		int minPrime = Integer.MAX_VALUE;
		int sqrt = (int) Math.sqrt(N);

		if(N % 2 == 0)
	    {
			minPrime = 2;
			
	    } 
		else {

			for(int i = 3; i <= sqrt; i++) {
				
				while(N % i == 0) {
					N = N / i;
					
					if(i < minPrime)
						minPrime = i;
				}
			}
		}
		
		
		BigInteger d = new BigInteger(Integer.toString(minPrime));
		
		BigInteger fib = new BigInteger("1");
		BigInteger prev = new BigInteger("1");
		
		while (fib.compareTo(new BigInteger("1000000000000000000")) < 0) {
			BigInteger t = fib;
			
			// Generate next fib
			fib = prev.add(fib);
			prev = t;
			

			// Finding factor of Fib
			if(fib.mod(d).compareTo(new BigInteger("0")) == 0) {
				System.out.println(String.format("%d %d", fib, d));
				return;
			}
			
		}
	}
	
	public void createNumberSet(List<Integer> integers, String line) {
		
		Integer integer = null;
		try {
			
			integer = new Integer(line);
			integers.add(integer);
			
		} catch (Exception e) {
			System.out.println(String.format("Cannot Process Non BigInteger [%s] due to %s", integer, e.getMessage()));
		}
	}
	
	
	public static void main(String[] args){
		
		Solution solution = new Solution();
		List<Integer> integers = new LinkedList<Integer>();
		
		
		
		
		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedInputStream(System.in));
			
			int numberOfTestCases = 1;
			int count = 0;
			while (count < numberOfTestCases + 1 && sc.hasNextLine()) {
				try {
					
					String integer = sc.nextLine().trim();
					
					if(integer.isEmpty())
					{
						break;
					}
					if(count > 0)
						solution.createNumberSet(integers, integer);
					else 
						numberOfTestCases = new Integer(integer);

				} catch (Exception e) {
					System.out.println(String.format("Cannot accept input due to %s", e.getMessage()));
					return;
				}
				
				count++;
			}
		} catch (Exception e) {
			System.out.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if(sc != null) {
				sc.close();
			}
		}

		
		
		for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext();) {
			Integer integer = iterator.next();
			solution.process(integer);
		}
		
	}

}