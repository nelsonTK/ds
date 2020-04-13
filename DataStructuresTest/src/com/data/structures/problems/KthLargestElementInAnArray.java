package com.data.structures.problems;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class KthLargestElementInAnArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		KthLargestElementInAnArray k = new KthLargestElementInAnArray();
		int kth = 2;
		int [] a = {3,2,1,5,6,4};
//		a = new int [] {3,2,3,1,2,4,5,5,6};
		System.out.println(k.findKthLargest(a, kth));

	}

	/**
	 * Nothing fancy here was applied...
	 * Just tried to apply the best data structures
	 * 
	 * @failed, I read the question wrongly
	 * 
	 * @space O(N)
	 * @time  O(N)
	 * 
	 * @param a array with numbers
	 * @param k	k largest indicator
	 * @return
	 */
	public int findKthLargest0(int[] a, int k) {

		if (a == null || k < 1)
		{
			throw new IllegalArgumentException();
		}

		TreeSet<Integer> tree = new TreeSet<Integer>();
		int i = 0;

		while (i < a.length && tree.size() < k)
		{
			if (!tree.contains(a[i]))
				tree.add(a[i]);
			i++;
		}

		for (int j = i; j < a.length; j++)
		{
			if (a[j] > tree.first() && !tree.contains(a[j])) {
				tree.pollFirst();
				tree.add(a[j]);
			}
		}

		return tree.first();
	}

	/**
	 * 		Runtime: 1 ms, faster than 97.34% of Java online submissions for Kth Largest Element in an Array.
			Memory Usage: 40.1 MB, less than 5.18% of Java online submissions for Kth Largest Element in an Array.
	 * 
	 * @time complexity  worst case n^2 average nlogn
	 * @space O(1) 
	 * 
	 * @param a
	 * @param k
	 * @return
	 */
	public int findKthLargest1(int[] a, int k) {
		if (a == null || k < 1)
		{
			throw new IllegalArgumentException();
		}

		Arrays.sort(a);
		return a[a.length-k];
	}


	/**
	 * 
	 * OTIMIZATION ATTEMPT
	 * 		Runtime: 3 ms, faster than 70.06% of Java online submissions for Kth Largest Element in an Array.
			Memory Usage: 39.4 MB, less than 10.37% of Java online submissions for Kth Largest Element in an Array.
	 * 
	 * 
	 * 
	 * 
	 * Solution with priority queue accomodating the changes to support Kth Largest and not the Kth Distinct Largest
	 * 
	 * @time  O(N * log K + N) N Log K in th end. the log n is from adding in the priority queue, 
	 * for small k's works very well for large ks probably not
	 * 
	 * @space O(N)
	 * 
	 * @param a array with numbers
	 * @param k	k largest indicator
	 * @return
	 */
	public int findKthLargest(int[] a, int k) {

		if (a == null || k < 1)
		{
			throw new IllegalArgumentException();
		}

		PriorityQueue<Integer> priority = new PriorityQueue<Integer>();
		int i = 0;

		while (i < a.length && priority.size() < k)
		{
			priority.add(a[i]);
			i++;
		}

		for (int j = i; j < a.length; j++)
		{
			if (a[j] > priority.peek()) {
				priority.poll();
				priority.add(a[j]);
			}
		}

		return priority.peek();
	}
}
