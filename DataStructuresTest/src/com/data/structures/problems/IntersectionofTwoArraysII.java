package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * EASY
 * @author Nelson Costa
 *
 */
public class IntersectionofTwoArraysII {

	public static void main(String[] args) {

		//		int[] m = {1,2,2,1}, n = {2,2};
		int[] m = {4,9,5}, n = {9,4,9,8,4};
		IntersectionofTwoArraysII i = new IntersectionofTwoArraysII();

		System.out.println(Arrays.toString(i.intersect(m, n)));
	}
	
	/**************************
	 * SOLUTION 1
	 *************************/
	
	/**
	 * @intuition
	 * 		The goal here is to hashmap the smaller table and iterate the larger.
	 * 		I decided to hashmap the smaller table because the smaller table would have less probability of having collisions
	 * 		In my opinion, this was base on my knownledge. in other words I'm defending that put is slower than get hashmap method.
	 * 		
	 *
	 * @score
	 * 		Runtime: 4 ms, faster than 29.39% of Java online submissions for Intersection of Two Arrays II.
			Memory Usage: 39.9 MB, less than 6.45% of Java online submissions for Intersection of Two Arrays II.

	 *
	 * @optimization
	 * 		I believe its possible to do better with an array
	 * 
	 * @fail
	 * 
	 * 
	 * @time O(M + N)
	 * @space O (M) M equals to smaller
	 * @bcr  O(M + N)
	 * 
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int[] intersect(int[] m, int[] n) {

		int [] smaller;
		int [] larger;

		if (m == null || n == null || m.length == 0 || n.length == 0)
		{
			return new int[] {};
		}


		if (m.length <= n.length)
		{
			smaller = m;
			larger = n;
		}
		else
		{

			smaller = n;
			larger = m;
		}

		List<Integer> ans = new ArrayList<Integer>();

		HashMap<Integer, Integer> smlMap = new HashMap<>();

		for(int element : smaller)
		{			
			if (smlMap.containsKey(element))
			{
				smlMap.put(element, smlMap.get(element) + 1);
			}
			else
			{
				smlMap.put(element, 1);
			}
		}

		Integer elementsLeft;
		for (int element : larger) {
			elementsLeft = smlMap.get(element);
			//this could absolutly be cleaner 

			if (elementsLeft == null)
			{
				continue;
			}else if (elementsLeft - 1 < 0)
			{
				smlMap.remove(element);
			}
			else{
				smlMap.put(element, smlMap.get(element) - 1);
				ans.add(element);
			}
		}

		return ans.stream().mapToInt(Integer::intValue).toArray();
	}

	/**************************
	 * SOLUTION 2
	 *************************/
	
	/**
	 * @intuition
	 * 		The goal here is to hashmap the smaller table and iterate the larger.
	 * 		I decided to hashmap the smaller table because the smaller table would have less probability of having collisions
	 * 		In my opinion, this was base on my knownledge. in other words I'm defending that put is slower than get hashmap method.
	 * 		
	 *
	 * @score
	 * 		Runtime: 4 ms, faster than 29.39% of Java online submissions for Intersection of Two Arrays II.
			Memory Usage: 39.9 MB, less than 6.45% of Java online submissions for Intersection of Two Arrays II.

	 *
	 * @optimization
	 * 		instead of removing from hashmap I'm just reducing the counter
	 * 		Achieved the same performance
	 * 
	 * @fail
	 * 
	 * 
	 * @time O(M + N)
	 * @space O (M) M equals to smaller
	 * @bcr  O(M + N)
	 * 
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int[] intersect1(int[] m, int[] n) {

		int [] smaller;
		int [] larger;

		if (m == null || n == null || m.length == 0 || n.length == 0)
		{
			return new int[] {};
		}


		if (m.length <= n.length)
		{
			smaller = m;
			larger = n;
		}
		else
		{

			smaller = n;
			larger = m;
		}

		List<Integer> ans = new ArrayList<Integer>();

		HashMap<Integer, Integer> smlMap = new HashMap<>();

		for(int element : smaller)
		{			
			if (smlMap.containsKey(element))
			{
				smlMap.put(element, smlMap.get(element) + 1);
			}
			else
			{
				smlMap.put(element, 1);
			}
		}

		Integer elementsLeft;
		for (int element : larger) {
			elementsLeft = smlMap.get(element);

			//Optimization attempt
			if (elementsLeft != null && elementsLeft > 0)
			{
				smlMap.put(element, smlMap.get(element) - 1);
				ans.add(element);
			}
		}
		return ans.stream().mapToInt(Integer::intValue).toArray();
	}



	/**************************
	 * SOLUTION 3
	 *************************/
	
	/**
	 * @intuition
	 * 		The intuition here was to sort the smaller of the arrays and then iterate the larger table, and binary search it's elements.
	 * 		I expect a lower score than the previous solution
	 * 
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 12.04% of Java online submissions for Intersection of Two Arrays II.
			Memory Usage: 40.2 MB, less than 6.45% of Java online submissions for Intersection of Two Arrays II.


	 * @time  O(mlogm + n*logm)
	 * @space O(1)
	 * @bcr   O(M + N)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int[] intersect0(int[] m, int[] n) {

		int [] smaller;
		int [] larger;

		if (m == null || n == null || m.length == 0 || n.length == 0)
		{
			return new int[] {};
		}



		if (m.length <= n.length)
		{
			smaller = m;
			larger = n;
		}
		else
		{

			smaller = n;
			larger = m;
		}

		Arrays.sort(smaller);
		boolean [] visited = new boolean[smaller.length];
		List<Integer> ans = new ArrayList<>();

		int low, high, mid;
		for (int i = 0; i < larger.length; i++) {

			low = 0;
			high = smaller.length - 1;

			while (low <= high)
			{
				mid = low + (high - low)/2;

				if (smaller[mid] == larger[i])
				{
					if (visited[mid])
					{
						low = mid + 1;
					}
					else
					{
						high = mid - 1;
					}
				}
				else if (smaller[mid] < larger[i])
				{
					low = mid + 1;
				}
				else
				{
					high = mid - 1;
				}
			}

			if (low < smaller.length && smaller[low] == larger [i])
			{
				visited[low] = true;
				ans.add(smaller[low]);
			}
		}

		return ans.stream().mapToInt(Integer::intValue).toArray();
	}

}

/**
 * 
 * rewrites the smaller array...
 * very cleaver
 * it's equivalent to my solution but with much better performance
 * 
 * @score
 * 		Runtime: 2 ms, faster than 97.66% of Java online submissions for Intersection of Two Arrays II.
		Memory Usage: 39.7 MB, less than 6.45% of Java online submissions for Intersection of Two Arrays II.
 * 
 * @author Nelson Costa
 *
 */
class IntersectionofTwoArraysIISolution{
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			return intersect(nums2, nums1);
		}
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int n : nums1) {
			m.put(n, m.getOrDefault(n, 0) + 1);
		}
		int k = 0;
		for (int n : nums2) {
			int cnt = m.getOrDefault(n, 0);
			if (cnt > 0) {
				nums1[k++] = n;
				m.put(n, cnt - 1);
			}
		}
		return Arrays.copyOfRange(nums1, 0, k);
	}
}