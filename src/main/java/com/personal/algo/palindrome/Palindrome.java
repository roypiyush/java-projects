package com.personal.algo.palindrome;

interface Processor {
	void process();
}

class PalindromeProcessor implements Processor {
	private String literal;

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}


	public void process() {
		if (literal == null || literal.length() == 0) {
			throw new IllegalArgumentException();
		}

		int length = literal.length();

		int preMiddleChar = 0;
		int postMiddleChar = length - 1;

		boolean isPanlindrome = true;

		while (preMiddleChar <= postMiddleChar) {
			char pre = literal.charAt(preMiddleChar);
			char post = literal.charAt(postMiddleChar);

			if (pre == post) {
				preMiddleChar++;
				postMiddleChar--;
			} else {
				isPanlindrome = false;
				break;
			}
		}

		String message = "The literal [" + literal + "] is "
				+ (isPanlindrome ? "" : "NOT ") + "a palindrome.";

		System.out.println(message);

	}

}

class KPalindromeProcessor implements Processor {
	private String literal;
	private int kTolerance = -1;

	public KPalindromeProcessor(String literal, int kTolerance) {
		this.literal = literal;
		this.kTolerance = kTolerance;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public int getkTolerance() {
		return kTolerance;
	}

	public void setkTolerance(int kTolerance) {
		this.kTolerance = kTolerance;
	}

	public void process() 
	{
		if (literal == null || literal.length() == 0) 
		{
			throw new IllegalArgumentException();
		}

		int length = literal.length();

		int preMiddleChar = 0;
		int postMiddleChar = length - 1;

		int i = 1;
		for(; i <= kTolerance && preMiddleChar <= postMiddleChar;)
		{
			char pre = literal.charAt(preMiddleChar);
			char post = literal.charAt(postMiddleChar);
			
			if (pre == post) 
			{
				preMiddleChar++;
				postMiddleChar--;
			}
			else if (literal.charAt(preMiddleChar + i) == literal.charAt(postMiddleChar))
			{
				preMiddleChar = preMiddleChar + i;
			}
			else if (literal.charAt(preMiddleChar) == literal.charAt(postMiddleChar - i))
			{
				postMiddleChar = postMiddleChar - i;
			}
			
			i++;
		}

		String message = "The literal [" + literal + "] is "+ (kTolerance >= i ? "" : "NOT ") + "a K-palindrome.";

		System.out.println(message);

	}

}

public class Palindrome {

	public static void main(String[] args) {

		PalindromeProcessor palindromeProcessor = new PalindromeProcessor();
		palindromeProcessor.setLiteral("abccba");
		palindromeProcessor.process();
		
		
		KPalindromeProcessor kPalindromeProcessor = new KPalindromeProcessor("abxa", 1);
		kPalindromeProcessor.process();

	}

}
