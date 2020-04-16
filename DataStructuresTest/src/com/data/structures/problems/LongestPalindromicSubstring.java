package com.data.structures.problems;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestPalindromicSubstring {



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LongestPalindromicSubstring l = new LongestPalindromicSubstring();
		String s = "bb";
		System.out.println(s);
		System.out.println(l.longestPalindrome(s));

	}

	int substringStart = 0;
	int substringEnd = 0;


	/**
	 * INTUITION
	 * 	Find largest palindfrome by searching the center of it
	 * 	I progressed from multiple stages of bruteforce (element by element, group by group) 
	 * 	and that I realized I could search only the middle and expand and voila
	 * 	the trick in this solution was the two char center.
	 * 
	 * 
	 * 		Runtime: 22 ms, faster than 90.16% of Java online submissions for Longest Palindromic Substring.
			Memory Usage: 37.5 MB, less than 61.69% of Java online submissions for Longest Palindromic Substring.
	 * 
	 * 
	 * @time O(N^2) worst case still is N^2 for the worst case. which is all elements equal. 
	 * @space O(1)
	 * 
	 * @failed at one case where length is 2 "bb", and the default value for substringEnd - substringStart is 1.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {

		if (s == null || s.length() == 0 || s.length() == 1 )
		{
			return s;
		}

		for (int i = 0; i < s.length(); i++) {
			expand(s, i);
		}

		return s.substring(substringStart, substringEnd + 1);
	}

	private void expand(String s, int pivot)
	{

		int r = pivot + 1;
		int l = pivot - 1;

		//expand pivot area to right if next elements are equal to pivot
		//I could still improve this using an array and the repeated elements ahead
		//like 
		while (r < s.length() && s.charAt(r) == s.charAt(pivot))
		{
			if (r - pivot > substringEnd - substringStart)
			{
				substringEnd = r;
				substringStart = pivot;
			}
			r++;
		}


		//expand pivot area unilaterally
		while (r < s.length() && l >= 0 && s.charAt(l) == s.charAt(r))
		{
			if (r - l > substringEnd - substringStart)
			{
				substringEnd = r;
				substringStart = l;
			}

			r++;
			l--;
		}

	}

}

class LongestPalindromicSubstringSolution{
	
	/**
	 * The concept is the same as my solutions
	 * but I intentionally decided I was not gonna call expand function 2 times 
	 * and I would resolve the centers with more than one character inside my function (i only stop when they are different)
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
}