package com.personal.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeFactorization {

	private Map<Integer, Integer> primeFactors = new HashMap<Integer, Integer>();
	
	public Map<Integer, Integer> getPrimeFactors() {
		return primeFactors;
	}

	public void setPrimeFactors(Map<Integer, Integer> primeFactors) {
		this.primeFactors = primeFactors;
	}

	private void addToMap(int i) {
		
		if(primeFactors.get(i) != null) {
			int numberOfOccurence = primeFactors.get(i);
			primeFactors.put(i, numberOfOccurence + 1);
		}
		else {
			primeFactors.put(i, 1);
		}
	}
	
	private int getNumber(int a, int b) {
		return a + b;
	}
	
	public boolean isPrime(int number) {
		
		int sqrt = (int)Math.sqrt(new Double(number));
		
		for (int i = 2; i <= sqrt; i++) {
			if(number % i == 0)
				return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		
		PrimeFactorization factorization = new PrimeFactorization();

		List<Integer> integers = new ArrayList<Integer>();
//		int givenNumber = 10000000;
//		int sqrt = (int) Math.sqrt(givenNumber);
//
//		while (givenNumber % 2 == 0)
//	    {
//			givenNumber = givenNumber / 2;
//			integers.add(2);
//	    }
//		
//		for(int i = 3; i <= sqrt; i++) {
//			
//			while(givenNumber % i == 0) {
//				givenNumber = givenNumber / i;
//				integers.add(i);
//			}
//		}
//		
//		if(givenNumber > 1)
//		integers.add(givenNumber);
		
//		System.out.println(integers);
		
		for (int i = 2; i <= 1000000; i++) {
			if(factorization.isPrime(i)) {
				integers.add(i);
				System.out.println(i);
			}
		}
		System.out.println(integers.size());
		
		
		
		

	}

}
