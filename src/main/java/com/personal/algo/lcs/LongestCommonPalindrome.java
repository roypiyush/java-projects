package com.personal.algo.lcs;

public class LongestCommonPalindrome {

	public static void main(String[] args) {
		String X = "character";
		String Y = new StringBuilder(X).reverse().toString();
		
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
		
		LCSStructure lcsStructure = longestCommonSubsequence.lcs(X, Y);
		
		
		int x = X.length();
		int y = Y.length();
		longestCommonSubsequence.printSequenceString(X, lcsStructure.getLetters(), x, y);
		
		
		System.out.println("\nLength : " + lcsStructure.getLCS()[x][y] );

		

	}

}
