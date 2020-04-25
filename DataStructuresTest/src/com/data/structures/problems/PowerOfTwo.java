package com.data.structures.problems;

/**
 * 231
 * https://leetcode.com/problems/power-of-two
 * Easy
 * @author Nelson Costa
 *
 */
public class PowerOfTwo {

	public static void main(String[] args) {
		PowerOfTwo t = new PowerOfTwo();
		int n = 128;
		System.out.println(t.isPowerOfTwo(n));

		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(~n + 1));
		System.out.println(Integer.toBinaryString(n & (~n + 1)));
		System.out.println(n);
		System.out.println(~n + 1);
		System.out.println(-n - 1);
		System.out.println(-n);
		System.out.println(~n);
	}
	
	/**
	 * 1) Power of 2 only have one 1 bit
	 * 2) Power of two minus 1 equals to only 1's and one digit less than the power of two ( e.g 16 and 16 - 1)
	 * 3) Power of two are greater than 0.
	 * 
	 * 
	 *	Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
		Memory Usage: 36.8 MB, less than 7.32% of Java online submissions for Power of Two.
	 * @time O (1)
	 * @space O (1)
	 * @param n
	 * @return
	 */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
