package com.data.structures.problems;

/**
 * https://leetcode.com/problems/first-bad-version/
 * EASY
 * @author Nelson Costa
 *
 */
public class FirstBadVersion {

	public static void main(String[] args) {
		FirstBadVersion f = new FirstBadVersion();
		int n = 91000;

		System.out.println("latest version: " + n);
		System.out.println("first bad version: " + FIRST_BAD_VERSION);
		System.out.println("First bad Version: " + f.firstBadVersion(n));
	}


	private static final int FIRST_BAD_VERSION = 9890;
	
	/**
	 * 
	 * @intuition
	 * 		search space 1 to n. where n is the latest version.
	 * 		so I want to find a number.
	 * 		This list is implicitly sorted
	 * 		So binary search is the better choice than doing linear search.
	 * 
	 * @failed 
	 * 		1) miss calculated which value I should return, I was returning the last good instead of first bad. it was a mister in high assignment
	 * 
	 * @score
	 * 		Runtime: 12 ms, faster than 98.52% of Java online submissions for First Bad Version.
			Memory Usage: 36.3 MB, less than 5.63% of Java online submissions for First Bad Version.
	 * 
	 * @time  O(logn)
	 * @space O(1)
	 * @bcr	  O(logn)
	 * 
	 * @param n
	 * @return
	 */
	public int firstBadVersion(int n) {

		if (n <= 0)
			throw new IllegalArgumentException();

		int low = 1;
		int high = n;
		int mid;

		while (low < high)
		{
			mid = low + (high - low)/2;

			if (isBadVersion(mid)) {
				high = mid;
			}else {
				low = mid + 1;
			}
		}
		
		return low;
	}

	boolean isBadVersion(int version) {
		
		return version >= FIRST_BAD_VERSION ? true : false;
	}
}
