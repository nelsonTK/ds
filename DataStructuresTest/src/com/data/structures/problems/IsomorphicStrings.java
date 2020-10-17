package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/isomorphic-strings
 * EASY
 * @author Nelson Costa
 *
 */
public class IsomorphicStrings {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 		trivial problem...
	 * 
	 * @score
	 *  	Runtime: 7 ms, faster than 61.54% of Java online submissions for Isomorphic Strings.
	 *		Memory Usage: 	 MB, less than 6.91% of Java online submissions for Isomorphic Strings.
	 *
	 *
	 * @comments
	 * 		this code could be much improved
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 *		O(N)
	 *
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isIsomorphic(String s, String t) {
		HashMap<Character, Character> charMap = new HashMap<>();
		HashMap<Character, Character> revCharMap = new HashMap<>();

		if (s == null && t != null || s != null && t == null)
			return false;

		for (int i = 0; i < s.length(); i++)
		{
			if (charMap.get(s.charAt(i)) == null)
			{
				if(revCharMap.get(t.charAt(i)) == null)
				{
					charMap.put(s.charAt(i), t.charAt(i));
					revCharMap.put(t.charAt(i), s.charAt(i));
				}
				else return false;

			}
			else if (charMap.get(s.charAt(i)) != t.charAt(i))
			{
				return false;
			}
		}
		return true;
	}
}

/**
 * Very good solution it uses the character size limit in order to use only one array
 * compares the side of the first string with the side of the second string.
 * they should always have consistent mapping.
 * you cannot have a letter mapped twice.
 * 
 * @author Nelson Costa
 *
 */
class IsomorphicStringsSolution {
    public boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }
}