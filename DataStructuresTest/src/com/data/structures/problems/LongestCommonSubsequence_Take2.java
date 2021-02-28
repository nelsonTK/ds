package com.data.structures.problems;


/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestCommonSubsequence_Take2 {

	


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition was to use recursion and memoization to try all the positions .
	 * 		This is a backtracking exercise with memoization.
	 * 		
	 * 		if the elements are equal we move both pointers.
	 * 		if not we try two things compating addind one more element to a, and then comparing adding one more element to b.
	 * 		
	 * 		the maximum of both will tell the answer for that combination of sizes for each string.
	 * 
	 * 		I can feel that because I've been spending so much time in DP exercises I forgot a bit of the gist of the recursion and backtracking
	 * 		the way of thinking, So I'm struggling a bit with this finding the right way of doing one and the other.
	 * 		but luckily is not the first time so I've got already a basic understanding of one and another.
	 * 
	 * 	
	 * @time
	 * 		O(N*M)
	 * 
	 * @space
	 * 		O(M*N)
	 * 
	 * 
	 */
    Integer [][] memo;
    public int longestCommonSubsequence(String a, String b) {
        
        memo = new Integer [a.length() + 1][b.length() + 1];
        return lcs(0, 0, a, b);
    }
    
    private int lcs(int ia, int ib, String a, String b)
    {
        if(memo[ia][ib] != null)
            return memo[ia][ib];
        
        int max = 0;
        if (ia >= a.length() || ib >= b.length())
            return  memo[ia][ib] = max;
        
        if (a.charAt(ia) == b.charAt(ib))
            max = 1 + lcs(ia + 1, ib + 1, a, b);
        else
            max = Math.max(lcs(ia, ib + 1,a, b ), lcs(ia + 1, ib,a, b ));
        
        return memo[ia][ib] = max;
    }
}
