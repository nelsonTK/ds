package com.data.structures.problems;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DeleteOperationforTwoStrings {

    /**
    
    Runtime: 10 ms, faster than 21.44% of Java online submissions for Delete Operation for Two Strings.
Memory Usage: 40.2 MB, less than 14.55% of Java online submissions for Delete Operation for Two Strings.
    
    
    **/
    public int minDistance(String a, String b) {
        
        int [][] dp = new int[a.length() + 1][b.length() + 1];
        
        for (int wa = 1; wa < dp.length; wa++)
        {
            for (int wb = 1; wb < dp[wa].length; wb++)
            {
                if (a.charAt(wa - 1) == b.charAt(wb - 1))
                {
                    dp[wa][wb] = dp[wa-1][wb-1] + 1;
                }
                else
                {
                    dp[wa][wb] = Math.max(dp[wa][wb-1], dp[wa-1][wb]);
                }
            }
        }
        int sizeA = a.length();
        int sizeB = b.length();
        return sizeA + sizeB - dp[sizeA][sizeB] * 2;
    }
}
