package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/time-based-key-value-store
 * MEDIUM	
 * @author Nelson Costa
 *
 */
public class TimeBasedKeyValueStore {

	public static void main(String[] args) {
		TimeMap obj = new TimeMap();
		String key = "";
		String value = "";
		int timestamp = 1;
		obj.set(key,value,timestamp);

		System.out.println(obj.get(key,timestamp));

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The goal here is to have a hashmap with a list of entries.
	 * 		The entries are sorted so I just have to apply binary search on the list of entries at each get.
	 * 
	 * @score
	 * 		Runtime: 125 ms, faster than 91.48% of Java online submissions for Time Based Key-Value Store.
	 *		Memory Usage: 113.2 MB, less than 97.07% of Java online submissions for Time Based Key-Value Store.
	 * 
	 * @fail
	 * 		1) infinite loop because I was not covering the case where the left target time stamp is smaller than the available
	 *
	 * 
	 * 
	 * @time
	 * 		get()
	 * 			O(Logn)
	 * 
	 * 		set
	 * 			O(1)
	 * 
	 * @space
	 * 		set and get O(N)
	 * 
	 * @author Nelson Costa
	 *
	 */
	static class TimeMap{
		HashMap<String, ArrayList<Entry>> map;

		/** Initialize your data structure here. */
		public TimeMap() {
			map = new HashMap<>();
		}

		public void set(String key, String value, int timestamp) {
			map.computeIfAbsent(key, k -> new ArrayList<Entry>()).add(new Entry(timestamp, value));
		}

		public String get(String key, int timestamp) {

			if (map.getOrDefault(key,  new ArrayList<Entry>()).size() == 0)
				return "";

			/** Binary search, this could have been in another method but I was cooking */
			List<Entry> list = map.get(key);
			int l = 0, r= list.size() - 1, mid;
			Entry cur;

			while (l <= r)
			{
				mid = l + (r - l)/2;
				cur = list.get(mid);

				if (cur.time == timestamp)
				{
					return cur.val; 
				}
				else if (cur.time < timestamp)
				{
					l = mid + 1;
				}
				else
				{
					r = mid - 1;
				}
			}

			if (r < 0)
				return "";
			else
				return list.get(r).val;
		}

		class Entry{
			int time;
			String val;

			Entry(int t, String v){
				time = t;
				val = v;
			}
		}
	}
}




/*********************
 * OTHERS SOLUTIONS
 *********************/


/**
 * Is the same concept than my solution, but uses Collection.binarysearch();
 * 
 * @author Nelson Costa
 *
 */
class TimeMapSolution1 {
	Map<String, List<Pair<Integer, String>>> M;

	public TimeMapSolution1() {
		M = new HashMap();
	}

	public void set(String key, String value, int timestamp) {
		if (!M.containsKey(key))
			M.put(key, new ArrayList<Pair<Integer, String>>());

		M.get(key).add(new Pair(timestamp, value));
	}

	public String get(String key, int timestamp) {
		if (!M.containsKey(key)) return "";

		List<Pair<Integer, String>> A = M.get(key);
		int i = Collections.binarySearch(A, new Pair<Integer, String>(timestamp, "{"),
				(a, b) -> Integer.compare(a.getKey(), b.getKey()));

		if (i >= 0)
			return A.get(i).getValue();
		else if (i == -1)
			return "";
		else
			return A.get(-i-2).getValue();
	}
}


/**
 * Solution that uses TreeMap for having the entries sorted, 
 * 
 * and then for get uses a function floorKey to get the greatest value below a key
 * 
 * Its very cleaver solution if you ask me
 * 
 * Does the same and has much less code
 * 
 * @author Nelson Costa
 *
 */
class TimeMapSolution2 {
	Map<String, TreeMap<Integer, String>> M;

	public TimeMapSolution2() {
		M = new HashMap();
	}

	public void set(String key, String value, int timestamp) {
		if (!M.containsKey(key))
			M.put(key, new TreeMap());

		M.get(key).put(timestamp, value);
	}

	public String get(String key, int timestamp) {
		if (!M.containsKey(key)) return "";

		TreeMap<Integer, String> tree = M.get(key);
		Integer t = tree.floorKey(timestamp);
		return t != null ? tree.get(t) : "";
	}
}