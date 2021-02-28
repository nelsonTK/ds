package com.data.structures.problems.contest;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/sum-of-unique-elements/
 * @author Nelson Costa
 *
 */
public class b45SumofUniqueElements {
	public int sumOfUnique(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int res = 0;

		for (int i : nums)
		{

			map.put(i, map.getOrDefault(i, 0) + 1);

		}


		for (int key : map.keySet())
		{
			if (map.get(key) == 1)
				res+=key;
		}

		return res;
	}
}
