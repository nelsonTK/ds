package com.data.structures.problems;

/**
 * https://leetcode.com/problems/flood-fill/
 * EASY
 * @author Nelson Costa
 *
 */
public class FloodFill {

	public static void main(String[] args) {
		//May Challenge
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	int sourceColor = 0;
	boolean [][] visited;

	
	/**
	 * @intuition
	 * 		Nothing particularly fancy was applied
	 * 		but I had some serious lack of attention...
	 * 
	 * @score
			Runtime: 0 ms, faster than 100.00% of Java online submissions for Flood Fill.
			Memory Usage: 40.3 MB, less than 89.47% of Java online submissions for Flood Fill.
			
	 * @fail 
        1) I put the wrong limit for column it should have been img[0].length instead of img.length
        2) forgot to mark visited nodes
	 * 
	 * @time	O(N) I believe this is O(N) because we only go though a node once
	 * @space	O(N) we can have the whole matrix in the stack
	 * @bcr
	 * 
	 * @param img
	 * @param r
	 * @param c
	 * @param newClr
	 * @return
	 */
	public int[][] floodFill(int[][] img, int r, int c, int newClr) { 

		sourceColor = img[r][c];
		visited = new boolean[img.length][img[0].length];

		flood(img, r, c, newClr);

		return img;
	}

	private void flood(int[][] img, int r, int c, int newClr)
	{

		if (r >= img.length || r < 0 || c < 0 || c >= img[0].length)
			return;

		if (img[r][c] != sourceColor)
			return;

		if (visited[r][c])
			return;

		visited[r][c] = true;

		img[r][c] = newClr;

		flood(img, r-1, c  , newClr);
		flood(img, r  , c+1, newClr);
		flood(img, r+1, c  , newClr);
		flood(img, r  , c-1, newClr);
	}
}
