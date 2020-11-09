package com.data.structures.problems.codility;

/**
 * https://app.codility.com/demo/results/trainingJ7Q53A-XP8/
 * https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 * EASY/Painless
 * @author Nelson Costa
 *
 */
public class CyclicRotation {
	
	/**
	 * @intuition
	 * 		this solution is very very easy, I tried to do in place solution but failed there was a glitch in the algo.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public int[] solution(int[] a, int k) {
	        // write your code in Java SE 8
	        
	        int [] ans = new int [a.length];
	        
	        for (int i = 0; i < a.length; i++)
	        {
	            int jump = (i + k) % a.length;
	            ans[jump] = a[i];
	        }
	        
	        return ans;
	    }
	}
}
