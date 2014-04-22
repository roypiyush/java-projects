/**
 * 
 */
package com.personal.algo.dynamicprogramming;

/**
 * @author piyush
 *
 */
public class BreakingString {

	
	public static int minCuttingCost(int startIndex, int endIndex, int cp, int C[]) {

		if(startIndex < endIndex) {
			
			int min = Integer.MAX_VALUE;
			
			
			if((cp >= 0 && cp < C.length) && C[cp] > startIndex && C[cp] < endIndex) {
				
				min = Math.min(min, 
						(endIndex - startIndex) 
						+ minCuttingCost(startIndex, C[cp], cp - 1, C) 
						+ minCuttingCost(C[cp], endIndex, cp + 1, C));
				
				
			}
			else{
				min = 0;
			}
			
			return min;
			
		}
		else {
			return 0;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		int C[] = {2, 8, 10};
		
		int L = 20;
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < C.length; i++) {
			min = Math.min(min, minCuttingCost(0, L, i, C));
		}
		System.err.println(min);

	}

}
