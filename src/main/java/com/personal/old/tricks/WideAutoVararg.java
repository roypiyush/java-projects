/**
 * 
 */
package com.personal.old.tricks;

/**
 * @author piyush
 *
 */
public class WideAutoVararg {

	void print(Integer i) {
		System.out.println("a");
	}
	
	void print(short i) {
		System.out.println("b");
	}
	
	void print(int i) {
		System.out.println("c");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WideAutoVararg o = new WideAutoVararg();
		char i = 'c';
		o.print(i);

	}

}
