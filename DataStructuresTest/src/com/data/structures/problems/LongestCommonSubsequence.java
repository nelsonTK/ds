package com.data.structures.problems;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestCommonSubsequence {

	/**
	 * 
	 * @intuition
	 * 		Fundamentals
	 * 			not using one char
	 * 			not using the other char
	 *     		using none and add one
	 *     
	 *     DP on strings
	 *     
	 *     
	 * @score
	 * 		Runtime: 16 ms, faster than 25.73% of Java online submissions for Longest Common Subsequence.
	 * 		Memory Usage: 43 MB, less than 30.88% of Java online submissions for Longest Common Subsequence.    
	 *     
	 * @time
	 * 		O(N*M)
	 * 
	 * @space
	 * 		O(N*M)
	 *     
	 **/
	public int longestCommonSubsequence(String a, String b) {

		int [][] dp = new int [a.length() + 1][ b.length() + 1];

		//a string (as)
		for (int astr = 1; astr < dp.length; astr++)
		{
			for (int bstr = 1; bstr < dp[astr].length; bstr++)
			{
				if(a.charAt(astr-1) == b.charAt(bstr - 1)){
					//not using new b char count + 1
					dp[astr][bstr] =  dp[astr-1][bstr-1] + 1;
				}
				else
				{
					dp[astr][bstr] = Math.max(dp[astr][bstr - 1], dp[astr-1][bstr]);
				}
			}
		}
		return dp[a.length()][b.length()];        
	}
}
