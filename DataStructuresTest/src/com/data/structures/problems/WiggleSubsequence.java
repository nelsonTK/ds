package com.data.structures.problems;

/**
 * https://leetcode.com/problems/wiggle-subsequence/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class WiggleSubsequence {

	/**
	 * the gist is every time we find the current element bigger than the previous we increase the current value up to down plus one
	 * 
	 * the thing is that we count the number of times a the difference between two elements is up or down
	 * 
	 * and when the difference is up we assign the value of down + 1 to up and the inverse for when a value goes down.
	 * 
	 * it means that you add one more element to the down sequence
	 * 
	 * this is like hell of a question
	 * 
	 * Hard to get to this beautiful solution
	 * 
	 * But it is pretty much like to find the right way of increasing the values. and basically you increase up according to downs, and downs according to ups.
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Wiggle Subsequence.
	 * 		Memory Usage: 36.4 MB, less than 71.69% of Java online submissions for Wiggle Subsequence.
	 * 
	 * 
	 **/
	public int wiggleMaxLength(int[] nums) {

		int n = nums.length;
		int [] up = new int[n];
		int [] down = new int[n];

		up[0]=1;
		down[0]=1;

		for (int i = 1; i < n; i++)
		{
			if (nums[i] < nums[i-1])
			{
				down[i] = up[i -1] +1;
				up[i] = up[i-1];
			}
			else if (nums[i] > nums[i -1])
			{

				up[i] = down[i -1] +1;
				down[i] = down[i-1];
			}
			else
			{
				up[i] = up[i-1];
				down[i] = down[i-1];
			}
		}

		return Math.max(up[n -1], down[n-1]);
	}
}


https://leetcode.com/problems/wiggle-subsequence/discuss/1115587/My-Simple-Java-Solution-using-2-variables-up-and-down



