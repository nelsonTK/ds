package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/design-hit-counter
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DesignHitCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @intuition
	 * 		have a list which stores all the historical values, then perform binary search to find the last valid value in the time stamp.
	 * 		and then perform basic math.
	 * 
	 * @score
	 *		Runtime: 0 ms, faster than 100.00% of Java online submissions for Design Hit Counter.
	 *		Memory Usage: 37.3 MB, less than 80.92% of Java online submissions for Design Hit Counter.
	 * 
	 * @time
	 * 		hit
	 * 			O(1)
	 * 
	 * 		getHits
	 * 			O(log n)
	 * 
	 * @space
	 * 		Hit
	 * 			O(n)
	 * 
	 * 		getHits
	 * 			O(1)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class HitCounter {

		List<Integer> history;
		Integer window;
		/** Initialize your data structure here. */
		public HitCounter() {
			history = new ArrayList<>();
			window = 5 * 60;
		}

		/** Record a hit.
	        @param timestamp - The current timestamp (in seconds granularity). */
		public void hit(int timestamp) {
			history.add(timestamp);
		}

		/** Return the number of hits in the past 5 minutes.
	        @param timestamp - The current timestamp (in seconds granularity). */
		public int getHits(int timestamp) {

			int referenceTimeStamp = timestamp - window < 0 ? 0 : timestamp - window;

			int l = 0, h = history.size() - 1, mid;

			while (l <= h)
			{
				mid = l + (h - l)/2;

				if (history.get(mid) > referenceTimeStamp)
				{
					h = mid - 1;
				}	
				else
				{
					l = mid + 1;
				}
			}

			if (h < 0)
			{
				return history.size();
			}
			else
			{
				return history.size() - l;
			}
		}
	}

}
