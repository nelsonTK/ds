package com.data.structures.problems;

import java.util.HashMap;

public class LongestSubstringwithAtMostKDistinctCharacters {


	/**
	 * @intuition
	 * 		Just a sliding window problem with two pointers
	 * 
     *  while R < len
     *      add char to hashmap
	 *
     *       while hashsize > k
     *           reduce charcount or remove it from hash
     *           increase left
 	 *
     *       update size
     *       increase R
	 *
	 * @score
	 * 		Runtime: 6 ms, faster than 78.46% of Java online submissions for Longest Substring with At Most K Distinct Characters.
	 * 		Memory Usage: 39.2 MB, less than 44.84% of Java online submissions for Longest Substring with At Most K Distinct Characters.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(K)
	 * 
	 **/
	public int lengthOfLongestSubstringKDistinct(String s, int k) {

		//edge cases
		//k == 0


		int L = 0, R = 0, maxsize = 0, n = s.length();
		HashMap<Character, Integer> map = new HashMap<>();
		char c;

		while (R < n)
		{
			c = s.charAt(R);

			//update count per char
			map.put(c, map.getOrDefault(c, 0)  + 1);

			//remove left while number of chars is greater than k
			while (map.size() > k)
			{
				c = s.charAt(L);

				//reduce window
				if(map.get(c) == 1)
				{
					map.remove(c);
				}
				else
				{
					map.put(c, map.get(c) - 1);
				}

				L++;
			}

			//update size
			maxsize = Math.max(maxsize, R - L + 1);
			R++;
		}

		return maxsize;
	}
}
