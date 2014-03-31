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
	
	public static void main(String[] args) {
		
		int[] A = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		
		int j = A.length - 1;
		System.out.println(maxLongestContiguousSubsequence(A, j));
		
		System.out.println(MCS(A, 0 , j));
		
	}

}
