package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MaxNumberofKSumPairs {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		I don't remember this question already, and took note late
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> charCount = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++)
        {
            charCount.put(nums[i], charCount.getOrDefault(nums[i], 0) + 1);        
        }
        
        int operations = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (charCount.containsKey(nums[i]) && charCount.containsKey(k - nums[i]) && k - nums[i] != nums[i] ||
               charCount.containsKey(nums[i]) && charCount.containsKey(k - nums[i]) && k - nums[i] == nums[i] && charCount.get(k - nums[i]) > 1){
                if (charCount.get(k - nums[i]) == 1)
                {
                    charCount.remove(k - nums[i]);
                }
                else {
                    charCount.put(k - nums[i], charCount.get(k - nums[i]) - 1);
                }
                
                System.out.println(i + " " + operations);
                if (charCount.get(nums[i]) == 1)
                {
                    charCount.remove(nums[i]);
                }
                else {
                    charCount.put(nums[i], charCount.get(nums[i]) - 1);
                }
                
                operations++;
            }
        }
        
        return operations;
    }
}
