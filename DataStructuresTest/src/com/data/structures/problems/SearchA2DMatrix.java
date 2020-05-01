package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

public class SearchA2DMatrix extends LeetCodeExercise{

	public static void main(String[] args) {
		
		SearchA2DMatrix s = new SearchA2DMatrix();
		int [][] m = stringToMatrix(
				"[" + 
				"  [1,   3,  5,  7]," + 
				"  [10, 11, 16, 20]," + 
				"  [23, 30, 34, 50]" + 
				"]");
		
		int x = 51;
		
		System.out.println(x);
		System.out.println("has x: "+ s.searchMatrix(m, x));
	}

	/**
	 * @intuition
	 * 		The intuition of this is that this is like an ordinary array but masked as a Matrix
	 * 		as a result if we find how to decode index to row and column we can find our way into the correct solution
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
			Memory Usage: 39.3 MB, less than 22.73% of Java online submissions for Search a 2D Matrix.
	 * 
	 * @fail
	 * 		1) forgot to subtract one to the end result
	 * 		2) failed at the time complexity
	 * 
	 * @time	O(log mn)
	 * @space	O(1)
	 * @bcr 	O(log mn)
	 * 
	 * @param m
	 * @param x
	 * @return
	 */
	public boolean searchMatrix(int[][] m, int x) {

		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0)
			return false;

		int low = 0;
		int high = m.length * m[0].length - 1;
		int mid, row, col;
		int colSize = m[0].length;

		while (low <= high)
		{
			mid = low + (high - low)/2;
			row = mid / colSize;
			col = mid % colSize;
			
			if (m[row][col] == x)
			{
				return true;
			}
			else if (m[row][col] > x)
			{
				high = mid - 1;
			}
			else
			{
				low = mid + 1;
			}
		}

		return false;
	}
}
