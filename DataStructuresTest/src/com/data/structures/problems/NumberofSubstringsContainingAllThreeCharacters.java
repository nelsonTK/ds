package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class NumberofSubstringsContainingAllThreeCharacters {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is to find the first 3 elements, and thats one combination.
	 * 		Then the goal is to find calculate the remaining elements that we need to increase the array.
	 * 		Then we reduce the window and see if there is still 3 characters in the window.
	 * 		If they are not then we forward the window. both start and end.
	 * 
	 * @score
	 * 		Runtime: 29 ms, faster than 13.14% of Java online submissions for Number of Substrings Containing All Three Characters
	 * 		Memory Usage: 39.4 MB, less than 34.46% of Java online submissions for Number of Substrings Containing All Three Characters.
	 * 
	 * @fail
	 * 		At the first implementation I missed at not forwarding the left pointer in the while loop.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 **/
	public int numberOfSubstrings(String s) {

		/*
	    hash = size 3
	       a = 1
	       b = 1
	       c = 1;
		 */
		
		HashMap<Character, Integer> map = new HashMap<>();
		int ans = 0;
		int L = 0, n = s.length();
		char c;
		for (int R = 0; R < n; R++)
		{
			c = s.charAt(R);

			map.put(c, map.getOrDefault(c,0) + 1);

			while (map.size() == 3)
			{
				c =s.charAt(L);
				//math so sum one and from eating 3
				//and adding all the values that comes next
				//ans += R - n - 1 + 1;
				ans += n - R;

				if (map.get(c)== 1)
					map.remove(c);
				else
					map.put(c, map.get(c) - 1);

				L++;
			}
		}
		return ans;
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Instead of using an hashmap the user uses arrays and a variable to count.
 * It is smart, and a charming solution
 * @author Nelson Costa
 *
 */
class NumberofSubstringsContainingAllThreeCharactersSolution {
	public int numberOfSubstrings(String s) {
		int len = s.length();
		int[] letter = new int[3];
		int count = 0;
		int res = 0;
		int start = 0;
		int end = 0;
		while (end < s.length()) {
			char c1 = s.charAt(end);
			if (letter[c1 - 'a'] == 0) {
				letter[c1 - 'a']++;
				count++;
			}
			end++;
			while (count == 3) {
				res += len - end - 1;
				char c2 = s.charAt(start);
				if (letter[c2 - 'a'] == 1) {
					letter[c2 - 'a']--;
					count--;
				}
				start++;
			}
		}
		return res;
	}
}