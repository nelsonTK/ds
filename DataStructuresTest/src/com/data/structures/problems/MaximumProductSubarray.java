package com.data.structures.problems;

public class MaximumProductSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/

	/**
	 *[WRONG]
	 *	There are few steps that were not too into account
	 *
     * @intuition
     * 
     * 
     * @score
	 * 
	 *
     * @fail
     *   1) didn't saw the case where the highest value comes from negative
	 *
     * @time   O(N)
     * @space  O(1) because its constant
     * @bcr    O(N) ??
	 *
	 **/
	public int maxProduct0(int[] nums) {

		//guards
		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		int dp[] = new int[]{1, nums[0]};
		int max = nums[0];


		for (int i = 1; i < nums.length; i++)
		{
			if(nums[i] <= 0)
			{
				dp[0] = 1;
				dp[1] = nums[i] * dp[1];
			}
			else if (nums[i - 1] == 0)
			{
				dp[0] = 1;
				dp[1] = nums[i];
			}
			else if (nums[i - 1] < 0)
			{
				dp[0] = nums[i];
				dp[1] = nums[i] * dp[1];
			}
			else // if (nums[i - 1] > 0)
			{
				dp[0] = nums[i] * dp[0];
				dp[1] = nums[i] * dp[1];
			}

			if (nums[i] <= 0)
			{
				max = Math.max(Math.max(nums[i], dp[1]), max);
			}
			else
			{
				max = Math.max(Math.max(dp[0], dp[1]), Math.max(nums[i], max));
			}
		}

		return max;
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**
	 *
	 *
	 *
     * @intuition
     *   Garbage solution
     *
     * @score
        Runtime: 90 ms, faster than 6.21% of Java online submissions for Maximum Product Subarray.
        Memory Usage: 39.3 MB, less than 9.76% of Java online submissions for Maximum Product Subarray.
	 *
     * @fail
     *     	1) I was not updating the max value at every iteration
	 *
     * @time   O(N^2)
     * @space  O(1)
     * @bcr    O(N)
     * 
	 */
	public int maxProduct1(int[] nums) {

		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		int temp  = nums[0];
		int fMax = temp;
		for (int i = 0; i <= nums.length - 1; i++)
		{
			temp  = nums[i]; 
			fMax = Math.max(temp, fMax);

			for (int j = i + 1; j < nums.length; j++)
			{
				temp *= nums[j];
				fMax = Math.max(temp, fMax);
			}

			fMax = Math.max(temp, fMax);
		}

		return Math.max(fMax, nums[nums.length - 1]);
	}



	/*********************************
	 * SOLUTION 3
	 ********************************/

	/**
	 * 
	 * 
	 * @intuition
	 *	  	in each iteration we save the min and the maximum value. to save those values we compare
	 *		  	PreviousMax = Max(previousMax * current, previousMin * current, current);
	 *		  	PreviousMin = Min(previousMax * current, previousMin * current, current);
	 *
	 *	  	The Importance of saving the previous max and min is for negative numbers.
	 *	  	because a negative number can turn positive if multiplied by a negative number, and it will be bigger if the negative numbers are smallest.
	 *	  	for positive numbers the biggest is always the biggest, no big deal about it.
 	 *
	 * @score
	 *		Runtime: 1 ms, faster than 93.86% of Java online submissions for Maximum Product Subarray.
	 *		Memory Usage: 39.5 MB, less than 9.76% of Java online submissions for Maximum Product Subarray.
	 *
	 *
	 * @fail
	 *      1) forgot about comparing the answer with itself
	 *
 	 *
	 * @time	O(N)
	 * @space O(1)
	 *
	 *
	 **/
	public int maxProduct(int[] nums) {

		if (nums == null || nums.length == 0)
			return 0;

		int ans = nums[0], pMax = nums[0], pMin = nums[0], cur, tmp;

		for (int i = 1; i < nums.length; i++)
		{
			cur =  nums[i];
			tmp =  Math.max(Math.max(pMax * cur, pMin * cur), cur);
			pMin = Math.min(Math.min(pMax * cur, pMin * cur), cur);
			pMax = tmp;

			ans = Math.max(Math.max(ans,pMax), cur);
		}

		return ans;
	}

}
