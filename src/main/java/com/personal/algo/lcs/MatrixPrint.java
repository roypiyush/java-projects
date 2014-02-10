package com.personal.algo.lcs;

public class MatrixPrint {

	public static void print(int matrix[][])
	{
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(String.format("%2s ", matrix[i][j]));
			}
			System.out.println();
		}
	}
}
