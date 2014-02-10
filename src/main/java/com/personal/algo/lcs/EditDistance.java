package com.personal.algo.lcs;

public class EditDistance {

	private int edit[][];
	private char ch[][];

	public int[][] getEdit() {
		return edit;
	}

	public void setEdit(int edit[][]) {
		this.edit = edit;
	}

	public char[][] getCh() {
		return ch;
	}

	public void setCh(char ch[][]) {
		this.ch = ch;
	}

	public EditDistance editDistance(String source, String destination)
	{
		/**
		 * Initialization 
		 */
		int m = source.length(), n = destination.length();
		
		EditDistance editDistance = new EditDistance();
		
		int edit[][] = new int[m+1][n+1];
		char ch[][] = new char[m+1][n+1];
		
		for(int i = 0; i <= m; i++)
			edit[i][0] = i;
		
		for(int i = 0; i <= n; i++)
			edit[0][i] = i;
		
		for(int i = 0; i <= m; i++)
		{
			for(int j = 0; j <= n; j++)
				ch[i][j] = 'e';
		}
		
		
		for(int i=1; i <= m; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				if(source.charAt(i-1) == destination.charAt(j-1))
				{
					edit[i][j] = edit[i-1][j-1];
					ch[i][j] = 'k';
					continue;
				}
				if(source.charAt(i-1) != destination.charAt(j-1) && edit[i-1][j-1] <= edit[i][j-1] && edit[i-1][j-1] <= edit[i-1][j])
				{
					edit[i][j] = edit[i-1][j-1] + 1;
					ch[i][j] = 'k';
				}
				else if(edit[i][j-1] <= edit[i-1][j] && edit[i][j-1] <= edit[i-1][j-1])
				{
					edit[i][j] = edit[i][j-1] + 1;
					ch[i][j] = 'c';
				}
				else if(edit[i-1][j] <= edit[i][j-1] && edit[i-1][j] <= edit[i-1][j-1]) 
				{
					edit[i][j] = edit[i-1][j] + 1;
					ch[i][j] = 'c';
				}
			}
		}
		
		
		
		editDistance.setEdit(edit);
		
		return editDistance;
	}

	public static void main(String[] args) {
		String source = "park";
		String destination = "spayrk";

		EditDistance editDistance = new EditDistance();
		EditDistance ref = editDistance.editDistance(source, destination);

		System.out.println(ref.getEdit()[source.length()][destination.length()]);

		MatrixPrint.print(ref.getEdit());

	}

}
