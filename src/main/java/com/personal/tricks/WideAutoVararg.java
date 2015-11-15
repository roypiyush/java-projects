/**
 * 
 */
package com.personal.tricks;

/**
 * @author piyush
 *
 */
public class WideAutoVararg {

	void print(Integer i) {
		System.out.println("Long");
	}
	
	void print(long i) {
		System.out.println("long");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WideAutoVararg o = new WideAutoVararg();
		int i = 1;
		o.print(i);

	}

}
