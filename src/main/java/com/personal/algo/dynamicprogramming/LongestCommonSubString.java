package com.personal.algo.dynamicprogramming;

import com.personal.algo.dynamicprogramming.lcs.MatrixPrint;

public class LongestCommonSubString {

	
	public static void length(String X, String Y)
	{
		int x = X.length();
		int y = Y.length();
				
		int c[][] = new int[x+1][y+1];
		
		for(int i=0; i<=x; i++)
		{
			c[i][0]=0;
		}
		for(int i=0; i<=y; i++)
		{
			c[0][i]=0;
		}
		
		int max = 0;
		int maxi = 0;
		for(int i=1; i <= x ; i++)
		{
			for(int j=1; j <= y; j++)
			{
				if(X.charAt(i-1) == Y.charAt(j-1))
					c[i][j] = c[i-1][j-1] + 1;
				else
					c[i][j] = 0;
				
				if(c[i][j] > max)
				{
					max = c[i][j];
					maxi = i;
				}
				
			}
		}
		
		MatrixPrint.print(c);
		System.out.println(String.format("max=%d, maxi=%d, max, maxi"));
		
		printSubString(X, maxi, max);
		
	}
	
	public static void printSubString(String X, int x, int length)
	{
		
		for(int i = x - length; i <= length; i++)
		{
			System.out.print(X.charAt(i));
		}
	}
	
	public static void main(String[] args) {
		
		
		String X = "dgeekforgeeks";
		String Y = "fgeekcode";
		
		length(X, Y);
		
		
		
		

	}

}
