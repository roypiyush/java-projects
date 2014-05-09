package com.personal.eulerrule;

public class EulerDemo {

	
	public int findHCF(int number1, int number2)
	{
		
		
		int divisor ;
		int dividend ;
		
		if(number1 < number2)
		{
			divisor = number1;
			dividend = number2;
		}
		else
		{
			divisor = number2;
			dividend = number1;
		}
		
		
		while(divisor > 0)
		{
			int temp = divisor;
			divisor = dividend % divisor;
			dividend = temp;
		}
		
		return dividend;
		
	}
	
	public int findLCM(int number1, int number2)
	{
		return number1 * number2 / findHCF(number1, number2);
	}
	
	public static void main(String[] args) {
		
		EulerDemo eulerDemo = new EulerDemo();

		int numbers[] = {14,56,63,36};
		
		int HCF = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			// Finding HCF of multiple numbers
			HCF = eulerDemo.findHCF(HCF, numbers[i]);
		}
		System.out.println(HCF);
		
		
		int LCM = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			// Finding LCM of multiple numbers
			LCM = eulerDemo.findLCM(LCM, numbers[i]);
		}
		System.out.println(LCM);
		
	}

}
