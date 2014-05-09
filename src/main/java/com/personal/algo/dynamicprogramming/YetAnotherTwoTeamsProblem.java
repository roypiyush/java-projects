package com.personal.algo.dynamicprogramming;

public class YetAnotherTwoTeamsProblem {
	
	public long count(int[] skill) {
		int n = skill.length;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += skill[i];
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (skill[j] < skill[j + 1]) {
					int t = skill[j];
					skill[j] = skill[j + 1];
					skill[j + 1] = t;
				}
			}
		}

		long res = 0;
		long[] d = new long[sum + 1];
		for (int s = 1; s <= sum; s++)
			d[s] = 0;
		d[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int s = sum; s >= 0; s--) {
				if (s - skill[i] >= 0) {
					if ((2 * s - sum > 0) && (2 * s - sum < 2 * skill[i]))
						res += d[s - skill[i]];
					
					printArray(d);
					
					d[s] += d[s - skill[i]];
					printArray(d);
				}
			}
		}

		return res;
	}
	
	public void printArray(long arr[])
	{
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		YetAnotherTwoTeamsProblem problem = new YetAnotherTwoTeamsProblem();
		int skills[] = {5,4,7,6};
		System.out.println(problem.count(skills));
	}

}