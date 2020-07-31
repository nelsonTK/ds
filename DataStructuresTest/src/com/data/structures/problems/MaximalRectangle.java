package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 * HARD
 * @author Nelson Costa
 *
 */
public class MaximalRectangle {
	static MaximalRectangle m = new MaximalRectangle();
	static MaximalRectangleSolution3 m3 = new MaximalRectangleSolution3();

	public static void main(String[] args) {
		char [][] c = new char[4][5];
		c[0] = new char []{'1','0','1','0','0'};
		c[1] = new char []{'1','0','1','1','1'};
		c[2] = new char []{'1','1','1','1','1'};
		c[3] = new char []{'1','0','0','1','0'};

		m.maximalRectangle(c);
		m3.maximalRectangle(c);
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * 
	 * @intuition
	 * 		The intuition for this problem is to calculate the trailling ones for each cell which would represent the width of the retangle
	 * 		than for each cell we will calculate the maximum retangle for a given height starting in a certain cell.
	 * 		the width gets minified as we go up because we must keep the retangle properties.
	 * 
	 * @score
	 * 		Runtime: 11 ms, faster than 42.65% of Java online submissions for Maximal Rectangle.
	 *		Memory Usage: 47.8 MB, less than 10.44% of Java online submissions for Maximal Rectangle.
	 * 
	 * 
	 * @fail 
	 * 		1) fail in setting the current cell streak 
	 * 		2) no guard for empty array
	 * 
	 * @optimizations
	 * 		I could have done this in one go, and not in two for loops
	 * 
	 * @time
	 * 		O(N^2M) if the matrix is all ones we will traverse all rows n times for each cell.
	 * @space
	 * 
	 * 
	 **/
	public int maximalRectangle(char[][] matrix)
	{
		if (matrix == null || matrix.length == 0)
			return 0;

		//count trailling ones/histogram

		//perform max histogram calculation

		int [][] dp = new int[matrix.length][matrix[0].length];

		for (int row = 0; row < matrix.length; row++)
		{           
			for (int col = 0; col < matrix[0].length; col++)
			{
				if (matrix[row][col] == '1')
				{
					if (col - 1 >= 0)
					{
						dp[row][col] = dp[row][col - 1]+1;  
					}
					else
					{
						dp[row][col] = 1;
					}
				}
			}
		}

		int max = 0;
		for (int row = 0; row < dp.length; row++) //O(N)
		{           
			for (int col = 0; col < dp[0].length; col++) //O(M)
			{
				if (dp[row][col] != 0)
				{
					//calculate max for current
					int width = dp[row][col];
					int curHeight = 1;
					max = Math.max(width * curHeight, max);
					curHeight++;

					//calculate max histogram up
					while(row - curHeight + 1 >= 0 && dp[row - curHeight + 1][col] != 0) //O(N)
					{
						width = Math.min(dp[row - curHeight + 1][col], width);
						max =  Math.max(width * curHeight, max);
						curHeight++;                        
					}
				}
			}
		}

		return max;
	}
}

/**
 * Very cleaver solution based in the stack, this is a typical advanced solution for stacks
 * 
 * if we find an element inferior to the current element we remove the peek of the stack and calculate it's width. 
 * 
 * then we multiply its height for its width and get the area.
 * 
 * We then add the next element in the array.
 * 
 * Some behaviors*
 * 
 * by default the smallest element of the array always stays in the stack.
 * 
 * The stack is populated with the indexes of the elements to help calculate the width of a given 
 * 
 * So if an element in the stack has nothing behind it 
 * it means that we are calculating from the current index to the begining
 * 
 * if we have some previous element behind it means that element is smaller and it is the end of the width
 * 
 * @author Nelson Costa
 *
 */
class MaximalRectangleSolution3 {

	// Get the maximum area in a histogram given its heights
	public int leetcode84(int[] heights) {
		Stack < Integer > stack = new Stack < > ();
		stack.push(-1);
		int maxarea = 0;
		for (int i = 0; i < heights.length; ++i) {
			//if stack peek bigger than current element in height histogram
			//do the max between the current and the previous element in the stack (width)
			while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
			{
				int firstpeek = heights[stack.pop()];
				int width =  (i - stack.peek() - 1);
				maxarea = Math.max(maxarea,firstpeek * width);
			}
			stack.push(i);
		}
		while (stack.peek() != -1)
		{
			//the remaining elements in the stack are calculated until the previous element in the stack
			int firstpeek = heights[stack.pop()];
			int width =  (heights.length - stack.peek() -1);
			maxarea = Math.max(maxarea, firstpeek * width );
		}
		return maxarea;
	}

	public int maximalRectangle(char[][] matrix) {

		if (matrix.length == 0) return 0;
		int maxarea = 0;
		int[] dp = new int[matrix[0].length];

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {

				// update the state of this row's histogram using the last row's histogram
				// by keeping track of the number of consecutive ones

				dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
			}
			// update maxarea with the maximum area from this row's histogram
			maxarea = Math.max(maxarea, leetcode84(dp));
		} return maxarea;
	}
}

/**
 * DP solution same principle as my solution, performed more efficiently
 * @author Nelson Costa
 *
 */
class MaximalRectangleSolution2 {
	public int maximalRectangle(char[][] matrix) {

		if (matrix.length == 0) return 0;
		int maxarea = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];

		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if (matrix[i][j] == '1'){

					// compute the maximum width and update dp with it
					dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

					int width = dp[i][j];

					// compute the maximum area rectangle with a lower right corner at [i, j]
					for(int k = i; k >= 0; k--){
						width = Math.min(width, dp[k][j]);
						maxarea = Math.max(maxarea, width * (i - k + 1));
					}
				}
			}
		} return maxarea;
	}
}
