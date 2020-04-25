package com.data.structures.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * N: 3
 * https://leetcode.com/problems/longest-substring-without-repeating-characters
 * Category: Strings
 * Medium
 * Though one need to repeat in the future
 * 20200316
 * 
 * 
 * @author Nelson Costa
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {


	public static void main(String [] args ) {
		String sampleData = "lengthOfLongestSubstring(String s)";
		System.out.println(lengthOfLongestSubstring(sampleData));
	}

	/**
	 * Runtime: 478 ms, faster than 5.02% of Java online submissions for Longest Substring Without Repeating Characters.
	 * Memory Usage: 42.2 MB, less than 5.20% of Java online submissions for Longest Substring Without Repeating Characters.
	 * @date 2020/03/16 18:30
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {

		if (s == null)
		{
			return 0;
		}

		if (s.length() == 1)
		{
			System.out.println(s);
			return 1;
		}

		String result = "";
		int areaEnd;
		String tmp;
		//create area for each word
		for (int i = 0; i < s.length() - 1; i ++)
		{
			areaEnd = findEnd(s, i, s.length() - 1);

			//get String
			tmp = s.substring(i, areaEnd + 1);

			if (tmp.length() > result.length())
			{
				result = tmp;
			}
		}

		System.out.println(result);
		return result.length();
	}


	/**
	 * Finds the longest index where characters are not repeated
	 * <br>
	 * 
	 * The Main logic is comparing the first char with all the following until you find a repeated element.
	 * when you find a repeated element, you shortned the endIndex
	 * and you will recursively check for all elements in that window, by advancing the startindex by 1.
	 * until your window is a single character, it is the base case
	 * from there the answer is the min between nested call and current call
	 * 
	 * <br>
	 * this is done until a single caracter is your window, it is the base case
	 * from there the answer is the min between nested call and current call
	 * @param s
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static int findEnd(String s, int startIndex, int endIndex) {

		if (startIndex >= endIndex)
			return Integer.MAX_VALUE;

		int currentEndIndex = startIndex;

		for (int i = startIndex; i < endIndex; i ++)
		{
			if (s.charAt(startIndex) != s.charAt(i + 1)) {
				currentEndIndex = i + 1;
			}
			else
			{
				break;
			}
		}	

		int previousEnd = findEnd(s, startIndex + 1 , currentEndIndex);
		return Math.min(currentEndIndex,previousEnd);//currentEnd <  previousEnd ? currentEnd : previousEnd;
	}


	/**
	 * Finds the longest index where characters are not repeated
	 * Trying to increase performance slightly with hashsset
	 * Poor Performance
	 * 
	 * @param s
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static int findEnd2(String s, int startIndex, int endIndex) {

		if (startIndex >= endIndex)
			return Integer.MAX_VALUE;

		int currentEndIndex = startIndex;
		HashSet<Character> set = new HashSet<Character>();
		char next;
		set.add(s.charAt(startIndex));
		for (int i = startIndex; i < endIndex; i ++)
		{
			next = s.charAt(i + 1);

			if (!set.contains(next)) {
				currentEndIndex = i + 1;
				set.add(s.charAt(i+1));
			}
			else
			{
				break;
			}
		}	

		int previousEnd = findEnd2(s, startIndex + 1 , currentEndIndex);
		return Math.min(currentEndIndex,previousEnd);//currentEnd <  previousEnd ? currentEnd : previousEnd;
	}
}

/**
 * cool solution, using a sliding window
 * 1) elements in the sliding window are all unique
 * 2) when a repeated element is found the start index in incremented
 * 
 *  A B C D A
 * |_|
 * |___|
 * |_____|
 * |_______|X
 *    |______|
 * 
 *  
 * Why I didn't get there?
 * Because I conceptualize a complety diferent solution, I wasn't able of envision this sliding window
 * According to leetcode sliding windows are very used in arrays and strings   
 *    
 *    
 * @author leetcode
 *
 */
class Solution {
	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))){
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			}
			else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}
}
