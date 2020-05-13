package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * HARD
 * @author Nelson Costa
 *
 */
public class FirstMissingPositive extends LeetCodeExercise{
	static FirstMissingPositive f = new FirstMissingPositive();
	
	public static void main(String[] args) {
		int [] nums = stringToArray("[1,2,0]");
		printArray(nums);
		System.out.println(f.firstMissingPositive(nums));
	}

	
	/**
	 * Lazy way of solving this problem
	 * nothing to be proud
	 * 
	 * 
	 * @time	O(N)
	 * @space	O(1)
	 * @bcr		O(N)
	 * 
	 * @param nums
	 * @return
	 */
    public int firstMissingPositive(int[] nums) {
    	
    	if (nums == null || nums.length == 0)
    		return 1;
    	
    	int [] map = new int[Integer.MAX_VALUE/2];
    	int [] map2 = new int[Integer.MAX_VALUE/2];
    	
    	for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
			{
				map[nums[i] - 1]++;
			}
		}
    	
    	for (int i = 0; i < map.length; i++) {
			if (map[i] == 0)
				return i + 1;
		}
    	
    	
        return -1;
    }
}
