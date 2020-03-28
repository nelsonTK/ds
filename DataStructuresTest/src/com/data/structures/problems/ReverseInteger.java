package com.data.structures.problems;

/**
 * 7
 * https://leetcode.com/problems/reverse-integer/
 * EASY
 * 20200321
 * Deviam indicar o que queriam que fosse retornado aquando de um overflow
 * 
 * @author Nelson Costa
 *
 */
public class ReverseInteger {

	
	public static void main(String[] args) {
		ReverseInteger r = new ReverseInteger();

		int x = -2147483642;
		//int x = -61;		
		System.out.println("x  is : " + x );
		System.out.println("rX is : " + r.reverse(x));
	}
	
	/**
	 * 	Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse Integer.
		Memory Usage: 37.1 MB, less than 5.55% of Java online submissions for Reverse Integer.
		
		I acidentally saw a guy explaining this instead of reversing bits and understood some of the principles
	 * 
	 * 
	 * @fail because I didnt paid attention to the upper bounds of an integer. 
	 * Failed again because reversed 120 (021), not equals to 120 when revered back (12)
	 * Failed again because 
	 * 
	 * 
	 * @time 	O(N) where N is the number digits of the integer, I believe this could be considered O(1) because it doesnt grow more than the limit of 32 bit signed integers 
	 * @space 	O(1)
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		
		if (x == 0)
			return x;
		
		int sign = (x < 0) ? -1 : 1;
		long rX = 0;
		int base10 = 10;
		int digit = 0;
		x = Math.abs(x);
		
		while (x > 0)
		{
			digit = x % base10;
			rX = rX * base10 + digit;
			x /= 10;
		}
		
		rX *= sign;
		
		if (rX > Integer.MAX_VALUE || rX < Integer.MIN_VALUE)
			return 0;
		
		return (int) rX;
	}
}
