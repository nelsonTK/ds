package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix
 * EASY
 * @author Nelson Costa
 *
 */
public class CountNegativeNumbersInASortedMatrix {

	public static void main(String[] args) {

		CountNegativeNumbersInASortedMatrix c = new CountNegativeNumbersInASortedMatrix();
		//0
//		int [][] a = new int [4][4];
//		a[0] = new int [] {4,  3, 2,-1};
//		a[1] = new int [] {3,  2, 1,-1};
//		a[2] = new int [] {1,  1,-1,-2};
//		a[3] = new int [] {-1,-1,-2,-3};
		

		//0
		int [][] a = new int [2][2];
		a[0] = new int [] {3, 2};
		a[1] = new int [] {1, 0};
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println("negative numbers: " + c.countNegatives(a));
	}


	/**
	 * @intuition
	 * 		This is a search problem where I want to find the first positive number before a negative number.
	 * 		then I subtract n tthe positive numbers, and that the negative numbers in the array
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
			Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	 * 
	 * @fail
	 * 		1) failed forgot about adding one to n
	 * 		
	 * 		
	 * 
	 * @alternative 
	 * 		Do linear search from right to left and stop
	 * 
	 * @time  O(mlog n)
	 * @space O(1)
	 * @bcr   O(mlog n)
	 * 
	 * @param a
	 * @return
	 */
	public int countNegatives(int[][] a) {

		int count = 0;
		int left, right, mid;
		int m = a.length - 1;
		int n = a[0].length - 1;
		
		for (int row = 0; row <= m; row++)
		{
			left = 0;
			right = n;

			while (left <= right) {
				mid = left + (right - left)/2;

				if(a[row][mid] >= 0)
				{
					left = mid + 1;
				}
				else
				{
					right = mid - 1;
				}
			}
			count += (n + 1 ) - (right + 1);
		}

		return count;
	}
}
