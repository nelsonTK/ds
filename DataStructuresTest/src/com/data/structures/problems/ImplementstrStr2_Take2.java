package com.data.structures.problems;

/**
 * https://leetcode.com/problems/implement-strstr/
 * EASY
 * @author Nelson Costa
 *
 */
public class ImplementstrStr2_Take2 {

	/**
	 * @intuition
	 * 		Better/cleaner implementation
	 * 	
	 * @param s
	 * @param pattern
	 * @return
	 */
	public int strStr(String s, String pattern) {

		if (s.length() < pattern.length())
			return -1;

		if (pattern.length() == 0)
			return 0;

		return KMPSearch(s, pattern);
	}


	public int[] lpsShort(String s) {
		int [] array = new int [s.length()];
		int len = 0, i = 1, n = s.length();

		while (i < s.length())
		{
			if (s.charAt(len) == s.charAt(i)){
				array[i++] = ++len;
			}
			else if (len == 0)
			{
				array[i++] = 0;
			}
			else
			{
				len = array[len - 1];
			}
		}

		return array;
	}


	public int KMPSearch(String s, String pattern)
	{
		int [] lps = lpsShort(pattern);

		//System.out.println(Arrays.toString(lps));

		int i = 0, len = 0;

		while (i < s.length())
		{
			if (pattern.charAt(len) == s.charAt(i))
			{
				i++;
				len++;
			}
			else if (len == 0) {
				i++;
			}
			else {
				len = lps[len - 1];
			}

			if (len == pattern.length())
				return i - len ;
		}

		return -1;
	}
}
