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

}
