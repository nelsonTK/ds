package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ShortestUnsortedContinuousSubarray {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The goal is to find the right pointer the left pointer, the min and the max.
	 * 		than see for the right and for the left if they should actually be on the left/right position or we should move por outwards in the array
	 * 
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 99.95% of Java online submissions for Shortest Unsorted Continuous Subarray.
	 *		Memory Usage: 40.4 MB, less than 57.61% of Java online submissions for Shortest Unsorted Continuous Subarray.
	 *
	 *
	 * @time
	 * 		O(N)
	 * 
	 * 
	 * @space
	 * 		O(1)
	 *
	 **/
	public int findUnsortedSubarray(int[] nums) {

		// find base subarray limits
		// get min and max
		// compare at right with max
		// compare at left win min
		if(nums == null || nums.length <= 1)
			return 0;

		int L = 0, R = 0, i = 1;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;


		// 1 - find base subarray limits
		while (i < nums.length && nums[i] >= nums[i - 1])
		{
			i++;
		}

		//find left limit
		if (i < nums.length)
		{
			L = i - 1;
			R = i++;
		}

		//find right limit
		while (i < nums.length)
		{
			if (nums[i] < nums[i - 1])
			{
				R = i;   

			}            
			i++;
		}

		//if limits have not move then it is sorted
		if (L == 0 && R == 0) return 0; 

		// 2 - get min and max element of the base subarray
		for (int j = L; j < R +1; j++)
		{

			max = Math.max(nums[j], max);
			min = Math.min(nums[j], min);
		}

		// 3 - compare at left side of the base subarray with min elment
		while (L  - 1 >= 0 && nums[L - 1] > min)
		{
			L--;
		}

		// 4 - compare at right side of the base subarray with max
		while (R + 1 < nums.length && nums[R + 1] < max)
		{
			R++;
		}

		return (L == 0 && R == 0) ? 0 : R - L + 1;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Using Sort and then compare the two arrays
 * @author Nelson Costa
 *
 */
class ShortestUnsortedContinuousSubarraySolution3 {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }
}

/**
 * I think this solution is similar to mine but I've not analyzed it	
 * @author Nelson Costa
 *
 */
class ShortestUnsortedContinuousSubarraySolution5 {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
