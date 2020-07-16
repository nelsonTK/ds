package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class UniquePathsII extends LeetCodeExercise{

	static UniquePathsII u = new UniquePathsII();
	
	public static void main(String[] args) {
		int [][] grid = stringToMatrix("[[0,0,0],[0,1,0],[0,0,0]]");
		grid = stringToMatrix("[[0],[1]]");
		System.out.println(u.uniquePathsWithObstacles(grid));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/	   
    /**
    * @intuition
    *   the goal was filling the last line with 1's and 0's according it was previous or after a obstacle.
    *   because starting from the end in the last line after an obstacle is impossible to get to the target, because you can only move right
    *   the rest the same principles aplies the sum of
    *   
    *    dp[r][c] = dp[r + 1][c] and dp[r][c + 1];
    *    
    *    However I use only one row to achieve this result.
    *    
    * @score
	*	Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
	*	Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Unique Paths II.
	*
    * @fail
    *    1) bad variable attributed to r, I attributed n instead of m
    *    2) assign dp with n-1 instead of real size
    *    3) order of invariant wrong in the wrile....basic stuff
    *    4) forgot to assign zero to the 1's in the dp solution
    *    5) false assumptions, that I can add blindly 1 on the edges. didn't thought on the case where the end is missing
    *
    * @debug
    *    yes (2)
    *    
    * @time  	O(M*N)
    * @space  	O(N)
    * @bcr  	
    *
    */
    public int uniquePathsWithObstacles(int[][] grid) {
        
        if (grid == null || grid.length == 0)
            return 0;
        
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        
        int[] dp = new int[grid[0].length];
        
        int i = n;
        
        //fill the dp
        while (i >= 0 && grid[m][i] != 1 )
        {
            dp[i] = 1;
            i--;
        }
        
        
        //solve
        for (int r = m - 1; r >= 0; r--)
        {
            for (int c = n; c >= 0; c--)
            {
                if (grid [r][c] != 1)
                {
                    
                    if (r + 1 <= m)
                    	dp[c] = dp [c];
                    
                    if (c + 1 <= n)
                    	dp[c] += dp[c + 1];
                    
                }
                else
                {
                	dp[c] = 0;
                }
            }
        }
        
        return dp[0];
    }
}
