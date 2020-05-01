package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/rotate-image/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RotateImage extends LeetCodeExercise{

	public static void main(String[] args) {

		RotateImage i = new RotateImage();
		RotateImageSolution1 s1 = new RotateImageSolution1();
		int [][] m = stringToMatrix("[\r\n" + 
				"  [7,4,1],\r\n" + 
				"  [8,5,2],\r\n" + 
				"  [9,6,3]\r\n" + 
				"]");


		m = stringToMatrix("[\r\n" + 
				"  [ 5, 1, 9,11],\r\n" + 
				"  [ 2, 4, 8,10],\r\n" + 
				"  [13, 3, 6, 7],\r\n" + 
				"  [15,14,12,16]\r\n" + 
				"]");

		m = stringToMatrix("[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]");

		printMatrix(m);
						
		i.rotate(m);

		System.out.println();

		printMatrix(m);




	}


	/**
	 * 
	 * @intuition
	 * 		I rotate a cell and all the cells in that rotation path.
	 * 		I rotate all elements from a line and then I move to the row below and decrease search space by 2. 
	 * 		1 in the start and other in the end
	 * 		I Rotate that line again. untill we reach the middle of the array where all elements will be rotated.
	 * 
	 * 		The most dificult here was to have all the calculations right. I messed up a little bit because I didn't follow always the same logic in my sketchbook
	 * 
	 * 
	 * @optimizations
	 * 		I can further optimize the rotation, which is by no means clean
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
			Memory Usage: 39.8 MB, less than 5.77% of Java online submissions for Rotate Image.
	 * 
	 * @fail
	 * 		1) failed in the rotations...
	 * 		2) failed because one element rotation was missing, col and row was swapped
	 * 		3) I laked an absolute formula 
	 * 		4) in the same place where it lacked a abs it lacked a parenthesis because of - sign
	 * 
	 * 
	 * @time  O(N^2)
	 * @space O(1)
	 * @bcr   O(N)
	 * 
	 * @param m
	 */
	public void rotate(int[][] m) {


		if (m == null || m.length == 0)
			return;

		int start = 0;
		int end = m[0].length - 1;

		int col = 0; //relativeX
		int row = 0; //relativeY
		int size = 0; //relative size;
		int tmp = 0;
		for (int i = 0; i <= m.length-1/2; i++)
		{
			for (int j = start; j < end; j++) {

				size = end - start;

				if (size == 0)
					break;

				//rotate top to right
				col = j + size - (j - start);
				row = i +  (j - start);
				tmp = m[row][col];
				m[row][col] = m[i][j];
				m[i][j] = tmp;


				//rotate right to bottom
				col = col - (j - start);
				row = row + size - (j - start);
				tmp = m[row][col];
				m[row][col] = m[i][j];
				m[i][j] = tmp;

				//rotate bottom to left
				col = col - Math.abs(size - (j - start));
				row = row - (j - start);
				tmp = m[row][col];
				m[row][col] = m[i][j];
				m[i][j] = tmp; //sorting the last element (left to top)

				// rotate left to top
				// it is already in place because we passed the value tmp to m[i][j] 
				// which is the original position

			}

			start++;
			end--;

			if (start > end)
				break;
		}
	}
}

/**
 * Is the Best Solution of the solutions just uses one pass
 * The logic is very similar to my solution but much more efficient in the rotation
 * 
 * Other than that the main difference is that conceptually I rotate a row and this solution creates rectangle of height n+1/2 and width n/2 and rotates that rectangle only
 * 
 * It has much less operations per iteration than my solution
 * @author Nelson Costa
 *
 */
class RotateImageSolution1 {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < (n + 1) / 2; i ++) {
			for (int j = 0; j < n / 2; j++) {
				int temp = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
				matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
				matrix[j][n - 1 - i] = matrix[i][j];
				matrix[i][j] = temp;
			}
		}
	}
}

/**
 * Transpose and Rotate
 * Its an interesting concept
 * That I was unaware of
 * 
 * @author Nelson Costa
 *
 */
class RotateImageSolution2 {
	
	public void rotate(int[][] matrix) {
		int n = matrix.length;

		// transpose matrix
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int tmp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = tmp;
			}
		}
		// reverse each row
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = tmp;
			}
		}
	}
}