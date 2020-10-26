package com.data.structures.problems;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class FirstUniqueCharacterinaString {

	/**
	 * @intuition
	 * 		Nothing special, just a counter for the leathers.
	 * 		perhaps there is a way of do it in one pass
	 *  
	 *  @score
	 *  	Runtime: 5 ms, faster than 95.91% of Java online submissions for First Unique Character in a String.
	 *  	Memory Usage: 40 MB, less than 7.31% of Java online submissions for First Unique Character in a String.
	 * 
	 *  @time
	 *  	O(N)
	 *  @space
	 *  	O(1)
	 **/
	public int firstUniqChar(String s) {
		if (s == null || s.length() == 0)
			return -1;

		int [] counter = new int[26];

		for (char c : s.toCharArray())
		{
			counter[c - 'a']++;                
		}

		int i =0;
		for (char c : s.toCharArray())
		{
			if (counter[c - 'a'] == 1)
				return i;

			i++;
		}

		return -1;
	}
}
