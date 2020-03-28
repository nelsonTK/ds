package com.data.structures.problems;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 * 371
 * EASY
 * 
 * @author Nelson Costa
 *
 */
public class SumOfTwoIntegers {
	public static void main(String[] args) {
		
		SumOfTwoIntegers s = new SumOfTwoIntegers();
		int a = 0;
		int b = 0;
		System.out.println(s.getSum(a, b));
	}
	
	/**
	 * 1110 carry
	 * 	111 a
	 *  111 b
	 *  carry is then loaded to b
	 *  sum is then loaded to a
	 *  repeat untill no carry remains
	 *  
	 *  
	 *  	Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Two Integers.
			Memory Usage: 36.1 MB, less than 6.67% of Java online submissions for Sum of Two Integers.		
	 * 
	 * @time O(1) - constant time because despite this is something like O(log2 max(a, b) + 1), we have a fixed limit of integers (32) so it is O(1)
	 * @space O(1) no extra space used
	 * @param a
	 * @param b
	 * @return
	 */
    public int getSum(int a, int b) {
        int carry = -1;
        
        while ( carry != 0)
        {
        	carry = (a & b) << 1;
        	a = a ^ b;
        	b = carry;
        }
    	
    	return a;
    }
}
