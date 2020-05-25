package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/maximal-square/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MaximalSquare extends LeetCodeExercise{
	static MaximalSquareSolution2 m2 = new MaximalSquareSolution2();
	static MaximalSquare m = new MaximalSquare();
	public static void main(String[] args) {
		
		char [][] matrix = new char[4][5];		
		matrix[0] = new char []{'1','0','1','0','0'};	
		matrix[1] = new char []{'1','0','1','1','1'};	
		matrix[2] = new char []{'1','1','1','1','1'};	
		matrix[3] = new char []{'1','0','0','1','0'};
		
		System.out.println(m2.maximalSquare(matrix));
		
		matrix = new char[1][1];	
		matrix[0] = new char []{'0'};
		
		System.out.println(m.maximalSquare(matrix));
	}

    /**
     * @intuition
     * 		My intuition is:
     * 			you find a square if your next cell, bottom cell and diagonal cell are 1's
     * 			from there you use dp to calculate  each square's size.
     * 			
     * 			the size of the square, is determined by the min between, right cell, bottom cell and diagonal cell + 1.
     * 			just try to rationalize about this.
     * 
     * 			1 means the square is size 1. 2 means the squase is size 2.
     * 
     * 			each dp cell represents the top left corner of the square. and its value is the length of its edge.
     * 
     * @alternatives
     * 		1) I could do Spiral search on each one
     * 		2) I could have implemented some more unefficient dp strategy were each cell indicates how many nodes are below counting itself.
     * 			from there we would need to traverse the matrix, it was a O(N^2)
     * 
     * @score
			Runtime: 5 ms, faster than 23.92% of Java online submissions for Maximal Square.
			Memory Usage: 42.8 MB, less than 100.00% of Java online submissions for Maximal Square.
     * 
    @debug
        yes, contest like debug
    
    @fail
        1) the for loop was incrementing instead of decrementing
        2) I was not adding one to the square size. just the minimum of right, diagonal and bottom
        3) the for loop signals were wrong, I had them right in the paper though.
        4) dp size were wrong by one value
        5) forgot to multiply the max by 2, inorder to return the area
        5.1) the area was badly calculared it was max * max and not times 2. what a dumb error.
        6) failed for array of length 1. I put a too strick guard

    @time 	O(mn)
    @space	O(mn)
    */
    public int maximalSquare(char[][] matrix) {
        
        if(matrix == null || matrix.length == 0)
        {
            return 0;
        }
        
        int rLen = matrix.length - 1;
        int cLen = matrix[0].length - 1;
        int right, diagonal, bottom, max = 0;         
        int [][] dp = new int [rLen + 1][cLen + 1];
        
       
        for (int r = rLen; r >= 0; r--)
        {

            for (int c = cLen; c >= 0; c--)
            {
                
                if (matrix[r][c] != '0'){
                    right = diagonal = bottom = 0;
                    
                    
                    if(c + 1 <= cLen)
                    {
                        right = dp[r][c + 1];
                    }
                    
                    if(r + 1 <= rLen)
                    {
                        bottom = dp[r + 1][c];
                    }
                    
                    if(c + 1 <= cLen && r + 1 <= rLen)
                    {
                        diagonal = dp[r + 1][c + 1];
                    }
                    
                    dp[r][c] = Math.min(right, Math.min(diagonal, bottom)) + 1;
                    
                    max = Math.max(dp[r][c], max);
                    
                }
                
            }
        }
        
        return max == 1? max : max * max;
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * 
 * the intuition is similar to tehe previous solution
 * but a step higher, very cleaver. It uses only the space O(N) for column length
 * 
 * 	prev stores the diagonal
 * 	dp[j] has the previous value
 * 	dp[j-1] stores de value behind
 * 
 * @time  O(mn)
 * @space O(n)
 * @author Nelson Costa
 *
 */
class MaximalSquareSolution2 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}

/**
 * Equivalent to my solution but starting from the begining
 * 
 * @time	O(mn)
 * @space	O(mn)
 * @author Nelson Costa
 *
 */
class MaximalSquareSolution1 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
