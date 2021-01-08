package com.data.structures.problems;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 * HARD
 * @author Nelson Costa
 *
 */
public class SplitArrayLargestSum {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
    /**
     * @intuition
     * 		This was a nother partitioning type of exercise.
     * 		
     * 
     * @score
     * 		Runtime: 78 ms, faster than 29.33% of Java online submissions for Split Array Largest Sum
     * 		Memory Usage: 37 MB, less than 28.92% of Java online submissions for Split Array Largest Sum.
     * 
     * @fail
     * 		because I confuse cut with cuts,I sholt
     * 
    **/
    public int splitArray(int[] nums, int m) {
        
        int [][] dp = new int[m][nums.length];
        int [] prefix = new int[nums.length];
        prefix[0] = nums[0];
        
        for (int i = 1; i < prefix.length; i++)
        {
            prefix[i] = nums[i] + prefix[i - 1];      
        }
        
        dp[0] = prefix;
        
        for (int cuts = 1; cuts < m; cuts++)
        {
            for (int n = cuts; n < dp[cuts].length; n++)
            {
                dp[cuts][n] = Integer.MAX_VALUE;
                
                for (int cut = 1; cut <= n - cuts + 1; cut++)
                {
                    dp[cuts][n] = Math.min(dp[cuts][n], Math.max(dp[cuts - 1][n - cut], prefix[n] - prefix[n - cut]));
                    
                }
            }
        }
        
        return dp[m - 1][nums.length - 1];
    }
}
