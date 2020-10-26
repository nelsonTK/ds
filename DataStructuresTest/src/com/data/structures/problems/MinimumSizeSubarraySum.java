package com.data.structures.problems;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumSizeSubarraySum {

	/**
	 * @intuition
	 * 		The intuition is to have some kind of sliding window.
	 * 		if the element is greater than target then return 1 can not have better than that.
	 * 		
	 * 		in a nutshell we expand the window while whe have not found a bigger element (expand the front)
	 * 		and we decrease the window while the sum is bigger than the target (delete from rear)
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 99.90% of Java online submissions for Minimum Size Subarray Sum.
	 *		Memory Usage: 38.9 MB, less than 13.35% of Java online submissions for Minimum Size Subarray Sum.
	 * 
	 * @time
	 * 
	 * @space
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
		public int minSubArrayLen(int s, int[] nums) {

			if (nums == null || nums.length == 0)
				return 0;

			int sum = 0;
			int size = 0;
			int ans = Integer.MAX_VALUE;

			for (int i = 0; i < nums.length; i++)
			{
				size++;
				sum += nums[i];

				while (sum >= s)
				{
					if (size == 1)
						return size;

					ans = Math.min(ans, size);
					sum -= nums[i - (size -1)];
					size--;
				}
			}

			return ans == Integer.MAX_VALUE? 0 : ans;
		}
	}
}
