package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    /** for loop limit was wrong
    **/
    
	List<List<String>> ans;
    /**
     * @intuition
     * 		This is just a backtracking problem
     * 
     * @score
     * 		Runtime: 3 ms, faster than 59.07% of Java online submissions for Palindrome Partitioning.
	 *		Memory Usage: 40.2 MB, less than 10.52% of Java online submissions for Palindrome Partitioning.
     * 
     * @time
     * 		?
     * 		O(N*2^N)
     * 		N for generating substring and check if it is palindrome.
     * 		2^N for decision.
     * 		because the decision is to 
     * 
     * @space
     * 		?
     * 
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        
        ans = new  ArrayList<List<String>>();
        
        if (s == null || s.length() == 0)
            return ans;
        
        //start in the begining for each index
        //start position, end position end position will be increasing
        //for int i = start + 1
        //substring and check if it is palindrome, if it is add to list
        //next starting from the end
        //backtrack
        
        partition(0, s, new ArrayList<String>());
        
        return ans;
    }
    
    private void partition(int start, String s, List<String> cur)
    {
        if (start >= s.length())
        {
            ans.add(new ArrayList<String>(cur));
            return;
        }
        
        String sub;
        for (int i = start + 1; i <= s.length(); i++)
        {
            sub = s.substring(start, i);
            if(isPalindrome(sub))
            {
                cur.add(sub);
                partition(i, s, cur);
                cur.remove(cur.size()-1);
            }
        }
    }
    
    
    public boolean isPalindrome(String s){
        
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
}



class PalindromePartitioningSolution1 {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }
}

/**
 * Optimized version of backtracking with cache
 * @author Nelson Costa
 *
 */
class PalindromePartitioningSolution2 {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}