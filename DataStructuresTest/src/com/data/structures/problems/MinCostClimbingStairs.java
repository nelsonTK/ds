package com.data.structures.problems;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * EASY
 * @author Nelson Costa
 *
 */
public class MinCostClimbingStairs {

	/**
	 * @intuition
	 * 		Dynamic programming solution for this problem
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 81.92% of Java online submissions for Min Cost Climbing Stairs.
	 *		Memory Usage: 38.6 MB, less than 95.04% of Java online submissions for Min Cost Climbing Stairs.
	 *
	 * @optimization
	 * 		I could use only two variables instead
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param cost
	 * @return
	 */
    public int minCostClimbingStairs(int[] cost) {
        
        int dp[] = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++)
        {
            //let it go or 
            dp[i] = Math.min(dp[i-1] + cost[i], dp[i-2] + cost[i]);
        }
        
        return Math.min(dp[dp.length - 1], dp[dp.length -2]);
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Dynamic programming solution, with variables instead of array
 * @author Nelson Costa
 *
 */
class MinCostClimbingStairsSolution {
    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }
}
