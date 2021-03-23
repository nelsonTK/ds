package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/3sum-with-multiplicity/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class _3SumWithMultiplicity {
	/**
	 * @intuition
	 * 	For me the gist of this solution was to firstly count all the pair sums.
	 * 	and iterate each number and see how many combinations do exist.
	 * 	So I used and hashmap inside another hashmap.
	 * 	One has the indexes and hashmap. and the other has the sums and the frequencies
	 * 
	 * @failed 
	 * 		because of +1 issues
	 * 		and later because of mod.
	 * 
	 * 
	 * @time
	 * 	This is a NlogN + N^2 + N^2 Solution
	 * 
	 * @space
	 * 	O(N^2)   
	 **/
	public int threeSumMulti(int[] arr, int target) {

		Arrays.sort(arr);
		HashMap<Integer,HashMap<Integer, Integer>> indexToSums = new HashMap<>();

		//calculate the pairs sum after zero index, and sum its frequency
		HashMap<Integer, Integer> auxMap;
		for (int i = 1; i < arr.length - 1; i ++)
		{
			for (int j = i + 1; j < arr.length; j++)
			{
				auxMap = indexToSums.get(i);
				if(auxMap==null)
				{
					auxMap = new HashMap<Integer, Integer>();
				}
				auxMap.put(arr[i] + arr[j], auxMap.getOrDefault(arr[i] + arr[j], 0) + 1);
				indexToSums.put(i, auxMap);
			}
		}


		long ans = 0;
		//check for each index from zero to len - 3, if there is a complement that matches and add its frequency
		for (int i = 0; i <= arr.length -1 - 2; i++)
		{
			for (int j = i + 1; j < arr.length - 1; j ++)
			{
				ans += indexToSums.get(j).getOrDefault(target - arr[i], 0);
			}
		}

		return (int) (ans % 1_000_000_007);
	}
}
