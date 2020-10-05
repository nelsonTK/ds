package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b36FindValidMatrixGivenRowAndColumnSums {
	
	/**
	 * @intuition
	 * 		It was kinda of an easy dp problem.
	 * 		the thoughest part was proofing, I still can't come up with a proof, only empirical observation
	 * 		dp[r][c] = min(rowSum[r], colSum[c])
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 100.00% of Java online submissions for Find Valid Matrix Given Row and Column Sums.
	 *		Memory Usage: 47.4 MB, less than 33.33% of Java online submissions for Find Valid Matrix Given Row and Column Sums.
	 * 
	 * 
	 * @time
	 * 		N*M
	 * 
	 * @space
	 * 		N*M
	 * 
	 * @param rowSum
	 * @param colSum
	 * @return
	 */
	public int[][] restoreMatrix(int[] rowSum, int[] colSum) {

		int [][] dp = new int[rowSum.length][colSum.length];
		for (int r = 0; r < rowSum.length; r++ )
		{
			for (int c = 0; c < colSum.length; c++)
			{
				//check colsum
				int min = Math.min(rowSum[r], colSum[c]);
				dp[r][c] = min;
				rowSum[r]-=min;
				colSum[c]-=min;
			}
		}

		return dp;
	}
	
}
