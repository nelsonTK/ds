package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * EASY
 * @author Nelson Costa
 *
 */
public class MaximumSubarray extends LeetCodeExercise {

	public static void main(String[] args) {
		int [] a = stringToArray("[-2,1,-3,4,-1,2,1,-5,4]");
		a = stringToArray("[1,1,-2]");
		a = stringToArray("[2,0,-3,2,1,0,1,-2]");
		a = stringToArray("[-1,0,-2]");

		MaximumSubarray m = new MaximumSubarray();
		printArray(a);
		System.out.println("max sum: " + m.maxSubArray(a));
	}

	/**
	 * @intuition
	 * 		1) Negative numbers never sum up its always about the largest of the negative numbers
	 * 		2) if the numbers are positive we can just sum them, the tricky part is if we have negatives in the middle.
	 * 		if we have negatives in the middle, it is worth go though them only if they are below carry, 
	 * 		because this way we could sum up with the positive number coming and we might find a bigger sum in the other side
	 * 
	 * 		I solved this with two passes. one to map the negative numbers to come, and other to perform the sums.
	 * 
	 * @score
			Runtime: 3 ms, faster than 9.87% of Java online submissions for Maximum Subarray.
			Memory Usage: 39.9 MB, less than 7.04% of Java online submissions for Maximum Subarray. 
	 *
	 * @optimizations
	 * 		After figuring out the most important steps for cracking this solution. 
	 * 		I think I can produce a much cleaner solution, by only comparing the current with the following
	 * 
	 * 
	 * @fail
	 * 		1) I missplace the signals of the first for, it should have been -- instead of ++
	 * 		2) I forgot part of the logic that deals with summing negative numbers, in case of the carry is on going
	 * 		3) I forgot that I can have a carry on going when I stop a sequence, so I compare the max with the carry plus current, and not the current alone
	 * 
	 * @time	O(N) => O(2N)
	 * @space	O(N)
	 * @bcr 	O(N)
	 * 
	 * @param a
	 * @return
	 */
	public int maxSubArray0(int[] a) {

		if (a == null || a.length == 0)
			return Integer.MIN_VALUE;

		if (a.length == 1)
			return a[0];

		int max = Integer.MIN_VALUE;
		int carry = 0;
		HashMap<Integer, Integer> negativesComming = new HashMap<>();	

		//map negatives to come given array
		for (int i = a.length - 1; i > 0; i--) {
			if (a[i] < 0)
				negativesComming.put(i, a[i] + negativesComming.getOrDefault(i + 1, 0));
		}

		for (int i = 0; i < a.length; i++) {
			//positive numbers
			if (a[i] >= 0)
			{
				if (negativesComming.getOrDefault(i + 1, 0) + (a[i] + carry) > 0)
				{
					carry += a[i];
					max = Math.max(carry, max);
				}
				else
				{
					carry += a[i];
					max = Math.max(carry, max);
					carry = 0;
				}
			}
			//negative numbers
			else
			{
				if (carry > 0)
				{
					carry += a[i];
					max = Math.max(carry, max);
				}
				else
				{
					max = Math.max(a[i], max);
				}
			}
		}

		return max;
	}

