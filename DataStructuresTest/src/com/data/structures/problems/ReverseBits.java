package com.data.structures.problems;

/**
 * EASY
 * https://leetcode.com/problems/reverse-integer/
 * Was tough, I needed to learn how binary operations work, &, |, ^, shifts 
 * @author Nelson Costa
 *
 */
public class ReverseBits {

	public static void main(String[] args) {
		ReverseBits r = new ReverseBits();
		int input = 43261596;
		System.out.println("processing:  " + r.reverseBits(input));
		System.out.println("expected for " + 43261596 + " is " + 964176192);
	}
	
	/**
	 * 		Runtime: 1 ms, faster than 99.57% of Java online submissions for Reverse Bits.
			Memory Usage: 38.5 MB, less than 7.32% of Java online submissions for Reverse Bits.
			
	   @failed because I though the goal was to rotate the most significant part, this is complement 2.
		
	 * @param input
	 * @return
	 */
	public int reverseBits(int input) {
    	if (input == 0)
    		return 0;
    	
        int result = 0;
        int mask = 1;
        int count = 0;
                
        while ( count < 32)
        {
        	result = (result << 1) | (input & mask);
        	input = input >> 1;
        	count++;
        }
        
        
        return result;
    }

}
