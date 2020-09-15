package com.data.structures.problems.contest;

/**
 * contest 206
 * https://leetcode.com/problems/special-positions-in-a-binary-matrix
 * EASY
 * @author Nelson Costa
 *
 */
public class SpecialPositionsinaBinaryMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Special Positions in a Binary Matrix.
	 *		Memory Usage: 39.8 MB, less than 75.00% of Java online submissions for Special Positions in a Binary Matrix.
	 * 
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
		public int numSpecial(int[][] mat) {

			int answer = 0;

			boolean [] rows = new boolean[mat.length];
			boolean [] cols = new boolean[mat[0].length];

			for(int row = 0; row < mat.length; row++)
			{   
				int colCount = 0;
				for (int col = 0; col < mat[row].length; col++)
				{
					if (mat[row][col] == 1)
					{
						colCount++;

						if (colCount > 1)
						{
							break;
						}
					}
				}
				if (colCount == 1)
					rows[row] = true;
			}


			for (int col = 0; col < mat[0].length; col++)
			{
				int rowCount = 0;
				for (int row = 0; row < mat.length; row++)
				{

					if (mat[row][col] == 1)
					{
						rowCount++;

						if (rowCount > 1)
						{
							break;
						}
					}
				}
				if (rowCount == 1)
					cols[col] = true;
			}


			for (int r = 0; r < mat.length; r++)
			{
				for(int c = 0; c < mat[r].length; c++)
				{
					if(rows[r] == true && cols[c] == true && mat[r][c] == 1)
					{
						answer++;
						break;
					}
				}
			}

			return answer;
		}
	}

}
