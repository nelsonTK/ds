package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumPathSum extends LeetCodeExercise {

	static MinimumPathSum m = new MinimumPathSum();
	
	public static void main(String[] args) {
		
		int [][] grid = stringToMatrix(
				"[\r\n" + 
				"  [1,3,1],\r\n" + 
				"  [1,5,1],\r\n" + 
				"  [4,2,1]\r\n" + 
				"]");
		
		System.out.println(m.minPathSum(grid));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		DP problem where I start filling the dp matrix which will be the input
	 * 		with sums.
	 * 
	 * 		the formula is:
	 * 			dp[row][col] = dp[row][col] + Min(dp[row + 1][col], dp[row][col + 1]);
	 * 			but there are some edges cases in the process.
	 * 
	 * 		We start from the bottom and fill the matrix all the way up untill the first position.
	 * 
	 * @score
	  		Runtime: 3 ms, faster than 42.86% of Java online submissions for Minimum Path Sum.
			Memory Usage: 41.9 MB, less than 87.84% of Java online submissions for Minimum Path Sum.
			
	 * @fail
	 * 		1) I forgot to subtract 1 to cLen variable
	 *      2) I forgot about the case where one of right and bottom is zero and doesnt count to the sum, and in those cases as it is zero, it will be used instead of the correct value.
     *      2.1) One case was missing in my correction
     *      
	 * @time	
	 * @space	
	 * 
	 * @param grid
	 * @return
	 */
    public int minPathSum(int[][] grid) {
        
    	if (grid == null || grid.length == 0)
    		return 0;
    	
    	int rLen = grid.length - 1, cLen = grid[0].length - 1, right, bottom; 
    	
    	for (int r = rLen; r >= 0; r--)
    	{
    		for (int c = cLen; c >= 0; c--)
    		{
    			right = bottom = -1;
    			
    			if (c + 1 <= cLen)
    				right = grid[r][c + 1];
    			
    			if (r + 1 <= rLen)
    				bottom = grid[r + 1][c];
    			
    			//the usual case
                if (right >= 0 && bottom >= 0)
                {
    			    grid[r][c] = grid[r][c] + Math.min(right, bottom);
                }
                //edge cases
                else if (right < 0 && bottom >= 0)
                {
                    grid[r][c] = grid[r][c] + bottom;
                }
                else if (right >= 0  && bottom < 0)
                {
                    grid[r][c] = grid[r][c] + right;
                }
            }
    	}
    	
    	return grid[0][0];
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * The same principle than my solution but less readable
 * 
 * @author Nelson Costa
 *
 */
class MinimumPathSumSolution4 {
    public int minPathSum(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    grid[i][j] = grid[i][j] +  grid[i][j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j],grid[i][j + 1]);
            }
        }
        return grid[0][0];
    }
}

/**
 * Solution with 1D array
 * 
 * that turns the space complexity to O(n) where n is the columns size.
 * 
 * this is a cool optimization over a more simpler dp approach using a matrix as dp.
 * 
 * this might be the optimal solution if you cannot change the original matrix.
 * 
 * 
 * @author Nelson Costa
 *
 */
class MinimumPathSumSolution3 {
    public int minPathSum(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    dp[j] = grid[i][j] +  dp[j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }
}

/**
 * 
 * It is a recursive solution with memoization
 * 
 * faster than dp solution, its memoization.
 * 
 * in this aproach you start in the last node, go all the way down till the first node, and from there, you start adding from start to finish
 * and in the end/Last position you will have the final and minimum sum path.
 * 
 * It's also a valid way of dealing with this problem. but I cannot really understand why its faster than DP approach.
 * 
 * 
 * 
 * @author leetcode random guy
 *
 */
class MinimumPathSumTopSolution {
    public int minPathSum(int[][] grid) {
        return helper(grid, grid.length - 1, grid[0].length - 1, new int[grid.length][grid[0].length]);
    }
    
    public int helper(int[][] grid, int r, int c, int[][] mem){
        //When the index goes out of the grid, we return max possible value. 
        if(r < 0 || c < 0){
            return Integer.MAX_VALUE;
        }
        
        if(r == 0 && c == 0){
            return grid[r][c];
        }
        
        if(mem[r][c] > 0){
            return mem[r][c];
        }
        
        mem[r][c] = grid[r][c] + Math.min(helper(grid, r-1, c, mem), helper(grid, r, c-1, mem));
        
        return mem[r][c];
    }
}