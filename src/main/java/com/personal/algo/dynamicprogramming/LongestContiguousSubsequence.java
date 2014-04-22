package com.personal.algo.dynamicprogramming;

public class LongestContiguousSubsequence {
	
	
	public static int maxLongestContiguousSubsequence(int[] A, int j)
	{
		if(j < 0)
			return 0;
			
		return Math.max(maxLongestContiguousSubsequence(A, j-1) + A[j], A[j]);

	}
	
	public static int MCS(int[] A, int i, int j) {
		
		if(i == j)
			return A[i];
		else if(i>j)
			return 0;

						
			int max = Integer.MIN_VALUE;
			for(int k = i; k < j; k++) {
				max = Math.max(MCS(A, i, k), MCS(A, k+1, j));
			}
			
			return max;

		
		
	}
	
	public static void longestMonotonicIncreasing(int[] A) {
		
		
		
		
		int startIndex = 0;
		int maxStartIndex = 0;

		int increment = 0;
		int maxIncrement = 0;
		
		for (int j = 1; j < A.length; j++) {
			
			if (A[j - 1] < A[j]) {
				if(increment == 0) {
					startIndex = j - 1;
				}
				increment = increment + 1;
			
			}
			else {
				if(increment > maxIncrement) {
					maxStartIndex = startIndex;
					maxIncrement = increment;
				}
				
				// Set it to maximum value before resetting increment counter
				increment = 0;

			}
		}
		
		System.out.println(String.format("Start Index: %d   End Index: %d", maxStartIndex, maxStartIndex + maxIncrement));
		for (int i = maxStartIndex; i <= maxStartIndex + maxIncrement; i++) {
			System.out.print(A[i] + " ");
		}
		
	}
	
	public static void main(String[] args) {
		
		int[] A = {13,-3,-25,20,-3,-23,-23,18,20,-7,12,-5,-22,15,-4,7};
		
		longestMonotonicIncreasing(A);
		
	}

}
