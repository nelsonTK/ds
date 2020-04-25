package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/search-insert-position/
 * EASY
 * @author Nelson Costa
 *
 */
public class SearchInsertPosition {

	public static void main(String[] args) {
		SearchInsertPosition s = new SearchInsertPosition();
		int [] a = {1,3,5,6};
		int x = 5;
//		x = 2;
//		x = 7;
//		x = 0;

		System.out.println(Arrays.toString(a));
		System.out.println("target: " + x);
		System.out.println(s.searchInsert(a, x));
	}

	/**
	 * @intuition
	 * 		Straitforward Binary Search
	 * 		this is just a simple search with no duplicates
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
			Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Search Insert Position.
	 * 
	 * @alternative
	 * 		I could have done it with linear time also but is worst
	 * 
	 * @fail 
	 * 1) I used if instead of a while. a lack of attention. kinda hurry mistake
	 * 2) failed again. had to had into account the scenarios where the answer is beyond array size
	 * 
	 * 
	 * @time  O(Log N)
	 * @space O(1)
	 * @bcr   O(Log N) 
	 * 
	 * @param a array
	 * @param x target
	 * @return
	 */
	public int searchInsert(int[] a, int x) {

		if (a == null || a.length == 0)
			return 1;
		int size = a.length - 1;

		if (x > a[size])
			return ++size;

		int left = 0;
		int right = size;
		int mid;

		while(left < right)
		{
			mid = left + (right - left)/2;

			if (a[mid] == x)
			{
				return mid;
			}
			else if (a[mid] > x) {
				right = mid;
			} 
			else 
			{
				left = mid + 1;
			}
		}

		return left;
	}
}
