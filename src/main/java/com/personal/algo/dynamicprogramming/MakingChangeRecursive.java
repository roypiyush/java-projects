package com.personal.algo.dynamicprogramming;

public class MakingChangeRecursive {

	
	
	
	public static void main(String[] args) {
	
		int A[] = {1,2,5,7};
		int Sum = 18;
		
		long start = System.currentTimeMillis();
		int changeCount = makingChange(A, Sum);
		System.out.println("Minimum coins: " + changeCount);
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
		
		int R[] = new int[Sum + 1];
		for(int i = 0; i <= Sum; i++) {
			if(i>0)
				R[i] = -1;
			else
				R[i] = 0;
		}
		
		
		start = System.currentTimeMillis();
		changeCount = makingChangeWithDP(A, Sum, R);
		System.out.println("Minimum coins: " + changeCount);
		end = System.currentTimeMillis();
		
		System.out.println(end-start);
		
	}

	private static int makingChangeWithDP(int[] a, int sum, int r[]) {
		
		if(r[sum] >= 0)
			return r[sum];
		
		int min = sum;
		
		for(int i = 0; i < a.length; i++) {
			
			if(sum - a[i] >= 0)
			
			min = Math.min(min, makingChangeWithDP(a, sum - a[i],r));
		}
		
		
		// No change occurred
		if(sum == min)
			return sum;
		
		min = min + 1;
		r[sum] = min;
		
		return min;
	}

	private static int makingChange(int[] a, int sum) {
		
		if(sum == 0)
			return sum;
		
		int min = sum;
		
		for(int i = 0; i < a.length; i++) {
			
			if(sum - a[i] >= 0)
			
			min = Math.min(min, makingChange(a, sum - a[i]));
		}
		
		
		// No change occured
		if(sum == min)
			return sum;
		
		min = min + 1;
		
		return min;
	}

}
