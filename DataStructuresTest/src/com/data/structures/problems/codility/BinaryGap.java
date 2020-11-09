package com.data.structures.problems.codility;

/**
 * https://app.codility.com/demo/results/trainingJXARYN-PC8/
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 * EASY/Painless
 * @author Nelson Costa
 *
 */
public class BinaryGap {


	/**
	 * @intuition
	 * 		is just to count the zeros between the ones and use de max variable 
	 * 		as a flag from which after it is set to 0, we are allowed to start counting zeros.
	 * 
	 * 		we set max variable eligible when we found the first one.
	 * 
	 * @fail
	 * 		compilation error only because of binary operation
	 * 
	 * @took
	 * 		28' but I was chilling
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
		public int solution(int n) {

			int max = -1;
			int curGap = 0;

			while (n != 0)
			{
				if ((n & 1) == 1)
				{
					max = Math.max(max, curGap);
					curGap = 0;

				}
				else if ((n & 1) == 0 && max >=0)
				{
					curGap++;
				}
				
				n = n >> 1;
			}

			max = max == -1? 0 : max;

			return max;
		}
	}	
}
