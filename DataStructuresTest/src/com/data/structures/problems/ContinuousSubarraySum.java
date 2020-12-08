package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ContinuousSubarraySum {

	/*********************************
	 * SOLUTION 1 (FAIL)
	 ********************************/
	/**        
        @fail
        	1) wrong algorithm only a subset of the subarrays where accepted
	 **/
	public boolean checkSubarraySumold(int[] nums, int k) {

		if (nums == null || nums.length <= 1)
			return false;

		int prefix = nums[0];
		int previous = nums[0];
		int current;
		for (int i = 1; i < nums.length; i++)
		{
			current = nums[i];

			//test if the prefix two elements are multiple
			if ((previous + current) % k == 0)
				return true;

			previous = current;
			prefix += current;

			//test if the prefix sum till the current number is multiple of k
			if (prefix % k == 0)
				return true;
		}

		return false;
	}



	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		The gist of this is to understand that if the modulo of two different points of a commulative sum are equal than the number between A and B (open close) is a multiple of the k used to modulo A and B.
	 * 
	 * 		from here we create an hashmap to save the index and validate if the indexes are 2 apart
	 * 
	 * 		to match we should have a matching module in our hashmap where its index is 2 apart at least
	 * 		
	 * 		and before starting to iterate we insert the zero to the hashmap with the -1 index. This is to fix the case where the prefix is module zero, which represents a match.

	 * 
	 * @media
	 * 		https://imgur.com/vtMzI2f
	 * 
	 * @score
	 * 		Runtime: 2 ms, faster than 99.38% of Java online submissions for Continuous Subarray Sum.
	 * 		Memory Usage: 39.9 MB, less than 31.31% of Java online submissions for Continuous Subarray Sum.
	 * 
	 **/
	public boolean checkSubarraySum(int[] nums, int k) {
		/**    
        1) have hashMap to store the modulos
        2) insert 0 to get the case where the whole array is the answer, or when the answer only appears one time and it is 0 example (k = 6 and array equals to [0, 6, 1]).
        3) do the prefixSum and store the sum % k
		 **/   

		if (nums == null || nums.length < 2)
			return false;

		HashMap<Integer, Integer> modIndex = new HashMap<>();
		modIndex.put(0, -1); //it is to solve the case where the current prefix sum is 0
		int prefix = 0;

		for (int i = 0; i < nums.length; i++)
		{
			prefix += nums[i];

			//mod of zero is impossible
			if (k != 0)
				prefix %= k;

			/**
                if current prefix sum is in the hashmap it means we have a curresponding modulo
                which in tern means in between the two coordinates we have a multiple of k
			 **/

			if (modIndex.containsKey(prefix))
			{
				if(i - modIndex.get(prefix) >= 2)
					return true;
			}
			else
			{
				modIndex.put(prefix, i);
			}
		}

		return false;
	}
}
