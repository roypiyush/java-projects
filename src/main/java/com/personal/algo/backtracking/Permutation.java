package com.personal.algo.backtracking;

public class Permutation {

    private int seqNo = 1;
    
    void permute(char[] str, int i) {
	if(i == str.length - 1) {
	    System.out.print(seqNo++ + " : ");System.out.println(str);
	}
	else {
	    for (int j = i; j < str.length; j++) {
		swap(str, i, j);
		permute(str, i + 1);
		swap(str, i, j);
	    }
	}
    }

    private void swap(char[] str, int i, int j) {
	char t = str[i];
	str[i] = str[j];
	str[j] = t;
    }
    
    public static void main(String[] args) {
	
	Permutation permutation = new Permutation();
	char[] str = {'a', 'b', 'c', 'd'};
//	System.out.println(str);
	permutation.permute(str, 0);
    }

}
