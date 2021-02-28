package com.data.structures.problems;

import java.util.PriorityQueue;

/**
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SorttheMatrixDiagonally {

	/**
    Runtime: 6 ms, faster than 72.71% of Java online submissions for Sort the Matrix Diagonally.
Memory Usage: 40.2 MB, less than 38.44% of Java online submissions for Sort the Matrix Diagonally.
	 **/
	//failed on edge case where it is a matrix of just one column length
	//time O(N*M log (M*n))
	//space O(N*M)
	public int[][] diagonalSort(int[][] mat) {
		PriorityQueue<Integer> min = new PriorityQueue<>();
		int offset = 0;

		for(int col = 0; col < mat[0].length; col++){

			offset = 0;
			//while row below length and the target column is withing boundaries do
			for (int row = 0; row < mat.length && col + offset < mat[0].length; row++)
			{
				min.add(mat[row][col + offset]);
				offset++;
			}

			offset = 0;

			for (int row = 0; row < mat.length && col + offset < mat[0].length; row++)
			{
				mat[row][col+offset] = min.poll();
				offset++;
			}
		}

		int col = 0;
		for (int row = 1; row < mat.length; row++)
		{
			offset = 0;
			col = 0;
			while (row + offset < mat.length && col + offset < mat[0].length)
			{
				min.add(mat[row+offset][col+offset]);
				offset++;
			}

			offset = 0;
			while (row + offset < mat.length && col + offset < mat[0].length)
			{
				mat[row+offset][col+offset] = min.poll();
				offset++;
			}
		}

		return mat;
	}
}
