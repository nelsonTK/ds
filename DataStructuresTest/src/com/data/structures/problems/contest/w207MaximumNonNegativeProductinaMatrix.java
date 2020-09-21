package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w207MaximumNonNegativeProductinaMatrix {
	/**
	 * 
	 * @intuition
	 * 		dynamic programming with pais that holds max value and min value.
	 * 		the last position will have the greates and the smallest value.
	 * 		We could also use a 3D matrix instead of pair class.
	 * 		the tricky part here is how to deal with rows and columns when they are not eligible.
	 * 		because increasing the size of the array would not work because it would change the value of it.
	 * 
	 * 
	 * @fail
	 * 		1) Commited added in the array 1 line and 1 column with pair(1,1) and that caused the final result to be changed. It was a bad misjudgement that could be avoided if I tested my hypotheses.
	 * 		
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Non Negative Product in a Matrix.
	 * 		Memory Usage: 38.8 MB, less than 25.00% of Java online submissions for Maximum Non Negative Product in a Matrix.
	 * 
	 * @time
	 * 		O(NxM)
	 * 
	 * @space
	 * 		O(NXM)
	 * 
	 * @space
	 * 
	 **/
	public int maxProductPath(int[][] grid) {

		//this will be a DP solution where we will create a DP table that has 
		Pair [][] dp = new Pair[grid.length][grid[0].length];
		int mod = 1_000_000_007;

		dp[0][0] = new Pair(grid[0][0], grid[0][0]);

		long v1 = 0, v2 = 0, v3 = 0, v4 = 0;//values calculated
		int start = 1;
		for (int r = 0; r < dp.length; r++)
		{
			for (int c = start; c < dp[r].length; c++)
			{
				//this portion could be simplified and passed to the assignment portion
				if (r!=0)
				{
					v1 = Math.min(dp[r-1][c].min * grid[r][c], dp[r-1][c].max * grid[r][c]);
					v2 = Math.max(dp[r-1][c].min * grid[r][c], dp[r-1][c].max * grid[r][c]);

				}

				if (c != 0)
				{
					v3 = Math.min(dp[r][c-1].min * grid[r][c], dp[r][c-1].max * grid[r][c]);
					v4 = Math.max(dp[r][c-1].min * grid[r][c], dp[r][c-1].max * grid[r][c]);

				}

				//assignment
				if (c == 0)
				{
					dp[r][c] = new Pair(v1, v2);

				}
				else if (r == 0)
				{
					dp[r][c] = new Pair(v3, v4);

				} 
				else
				{   
					dp[r][c] = new Pair(Math.min(v1, v3), Math.max(v2, v4));
				}
			}
			start = 0;
		}


		return dp[dp.length - 1][dp[0].length -1].max >= 0 ? (int)(dp[dp.length - 1][dp[0].length -1].max % mod) : - 1;

	}

	class Pair{
		long min;
		long max;

		Pair(long min, long max){
			this.min = min;
			this.max = max;
		}
	}
}

/**
 * Solution equivalent to mine but simpler
 * 
 * 3D matrix instead of 2D array of Pairs
 * 
 * more concise code for the assigment of the values.
 * 
 * @author Nelson Costa
 *
 */
class w207MaximumNonNegativeProductinaMatrixUnofficialSolution1 {
	public int maxProductPath(int[][] g) {
		int m = g.length, n = g[0].length, mod = 1_000_000_007;
		long dp[][][] = new long[m][n][2];
		dp[0][0] = new long[]{g[0][0], g[0][0]};
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) continue;
				long a = 0, b = 0;
				if (i == 0) {
					dp[i][j][0] = dp[i][j][1] = g[i][j] * dp[i][j - 1][0];
				} else if (j == 0) { 
					dp[i][j][0] = dp[i][j][1] = g[i][j] * dp[i - 1][j][0];
				} else {
					a = g[i][j] * Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
					b = g[i][j] * Math.min(dp[i][j - 1][1], dp[i - 1][j][1]);
					dp[i][j][0] = Math.max(a, b);
					dp[i][j][1] = Math.min(a, b);
				}
			}
		}
		if (dp[m - 1][n - 1][0] < 0) return -1;
		return (int) ((dp[m - 1][n - 1][0]) % mod);
	}
}