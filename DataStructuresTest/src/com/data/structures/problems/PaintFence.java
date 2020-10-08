package com.data.structures.problems;

/**
 * https://leetcode.com/problems/paint-fence
 * EASY
 * @author Nelson Costa
 *
 */
public class PaintFence {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		This is mainly a mathematical solution. (combinatorics)
	 * 
	 *	//Fence 0
	 *		//0 - k
	 *
	 *	//Fence 1
	 *		//k*(k-1)   - differents
	 *		//k*1       - the same
	 *
	 *	//Fence n
	 *		//different
	 *			//prevsame*k-1
	 *			//prevdifferent*k-1
	 *		//Same
	 *			//prevdifferent*1
	 * 
	 * 	This is just math
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Paint Fence.
	 * 		Memory Usage: 35.5 MB, less than 98.42% of Java online submissions for Paint Fence.
	 * 
	 * @time 
	 * 		O(N) 
	 * 
	 * @space 
	 * 		O(N)
	 * 
	 **/
	public int numWays(int n, int k) {

		if (n <= 1)
		{
			return n * k;
		}

		if (n == 2)
		{
			return k + k * (k - 1);
		}

		int [][] dp = new int[n][2];
		dp[0] = new int[]{k, k};
		dp[1] = new int[]{k*1, k*(k-1)};
		int same, different;
		for (int i = 2; i < n; i++)
		{
			same = dp[i-1][0];
			different = dp[i-1][1];
			dp[i][0] = different*1;
			dp[i][1] = (same + different) * (k - 1);
		}

		return dp[n-1][0] + dp[n-1][1];
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * More efficient Solution
 * instead of using matric only uses variables
 * 
 * @author Nelson Costa
 *
 */
class PaintFenceUnofficialSolution {

	public int numWays(int n, int k) {
		if(n == 0) return 0;
		else if(n == 1) return k;
		int diffColorCounts = k*(k-1);
		int sameColorCounts = k;
		for(int i=2; i<n; i++) {
			int temp = diffColorCounts;
			diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
			sameColorCounts = temp;
		}
		return diffColorCounts + sameColorCounts;
	}
}
