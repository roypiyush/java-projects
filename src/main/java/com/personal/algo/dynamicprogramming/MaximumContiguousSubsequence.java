package com.personal.algo.dynamicprogramming;

public class MaximumContiguousSubsequence {

	public static void main(String[] args) {

		int A[] = { 90, 80, -60, 200, -10, -40, 15 };

		int value = 0;
		
		long start = System.currentTimeMillis();
//		int value = findMaxSubsequence(A, 0, A.length - 1);
		long end = System.currentTimeMillis();

		System.out.println(String.format("Max Value = %d    Running time: %d",
				value, end - start));

		maxSubArraySum(A, 0, A.length);
		System.out.println(String.format("Max Value = %d    Running time: %d",
				value, end - start));

	}

	private static void maxSubArraySum(int a[], int start, int end) {
		int sum, max = a[0];

		int n = a.length;

		for (int i = 0; i < n; i++)

			for (int j = i; j < n; j++)

			{

				sum = 0;

				for (int k = i; k <= j; k++)

					sum += a[k];

				if (sum >= max)

				{

					start = i;

					end = j;

					max = sum;

				}

			}
		
		System.out.println("max = " + max);
	}

	private static int findMaxSubsequence(int[] A, int i, int j) {

		if (i > j)
			return 0;
		else if (i == j)
			return A[i];

		int value = Integer.MIN_VALUE;

		int sum = 0;

		for (int k = i; k <= j; k++) {
			sum = sum + A[k];
			int v = Math.max(findMaxSubsequence(A, i, k),
					findMaxSubsequence(A, k + 1, j));
			value = Math.max(sum, v);
		}

		return value;

	}

}
