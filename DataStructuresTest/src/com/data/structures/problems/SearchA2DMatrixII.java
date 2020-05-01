package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SearchA2DMatrixII extends LeetCodeExercise{

	public static void main(String[] args) {
		SearchA2DMatrixII s = new SearchA2DMatrixII();
		int x = 13;
		int [][] a = stringToMatrix("[\r\n" + 
				"  [1,   4,  7, 11, 15],\r\n" + 
				"  [2,   5,  8, 12, 19],\r\n" + 
				"  [3,   6,  9, 16, 22],\r\n" + 
				"  [10, 13, 14, 17, 24],\r\n" + 
				"  [18, 21, 23, 26, 30]\r\n" + 
				"]");
		
		System.out.println("target: " + x);
		System.out.println("found: " + s.searchMatrix(a, x));
		
	}

	/**
	 * @intuition
	 * 		go up the cell if the number is bigger than target
	 * 		go right the cell if the number is smaller
	 * 		we could start at the bottom left or top right because this corners can explore the matrix properties of sorting
	 * 		in other words we can eliminate the search space by moving vertically or horizontally.
	 * 		we can't do that with top left and bottom right. can you find out why?
	 * 		because whatever direction you go you only increase or decrease the cell. so its not good to eliminate the search space.
	 * 		if not getting, go and try yourself
	 * 
	 * @score
	 * 		Runtime: 7 ms, faster than 28.87% of Java online submissions for Search a 2D Matrix II.
			Memory Usage: 51.9 MB, less than 5.66% of Java online submissions for Search a 2D Matrix II.
	 * 
	 * 
	 * @failed
	 * 		1) I missed the sign of comparision put < in both predicates
	 * 		2) didnt set the row correctly
	 * 
	 * @time  O(m + n)
	 * @space O(1)
	 * @bcr
	 * 
	 * @param a
	 * @param x
	 * @return
	 */
	public boolean searchMatrix(int[][] a, int x) {

		if (a == null || a.length == 0)
			return false;

		int m = a.length - 1;
		int n = a[0].length - 1;
		int row = m, col = 0;

		while (row <= m && row >= 0 && col <= n && col >= 0)
		{
			if(a[row][col] < x)
			{
				col++;
			}
			else if(a[row][col] > x)
			{
				row--;
			}
			else if (a[row][col] == x)
			{
				return true;
			}
		}

		return false;
	}

}
