package com.data.structures.problems;

public class NumberOfIslands {
	public static void main(String[] args) {
		
		char [][] grid = new char[4][5];		
		grid[0] = new char[]{'1','1','1','1','0'};
		grid[1] = new char[]{'1','1','0','1','0'};
		grid[2] = new char[]{'1','1','0','0','0'};
		grid[3] = new char[]{'0','0','0','0','0'};
		
/*
		grid[0] = new char[]{'1','1','0','0','0'};
		grid[1] = new char[]{'1','1','0','0','0'};
		grid[2] = new char[]{'0','0','1','0','0'};
		grid[3] = new char[]{'0','0','0','1','1'};

		char [][] grid = new char[3][3];	
		grid[0] = new char[]{'1','1','1'};
		grid[1] = new char[]{'0','1','0'};
		grid[2] = new char[]{'1','1','1'};
*/		
		NumberOfIslands n =  new NumberOfIslands();
		
		System.out.println("Number of islands: ");
		System.out.println(n.numIslands(grid));
	}
	
	

	boolean [][] visited;
	
	/**
	 * 
	 * I have troubles telling the time of recursive solutions
	 * and whether or not a not is processed or you pass in a node more than one...
	 *  it counts one for processing? or just for going throught the node?
	 * 
	 * 
	 * 	Runtime: 1 ms, faster than 99.99% of Java online submissions for Number of Islands.
		Memory Usage: 42.2 MB, less than 21.86% of Java online submissions for Number of Islands.
	 * 
	 * 
	 * 
	 * @time O(MxN), I have some doubts about it...
	 * @space O(MxN)
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0)
		{
			return 0;
		}		
		
		visited = new boolean [grid.length][grid[0].length];
		int numIslands = 0;
		
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {				
				
				if (!visited[row][col] && grid[row][col] - '0' == 1) {
					checkIsland(grid, row, col);
					numIslands++;
				}
			}
		}
		
		return numIslands;
	}
	
	//failed on empty array
	//erro outOfBounds deixei fugir... <= & >= length , confusão com os limites do array
	public void checkIsland(char[][] grid, int row, int col)
	{


		if (row  < 0 || row >= grid.length || col < 0 || col  >= grid[0].length)
		{
			//out of bounds is water
			return;
		}
		
		if (visited[row][col]) {
			return;
		}
		
		visited[row][col] = true;
		
		
		if (grid[row][col] - '0' == 0)  {
			//current value
			return;
		}
		checkIsland(grid, row - 1, col);
		checkIsland(grid, row, col + 1);
		checkIsland(grid, row + 1, col);
		checkIsland(grid, row, col - 1);
	}
	
	

	/**
	 * wrong....
	 * @param grid
	 * @return
	 */
	public int numIslands2(char[][] grid) {

		int u = 0;
		int l = 0;
		int curr = 0;
		int numIslands = 0;

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				
				u = row - 1 < 0 ? 0 : grid[row-1][col] - '0'; //What the...
				l = col - 1 < 0 ? 0 : grid[row][col-1] - '0';
				curr = grid[row][col] - '0';

				if (u == 0 && l == 0 && curr == 1) {
					numIslands++;
				}
			}
		}

		return numIslands;
	}

}
