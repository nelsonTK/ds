package com.data.structures.problems;

/**
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * EASY
 * @author Nelson Costa
 *
 */
public class PeakIndexInAMountainArray {

	public static void main(String[] args) {
		PeakIndexInAMountainArray p = new PeakIndexInAMountainArray();
//		int [] a = {0,1,0};
		int [] a = {0,2,1,0};
		System.out.println("peak: " + p.peakIndexInMountainArray(a));
	}

	/**
	 * 
	 * TOO EASY
	 * 
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Peak Index in a Mountain Array.
			Memory Usage: 43.8 MB, less than 6.00% of Java online submissions for Peak Index in a Mountain Array.
	 * 
	 * @fail mistake, I was returning the value instead of the index
	 * 
	 * @bcr o(logn)
	 * @space o(1)
	 * @time o(logn)
	 * 
	 * @param a
	 * @return
	 */
	public int peakIndexInMountainArray(int[] a) {

		if(a == null || a.length == 0)
			throw new IllegalArgumentException();

		int low = 0;
		int high = a.length - 1;
		int mid;

		while (low < high) {
			mid = low + (high - low)/2;

			if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1])
			{
				return mid;
			}
			else if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1])
			{
				low = mid + 1;
			}
			else
				high = mid;
		}

		return low;
	}

}
