package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumOperationstoReduceXtoZero {

	/**
	 * 
	 * @intuition
	 * 		this like asking the maximum subarray with sum equals to "TotalSum - x"
	 * 
	 * 
	 * @score
	 * 		Runtime: 109 ms, faster than 11.69% of Java online submissions for Minimum Operations to Reduce X to Zero.
	 * 		Memory Usage: 59.6 MB, less than 19.24% of Java online submissions for Minimum Operations to Reduce X to Zero.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 ***/
	//forgot to put max in the maxlen
	//put the wrong symbol in comparation of candidate
	public int minOperations(int[] nums, int x) {

		//1) tests len 0, x negative or zero, 
		//2) edge case zero and negative target values
		//3) create prefix sum array and hashmap;
		//calculate target value total - x;
		//iterate each value of the array sum and see if they reach the target value, only if the index is greater;

		if (x == 0)
			return 0;

		if (x < 0)
			return -1;


		HashMap<Integer, Integer> sumIdx = new HashMap<>(); //maps sum to index
		int [] pfx = new int [nums.length + 1];
		
		//find comulative Sum
		for (int i = 1; i < pfx.length; i++)
		{
			pfx[i] = pfx[i - 1] + nums[i - 1];
			sumIdx.put(pfx[i], i);
		}

		//Y - Sum[i] = totalSum - X
		//Y = Sum[i] + (totalSum - x) => we have to find Y
		int target = pfx[pfx.length - 1] - x; //this is the target => totalSum - x

		//can never reach a target below zero
		if (target < 0 )
			return -1;

		int maxLen = -1; //max len of the subarray, we then have to subtrack by the full length
		int candidateIndex;
		
		//try the subarray starting in each element of the prefix sum
		for (int i = 0; i < pfx.length; i++)
		{
			if (sumIdx.containsKey(pfx[i] + target))
			{
				candidateIndex = sumIdx.get(pfx[i] + target);

				if (candidateIndex >= i)
				{
					maxLen = Math.max(candidateIndex - i, maxLen);
				}
			}
		}

		return maxLen == -1 ? maxLen : nums.length - maxLen;        
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
