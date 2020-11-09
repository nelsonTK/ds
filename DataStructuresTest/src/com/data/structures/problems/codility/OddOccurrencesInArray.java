package com.data.structures.problems.codility;

/**
 * https://app.codility.com/demo/results/trainingGF9VRD-ZUP/
 * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 * EASY/Painless
 * @author Nelson Costa
 *
 */
public class OddOccurrencesInArray {
	
	/**
	 * @intuition
	 * 		this is just a problem from leetcode
	 * 
	 * @time
	 * 		O(N)
	 * @space
	 * 		O(1)
	 * 	
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public int solution(int[] a) {
	        
	        int ans = 0;
	        
	        for (int n : a)
	        {
	            ans ^=n;
	        }
	        
	        return ans;
	    }
	}
}
