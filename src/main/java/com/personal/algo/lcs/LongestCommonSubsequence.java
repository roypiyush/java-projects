package com.personal.algo.lcs;


class LCSStructure{
	
	private int[][] LCS;
	private char[][] letters;
	
	public int[][] getLCS() {
		return LCS;
	}
	public void setLCS(int[][] lCS) {
		LCS = lCS;
	}
	public char[][] getLetters() {
		return letters;
	}
	public void setLetters(char[][] letters) {
		this.letters = letters;
	}
	
	
	
}

public class LongestCommonSubsequence {
	
	
	
	public LCSStructure lcs(String X, String Y)
	{
		/**
		 * Initialization 
		 */
		int m = X.length(), n = Y.length();
		
		LCSStructure lcsStructure = new LCSStructure();
		
		
		
		int[][] LCS = new int[m+1][n+1];
		char[][] c = new char[m+1][n+1];
		
		for(int i = 0;i<=m;i++)
		{
			LCS[i][0]=0;
		}
		
		for(int j = 0;j<=n;j++)
		{
			LCS[0][j]=0;
		}
		
		for(int i = 0;i<=m;i++)
		{
			for(int j = 0;j<=n;j++)
			{
				c[i][j] = 'e';
			}
		}
		
		/***************/
		
		for(int i = 1; i<=m ;i++)
		{
			for(int j = 1; j<=n; j++)
			{
				if(X.charAt(i-1) == Y.charAt(j-1))
				{
					LCS[i][j] = 1 + LCS[i-1][j-1];
					c[i][j] = 's';
				}
				else if(LCS[i-1][j] > LCS[i][j-1])
				{
					LCS[i][j] = LCS[i-1][j];
					c[i][j] = 'i';
				}
				else
				{
					LCS[i][j] = LCS[i][j-1];
					c[i][j] = 'j';
				}
			}
		}
		
//		printSequenceString(X, c,m,n);
		
		lcsStructure.setLCS(LCS);
		lcsStructure.setLetters(c);

		
		return lcsStructure;
		
	}
	
	public void printSequenceString(String X, char c[][], int i, int j)
	{
		if( i == 0 || j == 0)
			return;
		

		if(c[i][j] == 's')
		{
			printSequenceString(X, c, i-1, j-1);
			System.out.print(X.charAt(i-1));
		}
		else if(c[i][j] == 'i')
		{
			printSequenceString(X, c, i-1, j);
		}
		else if(c[i][j] == 'j')
		{
			printSequenceString(X, c, i, j-1);
		}
		
	}

	public static void main(String... args) {
		
		String X = "APBCADCQER";
		String Y = "RASBTAUCVE";
		
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
		
		LCSStructure lcsStructure = longestCommonSubsequence.lcs(X, Y);
		
		
		int x = X.length();
		int y = Y.length();
		longestCommonSubsequence.printSequenceString(X, lcsStructure.getLetters(), x, y);
		
		
		System.out.println("\nLength : " + lcsStructure.getLCS()[x][y] );
		
		MatrixPrint.print(lcsStructure.getLCS());

	}

}
