package com.personal.old.tricks;

class MyGenericClass<T> {

	/**
	 * Generic T is specified at class level
	 */
	private T memberVariable;

	public T myMethod(T t) {
		return t;
	}

	/**
	 * Another method where generic E is not specified in class level
	 */
	public <E> E myAnotherMethod() {
		return null;
	}

	public T getMemberVariable() {
		return memberVariable;
	}

	public void setMemberVariable(T memberVariable) {
		this.memberVariable = memberVariable;
	}

}

class AnotheGenericClass<R extends MyGenericClass<T>, T> {

	private R memberVariable;

	/**
	 * Only generic R that extends super class can call its method
	 * 
	 * @param r
	 */
	public void myMethod(R r) {

		if (r == null)
			return;

		r.myAnotherMethod();

	}

	public R getMemberVariable() {
		return memberVariable;
	}

	public void setMemberVariable(R memberVariable) {
		this.memberVariable = memberVariable;
	}
}

public class GenericsSyntaxTest {

	public static void main(String[] args) {
		System.out.println("hello");
	}

}
