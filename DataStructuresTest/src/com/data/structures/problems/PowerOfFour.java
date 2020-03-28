package com.data.structures.problems;

/**
 * 342
 * Very confused question
 * for me power of 4 is a number multiplied by itself 4 times.
 * 
 * @author Nelson Costa
 *
 */
public class PowerOfFour {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 1;
		PowerOfFour p = new PowerOfFour();
		System.out.println(num);
		System.out.println(p.isPowerOfFour(num));
	}

	/**
	 * With Hints
	 * 		Runtime: 1 ms, faster than 99.94% of Java online submissions for Power of Four.
			Memory Usage: 36.8 MB, less than 6.67% of Java online submissions for Power of Four.
	 * 
	 * 1) Powers of 4 are always positive 
	 * 2) Power of 2 is true if number & (number - 1 ) == 0
	 * 3) all powers of 4 are power of tree and we need to distinguish
	 * I distinguished by saying: 
	 * 		if number - 1 is multiple of 3. because all power of 4 minus 1 are multiple of tree 
	 * 
	 * 
	 * @space O (N)
	 * @time O(N)
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFour(int num) {
		if (num <= 0)
			return false;

		return (num & (num - 1)) == 0  //find if it is power of 2.
				&& 
				(num - 1) % 3 == 0 ; //find if num - 1 is multiple of 3 because all power of 4 minus 1 are multiple of tree 
	}


	/**
	 * @failed the first time because I was not understanding the power of 4 language.
	 * but it is n * n * n * n = n^4
	 * remembered how to solve how to solve :
	 * 
	 * 		log x (16) = 4
	 *  	x^4  = 16
	 *  <=>	√x^4 = √16
	 *  <=>	x^2  = 4
	 *  <=>	√x^2 = √4
	 *  <=> x    = 2 
	 *  
	 *  I believe that 0 ^ 4 should be considered 0. and they don't in this question. it is bad.
	 *  This question really looks bad....
	 *  
	 * @time O(1)
	 * @Space O(1)
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFourBadInterpretation(int num) {
		if (num == 0)
			return false;
		return Math.sqrt(Math.sqrt(num)) % 1 == 0;
	}

	/**
	 * 
		Runtime: 2 ms, faster than 11.92% of Java online submissions for Power of Four.
		Memory Usage: 37 MB, less than 6.67% of Java online submissions for Power of Four.
	 * 
	 * 
	 * The rational was that a power of 4 (4 64 256 ...) is also a power or 2,
	 * so it will have only one number.
	 * If we subtract that number per 1. We get only 1's, and a even number of 1's.
	 * 
	 * @time O(N)
	 * @space O(1)
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFour1(int num) {
		String bits = Integer.toBinaryString(num - 1);

		if (num == 0)
			return false;

		if (num == 1)
			return true;

		return bits.contains("0")? false : bits.length() % 2 == 0;
	}	


	class Solution {
		public boolean isPowerOfFour(int num) {
			// 0xaaaaaaaa is a mask used to tell if odd exponents. because odd exponents are not power of 4. e.g 2^1 2^3 2^4, etc..
			// if you & with a power of 4, the result is 0
			return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
		}
	}
}


