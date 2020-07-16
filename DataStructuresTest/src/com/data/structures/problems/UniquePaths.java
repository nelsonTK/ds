package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 * MEDIUM
 * 
 * @author Nelson Costa
 *
 */
public class UniquePaths {

	static UniquePaths u = new UniquePaths();
	public static void main(String[] args) {

		int m = 3;
		int n = 2;
		u.uniquePaths(m, n);
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition 
	 * 		The intuition is that the number of unique paths are given by the number of ways of bottom element and right element.

	 * 		No need to create a matrix, and DP only needs to be n length long, we dont need to have a full matrix.

	 * 		I tried to think in a solution with only variables but I was not capable of it.

	 *	Base case:
	 * 		The base case is m*n = 0. because it is the targe source.
	 *
	 * 		When you hit a element outside the range then you know that it's cost is 1.
	 *
	 * 	Initial State:
	 * 		fill the array with 1's but the last position
	 *
	 * Subproblem:		
	 * 		The subproblem is dp[c] = dp[c + 1] + dp[c];
	 *
	 * Return 
	 * 		first element of dp will have my answer
	 *
     * @comments
     *   I believe I could have done it a little bit different, by providing 1 to the last position instead of zero.
     *   I believe that it would reduce the number of validations.
	 *
	 * @score
     	Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
		Memory Usage: 35.8 MB, less than 5.10% of Java online submissions for Unique Paths.
	 *
     * @fail   
     *   1) array out of bounds, I switch m per n, for the columns and rows
     *   1) forgot the case where array is size 1...
     *   
     * @time   O(N)
     * @space  O(N)
     * @bcr    O(1) - Some mathematical Formula

	 */
	public int uniquePaths0(int m, int n) {

		if (m <= 0 || n <= 0)
			return 0;

		if (m == 1 && n == 1)
			return 1;

		int [] dp = new int [n];
		Arrays.fill(dp, 1);
		dp[n - 1] = 0;

		// -2 because we start from the row before the last
		for (int r = m - 2; r >= 0; r--)
		{
			for (int c = n - 1; c >= 0; c--)
			{

				if (r + 1 < m && c + 1 < n)
				{
					//normal case
					dp[c] = dp[c+1] + dp[c];
				}
				else if (r + 1 >= m && c + 1 < n || r + 1 < m && c + 1 >= n )
				{
					//edges
					dp[c] = 1;
				}
			}
		}

		return dp[0];
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**
	 * 
	 * @intuition
	 * 
	 * @comments
     *		Simplification of the code above
	 * 
	 *  @alternativesw
	 *  		Recusion with Backtracking
	 *  		DP with Matrix
	 *  		DP with array
	 *  
	 * @score
			Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
			Memory Usage: 36 MB, less than 5.10% of Java online submissions for Unique Paths.

	 *   @fail   
	 *       1) array out of bounds, I switch m per n, for the columns and rows
	 *       1) forgot the case where array is size 1...
	 *       
	 *   @time   O(N*M)
	 *   @space  O(N)
	 *   @bcr    O(1) - Some mathematical Formula
	 *  
	 * @param m
	 * @param n
	 * @return
	 * 
	 */
	public int uniquePaths(int m, int n) {

		if (m <= 0 || n <= 0)
			return 0;

		if (m == 1 && n == 1)
			return 1;

		int [] dp = new int [n];
		Arrays.fill(dp, 1);
		int cost;
		// -2 because we start from the row before the last
		for (int r = m - 2; r >= 0; r--)
		{
			for (int c = n - 1; c >= 0; c--)
			{
				cost = 0;

				if (r + 1 < m)
				{
					cost = dp[c];
				}

				if (c + 1 < n)
				{
					cost += dp[c + 1];
				}

				dp[c] = cost;
			}
		}

		return dp[0];
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/


/**
 * Unofficial solution
 * 
 * 
 * Done with same philosophy as the solutions below but using only one array instead of matrix.
 * 
 * @time	O(M*N)
 * @space	O(N)
 * 
 * @param m
 * @param n
 * @return
 */
class UniquePathsUnoficialSolution1 {

	public int uniquePaths(int m, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[j] = dp[j] + dp[j - 1];
			}
		}
		return dp[n - 1];
	}
}


/**
 * Solution that uses the same principles than solution1.
 * 
 * but in a DP way.
 * 
 * space O(NxM) opposite to my solution that uses O(N)
 * 
 * @author Nelson Costa
 *
 */
class UniquePathsSolution2 {
	public int uniquePaths(int m, int n) {
		int[][] d = new int[m][n];

		for(int[] arr : d) {
			Arrays.fill(arr, 1);
		}
		for(int col = 1; col < m; ++col) {
			for(int row = 1; row < n; ++row) {
				d[col][row] = d[col - 1][row] + d[col][row - 1];
			}
		}
		return d[m - 1][n - 1];
	}
}


/**
 * [Timeout]
 * Recursive solution 
 * 
 * 
 * This approach is different from my DP appraoch. sufficiently different to make me effort to understand it.
 * 
 * In this approach you define how many ways to can get to a cell, starting from [0][0] which means that
 * first row and first column only have a way to be reached. from there the inner cells are a sum of the other cells.
 * 
 * I do the same but starting from the end. this approach despite being the inverse of mine, is less obvious to me.
 * 
 * @author Nelson Costa
 *
 */
class UniquePathsSolution1 {
	public int uniquePaths(int m, int n) {
		if (m == 1 || n == 1) {
			return 1;
		}
		return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
	}
}
