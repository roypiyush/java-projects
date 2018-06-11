package com.personal.old.tricks;

class FinalStateClass {
	final int i;
	
	public FinalStateClass() {
		this(42);
	}
	
	public FinalStateClass(int i) {
		this.i = i;
	}
}

public class FinalRead {

	public static void main(String[] args) {
		FinalStateClass f = new FinalStateClass(21);
		System.out.println(f.i);

	}

}
