package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SubarraySumEqualsK_Take2 {

	/**
	 * @Intuition
	 * 		This solution is based in the properties of prefix sum
	 * 		You must dominate prefix sum to come up with something like this
	 * 		if the target is 4 than it means that the difference between two points of the prefix sum is the target number, than we found a possibility.
	 * 
	 * 		the number of times curSum - target exists is what you have to sum to the current counter
	 * 		The other case is when you have the prefixSum equals to the desired target
	 * 		This two situations completes the possible cases.
	 * 
	 * 
	 * @media
	 * 		https://imgur.com/4obhb3C
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param a
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] a, int k) {
        
        //testing for k = 2
        //1   0 1 3 6
        //1 - 1 1 2 3
        //3 - 2 = 1
        
        //k = 0
        //0
		
		int prefixSum = 0;
        int count  = 0, complementCount;
        HashMap<Integer, Integer> numCount = new HashMap<>();
            
        for (int n : a)
        {
        
            prefixSum += n;
            
            if (prefixSum == k)
            {
                count ++;
            }
            
            
            //find integers that state that there is a gap of k between prefixSum and prefix sum - target element
            //if they dont exist than there is no paths that sum the target value
            //create an example and visualize it is easier
            complementCount = numCount.getOrDefault(prefixSum - k, 0);
            
            count += complementCount;
            
            numCount.put(prefixSum, numCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
	}
}