	/**
	 * @intition
	 * 		just comparing the current with the following, if the current is negative, we continue the sum only if the carry stays positive after sum
	 * 		else we zero carry and start new sequence.
	 * 
	 * 		the principles are the same as the previous solution
	 * 
	 * 
	 * @score
			Runtime: 1 ms, faster than 66.32% of Java online submissions for Maximum Subarray.
			Memory Usage: 39.4 MB, less than 9.39% of Java online submissions for Maximum Subarray.
			
	 * @fail
	 * 		1) I was not dealing with the case where the negative number is first and adds to a positive number, reducing possible values.
	 * 		2) I excluded zero from the sum, it was a mistake 
	 * 		3) I was not zeroing carry properly after a negative number sum carry and turns it negatives
	 * 	
	 * @optimizations
	 * 		I Still think this if conditions could be improved
	 * 
	 * @time  O(N)
	 * @space O(1)
	 * @bcr   O(N)
	 * 
	 * @param a
	 * @return
	 */
	public int maxSubArray(int[] a) {

		if (a == null || a.length == 0)
			return Integer.MIN_VALUE;

		if (a.length == 1)
			return a[0];

		int max = Integer.MIN_VALUE;
		int carry = 0;

		for(int i = 0; i < a.length - 1; i++)
		{
			
			if(a[i] < 0 || carry + a[i] + a[i + 1] < 0)
			{
				if (carry > 0)
				{
					carry += a[i];
					max = Math.max(carry, max);
					carry = carry < 0 ? 0 : carry;

				}
				else
				{
					carry += a[i];
					max = Math.max(carry, max);
					carry = 0;

				}
			}
			else if (carry + a[i] + a[i + 1] >= 0)
			{
				carry += a[i];

				max = Math.max(carry, max);
			}
		}

		//last element
		carry += a[a.length - 1];
		max = Math.max(carry, max);

		return max;
	}
}

/**
 * Very Clean solution with Dynamic programming
 * 
 * @score
  		Runtime: 1 ms, faster than 66.32% of Java online submissions for Maximum Subarray.
		Memory Usage: 39.3 MB, less than 9.39% of Java online submissions for Maximum Subarray.
 * 
 * @author Nelson Costa
 *
 */
class MaximumSubarraySolution3 {
	  public int maxSubArray(int[] nums) {
	    int n = nums.length, maxSum = nums[0];
	    for(int i = 1; i < n; ++i) {
	      if (nums[i - 1] > 0) nums[i] += nums[i - 1];
	      maxSum = Math.max(nums[i], maxSum);
	    }
	    return maxSum;
	  }
	}

/**
 * Greedy solution
 * 
 * @score
  		Runtime: 1 ms, faster than 66.32% of Java online submissions for Maximum Subarray.
		Memory Usage: 39.3 MB, less than 9.39% of Java online submissions for Maximum Subarray.
		 
 * @author Nelson Costa
 *
 */
class MaximumSubarraySolution2 {
	  public int maxSubArray(int[] nums) {
	    int n = nums.length;
	    int currSum = nums[0], maxSum = nums[0];

	    for(int i = 1; i < n; ++i) {
	      currSum = Math.max(nums[i], currSum + nums[i]);
	      maxSum = Math.max(maxSum, currSum);
	    }
	    return maxSum;
	  }
	}

/**
 * The worst performing solution but is that catch my most interest
 * 
 * is a divide & conquer approach where the bullets that enable a solution are
 * 
 * 0) we divide the array in half
 * 1) the solution is either at left
 * 2) or athe the right
 * 3) or goes throught the middle
 * 4) recursively call the functions
 * 5) base case is a array cell
 * 
 * @author Nelson Costa
 *
 */
class MaximumSubarraySolution1 {
	  public int crossSum(int[] nums, int left, int right, int p) {
	    if (left == right) return nums[left];

	    int leftSubsum = Integer.MIN_VALUE;
	    int currSum = 0;
	    for(int i = p; i > left - 1; --i) {
	      currSum += nums[i];
	      leftSubsum = Math.max(leftSubsum, currSum);
	    }

	    int rightSubsum = Integer.MIN_VALUE;
	    currSum = 0;
	    for(int i = p + 1; i < right + 1; ++i) {
	      currSum += nums[i];
	      rightSubsum = Math.max(rightSubsum, currSum);
	    }

	    return leftSubsum + rightSubsum;
	  }

	  public int helper(int[] nums, int left, int right) {
	    if (left == right) return nums[left];

	    int p = (left + right) / 2;

	    int leftSum = helper(nums, left, p);
	    int rightSum = helper(nums, p + 1, right);
	    int crossSum = crossSum(nums, left, right, p);

	    return Math.max(Math.max(leftSum, rightSum), crossSum);
	  }

	  public int maxSubArray(int[] nums) {
	    return helper(nums, 0, nums.length - 1);
	  }
	}