package com.data.structures.problems;

import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ShuffleAnArray {


	/***
	 * @intuition
	 * 		This is not a brilliant solution I was thinking in a solution with linkedlists but didn't found a way.
	 * 		So I decided to do it with hashmap, but then My approach had a bug so I ended up trying values randomly agains an hashmap.
	 * 
	 * @score
	 * 		Runtime: 90 ms, faster than 17.00% of Java online submissions for Shuffle an Array.
	 * 		Memory Usage: 47.5 MB, less than 40.17% of Java online submissions for Shuffle an Array.
	 * 
	 ***/
	int [] copy;
	int [] output;
	HashMap<Integer, Integer> indexValue = new HashMap<>();

	public ShuffleAnArray(int[] nums) {
		copy = new int[nums.length];
		output = new int[nums.length];

		for (int i = 0; i < nums.length; i++)
		{
			copy[i] = nums[i];
			indexValue.put(i, nums[i]);
		}

	}

	/** Resets the array to its original configuration and return it. */
	//O(1)
	public int[] reset() {
		return copy;
	}

	/** Returns a random shuffling of the array. */
	//O(N)
	public int[] shuffle() {
		//create queue that will have the values of the original array
		Random r = new Random();
		int index = 0;
		int size = indexValue.size();
		while (indexValue.size() != 0){
			//next random number
			int candidate = r.nextInt(size); 
			//get random from hashmap that replicates the original array
			if (indexValue.get(candidate) != null)
			{
				int randVal = indexValue.get(candidate); 
				//setup the output
				output[index++] = randVal;
				// remore the element from the hashmap
				indexValue.remove(candidate); 
			}
		}
		//restore the hashmap for a next time
		restore();

		return output;
	}


	private void restore (){

		for (int i = 0; i < copy.length; i++)
		{
			indexValue.put(i, copy[i]);
		}
	}
}
