package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum_Take2 {

	/**1
	 * https://leetcode.com/problems/two-sum/
	 * Easy
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoSum_Take2 t = new TwoSum_Take2();
		int [] m = new int [] {3, 3,4,4};
		System.out.println(Arrays.toString(t.twoSum(m, 8)));
	}


	/*********************************
	 * SOLUTION 1
	 ********************************/	

	/**
	 * Runtime: 2 ms, faster than 65.10% of Java online submissions for Two Sum.
Memory Usage: 39.9 MB, less than 5.65% of Java online submissions for Two Sum.
	 * 
	 * @time O(N), N equals to elements in nums
	 * @space O(N), N equals to elements in nums. I think this is the best space I can get
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum1(int[] nums, int target) {

		if (nums == null) {
			throw new IllegalArgumentException();
		}

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}

		Integer pairKey = 0, pairValue = 0;
		int currentValue = 0;
		for (int i = 0; i < nums.length; i++)
		{
			map.put(nums[i], i);
			currentValue = nums[i];
			pairValue = target - currentValue;
			pairKey = map.get(pairValue);

			//if has a value pair and its not the same than we have a solution
			if(pairKey != null && pairKey != i)
			{
				return new int[] {i, pairKey};
			}
		}

		return null;
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/	

	/**
	 * Only use one for loop
	 * 
		Runtime: 1 ms, faster than 99.89% of Java online submissions for Two Sum.
		Memory Usage: 39.8 MB, less than 5.65% of Java online submissions for Two Sum.
	 * 
	 * @time O(N), N equals to elements in nums
	 * @space O(N), N equals to elements in nums. 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {

		if (nums == null) {
			throw new IllegalArgumentException();
		}

		HashMap<Integer, Integer> map = new HashMap<>();

		Integer pairKey = 0, pairValue = 0;
		int currentValue = 0;
		for (int i = 0; i < nums.length; i++)
		{
			currentValue = nums[i];
			pairValue = target - currentValue;
			pairKey = map.get(pairValue);    		
			map.put(nums[i], i);
			//if has a value pair and its not the same than we have a solution
			if(pairKey != null && pairKey != i)
			{
				return new int[] {i, pairKey};
			}
		}

		return null;
	}
}
