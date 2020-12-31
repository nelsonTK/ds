package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/perfect-squares/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PerfectSquares {


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		this is a variation of coin change problem that an be solved with DP.
	 * 		in the columns you find the target sum, and at the rows you find the scares.
	 * 		you calculate the best value for each target, whenever you add a new perfect square
	 * 		when the perfect square is bigger than target we stop.
	 * 
	 * @optimizations
	 * 		can be done with one row
	 * 
	 * @score
	 * 		Runtime: 83 ms, faster than 28.41% of Java online submissions for Perfect Squares.
	 * 		Memory Usage: 47.7 MB, less than 10.08% of Java online submissions for Perfect Squares.
	 * 
	 * @time
	 * 		n*sqrt(n)
	 * 
	 * @space
	 * 		n*sqrt(n)
	 * 
	 * @param n
	 * @return
	 */
	public int numSquares(int n) {

		int [][] dp = new int[1 + (int)Math.sqrt(n)][n + 1];

		///add max value to the first row to not destroy the calculations for the perfect square 1
		for (int i = 0; i < dp[0].length; i++)
			dp[0][i] = Integer.MAX_VALUE;

		for (int ps = 1; ps < dp.length; ps++ )
		{
			if (ps * ps > n)
				break;

			for (int target = 1; target < dp[ps].length; target++ )
			{
				if (target - ps*ps >= 0)
				{
					dp[ps][target] = Math.min(dp[ps - 1][target], dp[ps][target - ps*ps] + 1);
				}
				else
				{
					dp[ps][target] = dp[ps-1][target];
				}

			}
		}

		//find the least element calculated
		int row = dp.length - 1;
		int col = dp[0].length -1;
		while (dp[row][col] == 0)
		{
			row--;
		}

		return dp[row][col];
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * DP solution with one array
 * same philosophy than my solution
 * 
 * 
 * @author Nelson Costa
 *
 */
class PerfectSquaresSolution2 {

	public int numSquares(int n) {
		int dp[] = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		// bottom case
		dp[0] = 0;

		// pre-calculate the square numbers.
		int max_square_index = (int) Math.sqrt(n) + 1;
		int square_nums[] = new int[max_square_index];
		for (int i = 1; i < max_square_index; ++i) {
			square_nums[i] = i * i;
		}

		for (int i = 1; i <= n; ++i) {
			for (int s = 1; s < max_square_index; ++s) {
				if (i < square_nums[s])
					break;
				dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
			}
		}
		return dp[n];
	}
}
