package com.personal.algo.dynamicprogramming;

public class KnapsackProblem {

	public static void main(String[] args) {

		int val[] = { 30, 100, 120 };
		int wt[] = { 10, 20, 30 };
		int W = 50;

		long start = System.currentTimeMillis();
		int value = knapsack(val, wt, W);
		long end = System.currentTimeMillis();

		System.out.println(String.format("Max Value = %d    Running time: %d",
				value, end - start));

		int R[] = new int[W + 1];
		for (int i = 0; i <= W; i++) {
			if (i > 0)
				R[i] = -1;
			else
				R[i] = 0;
		}

		start = System.currentTimeMillis();
		value = knapsackWithDP(val, wt, W, R);
		end = System.currentTimeMillis();

		System.out.println(String.format("Max Value = %d    Running time: %d",
				value, end - start));

	}

	private static int knapsackWithDP(int[] val, int[] wt, int w, int[] r) {
		
		if(r[w] >= 0)
			return r[w];
		
		int value = 0;

		for (int i = 0; i < val.length; i++) {

			if (w - wt[i] > 0) {
				value = Math.max(value, val[i] + knapsackWithDP(val, wt, w - wt[i],r));
			} else if (w - wt[i] == 0) {
				value = val[i];
			}

		}

		r[w] = value;
		
		return value;
	}

	private static int knapsack(int[] val, int[] wt, int w) {


		int value = 0;

		for (int i = 0; i < val.length; i++) {

			if (w - wt[i] >= 0) {
				value = Math.max(value, val[i] + knapsack(val, wt, w - wt[i]));
			} else if (w - wt[i] == 0) {
				value = val[i];
			}

		}

		return value;
	}

}
