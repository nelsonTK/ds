package com.data.structures.problems;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DailyTemperatures {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		My solution here was to create a sort of sliding window where I start with the length 2 for each new position
	 * 		and I expand the window while a greater element is not found, and then I close the window when I find it or go off bounds and move 
	 * 		to analyze the next temperature day.
	 * 		the worst case was when there is not element greater coming for none element
	 * 
	 * @media
	 * 		https://imgur.com/tQEMYpt
	 * 
	 * @score
	 *		Runtime: 890 ms, faster than 10.96% of Java online submissions for Daily Temperatures.
	 *		Memory Usage: 47.8 MB, less than 7.42% of Java online submissions for Daily Temperatures.
	 *
	 * @fail
	 *		fail, the while signal was reversed <= instead of >=
	 * 
	 * @time
	 * 		O(N^2)
	 *
	 * @space
	 * 		O(N)
	 * 
	 * @param t
	 * @return
	 */
	public int[] dailyTemperatures(int[] t) {

		if (t.length == 1)
			return new int[]{0};

		int [] ans = new int[t.length];        
		int low, high;
		int index = 0;

		for (int i = 0; i < t.length - 1; i++)
		{
			low = i;
			high = low + 1;

			//increase window if not found element bigger
			while (high < t.length && t[low] >= t[high])
			{
				high++;
			}

			//if number found then assign
			if (high < t.length)
			{
				ans[index] = high - low;
			}

			index++;
		}

		return ans;
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/