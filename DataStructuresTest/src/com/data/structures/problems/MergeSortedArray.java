package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * EASY
 * @author Nelson Costa
 *
 */
public class MergeSortedArray {

	public static void main(String[] args) {
		int [] nums1 = {1,2,3,0,0,0};
		int [] nums2 = {2,5,6};
		int  m = 3;
		int  n = 3;
		MergeSortedArray ms = new MergeSortedArray();
		System.out.println(Arrays.toString(nums1));
		ms.merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));


	}

	/**
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Sorted Array.
		Memory Usage: 39.2 MB, less than 5.94% of Java online submissions for Merge Sorted Array.

	 * @time mlogm
	 * @space o(1)
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge0(int[] nums1, int m, int[] nums2, int n) {
		int n2 = 0;

		if (n > 0)
		{

			for (int i = m; i < m + n; i++) {
				nums1[i] = nums2[n2];
				n2++;
			}

			Arrays.sort(nums1);
		}
	}
	
	/**
	 * no fail
	 * @space O(1)
	 * @time  O(n + m)
	 * @param a
	 * @param m
	 * @param b
	 * @param n
	 */
	public void merge(int[] a, int m, int[] b, int n) {

		int p = a.length -1;
		int i = m - 1;
		int j = n - 1;
		
		while (i >= 0 && j >= 0)
		{
			if (a[i] >= b[j])
			{
			
				a[p] = a[i--];
			}
			else
			{
				a[p] = b[j--];
			}
			p--;
		}
		
		if (j >= 0) {
			System.arraycopy(b, 0, a, 0, j + 1);
		}
	}
}
