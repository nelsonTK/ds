package com.data.structures.problems;

public class MaxAreaofIsland {
	//failed because I had a bad length comparison for the column length
	/**
	 * @intuition
	 * 	Just backtracking...Not worthy
	 * 
	 * 
	 * @time
	 * 	O(N)
	 * @space
	 * 	O(N)
	 * 
	 * 
	 * @param grid
	 * @return
	 */
    public int maxAreaOfIsland(int[][] grid) {
        
        if (grid == null)
            return 0;
        
        int max = 0;
        
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                if(grid[r][c] == 1)
                {
                    max = Math.max(countLand(r, c, grid), max);
                }
            }
        }
        
        return max;
    }
    
    private int countLand(int r, int c, int [][] grid)
    {
        int sum = 0;
        
        //if out of bounds or is zero
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] == 0)
        {
            return sum;
        }
        
        if (grid[r][c] == 1)
        {
            sum++;
            grid[r][c] = 0;
        }
        
        sum += countLand(r, c + 1, grid);
        
        sum += countLand(r - 1, c, grid);
        
        sum += countLand(r, c - 1, grid);
        
        sum += countLand(r + 1, c, grid);
        
        return sum;
    }
}
