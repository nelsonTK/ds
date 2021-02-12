package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 	@intuition
	 * 		just a sliding window problem with two pointers
	 * 
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 83.19% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
	 * 		Memory Usage: 39.1 MB, less than 49.18% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1) because hashmap never has more than 3
	 ****/
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		//tests
		// s = null; 1; 0
		// only one char

		if (s == null)
			return 0;

		int L = 0; int R = 0, maxSize = 0, n = s.length();
		char c;
		HashMap<Character, Integer> charCount = new HashMap<>();

		while (R < n)
		{
			c = s.charAt(R);

			charCount.put(c, charCount.getOrDefault(c,0) + 1);

			while (charCount.size() > 2 && L < R)
			{
				c = s.charAt(L);
				//reduce last element
				if(charCount.get(c) == 1)
				{
					charCount.remove(c);
				}
				else
				{
					charCount.put(c, charCount.get(c) - 1);
				}

				L++;

			}

			maxSize = Math.max(maxSize, R - L + 1);
			R++;

		}

		return maxSize;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
