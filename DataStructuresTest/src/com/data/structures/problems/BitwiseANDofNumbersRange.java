package com.data.structures.problems;

public class BitwiseANDofNumbersRange {

	/**
	 * @intuition
	 * 		This was a cool question, the gist was to understand that to have the same bit, the 1's need to be in the same section.
	 * 		if they are in different sections than there is something in between that is not a 1 or 0.
	 * 
	 * 		when I say section I mean a block of 1's or 0's from binary.
	 * 
	 * 		3th 2nd 1st
	 * 		0	0	0
	 * 		0	0	1
	 * 		0	1	0
	 * 		0	1	1
	 * 		1	0	0
	 * 
	 * 		the third row changes every multiple of 2^2, the second row changes every multiple of 2^1 and first from every multiple of 2^0
	 * 
	 * 		what I have to do is to calculate for each row if the source and target number are in the same section of 0's or 1's.
	 * 
	 * @fail 1) bits calculation size is wrong 2) edge case 0 3) missing getting the ith bit, din't think of it 4) ithBit wrong calculation 5) calculation of the section was wrong, 6) I was using natural logarithm (log e) instead of log 2)
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 13.67% of Java online submissions for Bitwise AND of Numbers Range.
	 * 		Memory Usage: 38.4 MB, less than 45.50% of Java online submissions for Bitwise AND of Numbers Range.
	 * 
	 * @time
	 * 		O(32)
	 * 
	 * @space
	 **/
	public int rangeBitwiseAnd(int m, int n) {
		/*
		2^0
	        5 / 1 = 5 first row is never equal, unless is the same number
			7 / 1 = 7

		2^1
	        7 / 2  (up)= 4 is in the 4th section
	        5 / 2  (up)= 3 is in the 3th section

		2^2
	        5 / 4 (up) = 2 is in the 2nd section
	        7 / 4 (up) = 2 is in the 2nd section


        1) get the number of digits the smallest number have

        2) iterate and find what will be the final number

	        for the first is always different.
	        for the second, third, forth do math
	        
		*/

		if (m == 0)
			return 0;

		int mSize = (int)(Math.ceil(Math.log(m)/Math.log(2) + 1));

		int result = 0,sectionA, sectionB;

		for (int i = mSize - 1; i >= 0; i--)
		{
			result <<= 1;

			int power2 = (int) Math.pow(2, i);

			sectionA = m < power2 ? 0 : (int) Math.floor((double) m / power2) + 1;
			sectionB = n < power2 ? 0 : (int) Math.floor((double) n / power2) + 1; 

			if (sectionA == sectionB)
			{
				int ithBit = (m & (1 << i)) != 0 ? 1 : 0;
				result += ithBit;
			}
		}

		return result;
	}
}

/**
 * They Defend that we must have a common prefix in both numbers
 * 
 * And as soon as you have it you are good to go.
 * 
 * so you shift right when you find it you shift left and you are done
 * 
 * it is a tought conclusion, but is clean
 * 
 * @author Nelson Costa
 *
 */
class BitwiseANDofNumbersRangeSolution1 {
	
	
	  public int rangeBitwiseAnd(int m, int n) {
	    int shift = 0;
	    // find the common 1-bits
	    while (m < n) {
	      m >>= 1;
	      n >>= 1;
	      ++shift;
	    }
	    return m << shift;
	  }
	}
