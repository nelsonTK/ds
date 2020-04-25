package com.data.structures.problems;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/largest-unique-number/
 * EASY
 * @author Nelson Costa
 *
 */
public class LargestUniqueNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] a = {5,7,3,9,4,9,8,3,9,1};
		//				a = new int [] {9,9,8,8};
		LargestUniqueNumber l = new LargestUniqueNumber();
		System.out.println(l.largestUniqueNumber(a));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	
	/**
	 * I use a TreeSet because I want quick access to the maximum number, I also thought in Priority Queue but rejected that idea
	 * Because I think Treeset has O(1) to the last
	 * 
	 * @failed Didnt think  in the case of odd repetitions
	 * @time I think it is N*LogN
	 * @space O(N) I think I ant go less than this
	 * @param a
	 * @return
	 */
	public int largestUniqueNumber0(int[] a) {

		if (a == null || a.length == 0)
			return -1;

		TreeSet<Integer> t = new TreeSet<>();

		for (int i = 0; i < a.length; i++) {

			if (t.contains(a[i])) {
				t.remove(a[i]);
			}
			else
			{
				t.add(a[i]);
			}
		}

		Integer result = t.pollLast();

		return result != null ? result : - 1;
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	
	/**
	 * 		Runtime: 7 ms, faster than 5.91% of Java online submissions for Largest Unique Number.
			Memory Usage: 39.7 MB, less than 33.33% of Java online submissions for Largest Unique Number.
	 * 
	 * 
	 * @time  O(NLogN)
	 * @space O(N) => 2N
	 * @param a
	 * @return
	 */
	public int largestUniqueNumber1(int[] a) {

		if (a == null || a.length == 0)
			return -1;

		TreeMap<Integer, Integer> tracker = new TreeMap<>((c, b) -> b - c);
		TreeSet<Integer> max = new TreeSet<Integer>();

		for (int i = 0; i < a.length; i++) {

			if (tracker.containsKey(a[i]) & max.contains(a[i])) {
				max.remove(a[i]);
			}
			else if (!tracker.containsKey(a[i]) && !max.contains(a[i]))
			{
				tracker.put(a[i], 1);
				max.add(a[i]);
			}
		}

		Integer result = max.pollLast();

		return result != null ? result : - 1;
	}

	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * 
	 	Runtime: 3 ms, faster than 58.39% of Java online submissions for Largest Unique Number.
		Memory Usage: 38.9 MB, less than 33.33% of Java online submissions for Largest Unique Number.

	 * @time O(N)
	 * @space O(N)
	 * @param a
	 * @return
	 */
	public int largestUniqueNumber2(int[] a) {

		HashMap<Integer, Integer> map = new HashMap<>();
		int result = -1;

		for (int i : a)
		{
			if (map.containsKey(i))
			{
				map.put(i, map.get(i) + 1);
			}else
			{
				map.put(i, 1);
			}
		}

		for (Integer i : map.keySet())
		{
			if (map.get(i) == 1 && i > result)
				result = i;
		}

		return result;
	}

	/*********************************
	 * SOLUTION 4
	 ********************************/
	
	/**
	 * its constant because of the input restrictions
	 * 
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Largest Unique Number.
			Memory Usage: 39.1 MB, less than 33.33% of Java online submissions for Largest Unique Number.
	 * 
	 * 
	 * @time O(1)
	 * @space O(1)
	 * @param a
	 * @return
	 */
	public int largestUniqueNumber(int[] a) {
		int [] n = new int [1001];
		
		if (a == null)
			return -1;
		
		for (int i = 0; i < a.length; i++) {
			
			n[a[i]]++;
		}
		
		for (int i = n.length - 1; i > 0; i--)
		{
			if (n[i] == 1)
				return i;
		}

		return -1;
	}
}