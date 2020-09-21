package com.data.structures.problems.contest;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w207SplitaStringIntotheMaxNumberofUniqueSubstrings {

	/**
	 * @intuition
	 * 		backtracking solution
	 * 		
	 * 
	 * @score
	 * 		Runtime: 1322 ms, faster than 33.33% of Java online submissions for Split a String Into the Max Number of Unique Substrings.
	 *		Memory Usage: 39.7 MB, less than 66.67% of Java online submissions for Split a String Into the Max Number of Unique Substrings.
	 *
	 * @time
	 * 		O(2^N)
	 * 
	 * @space
	 * 		O(N) 
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    
	    Set<String> set;
	    int max = 0;
	    public int maxUniqueSplit(String s) {
	        set = new HashSet<>();
	        
	        solve(0, 1, s, true);
	        return max;
	    }
	    
	    void solve (int start, int end, String s, boolean prevValid)
	    {
	        //Base Case, when end of string go back
	        if (start >= s.length())
	        {
	            if (prevValid)
	            {
	                max = Math.max(max, set.size());
	            }
	            return;
	        }
	        
	        String cur = s.substring(start, end);
	        
	        //if string not in the set of words we add it
	        if(!set.contains(cur))
	        {
	            //switch for the next character
	            set.add(cur);
	            solve(end, end+1, s, true);
	            set.remove(cur);

	            //increase current character
	            for (int i = end + 1; i <= s.length(); i++)
	            {
	                solve(start,i, s, false);
	            }
	        }
	        else
	        //if string already in the set we increase its leanth
	        {
	            for (int i = end + 1; i <= s.length(); i++)
	            {
	                solve(start,i, s, false);
	            }
	        }
	    }
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * 2^N solution
 * @author Nelson Costa
 *
 */
class Solution {
    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        return dfs(set, 0, s);
    }
    
    private int dfs(Set<String> set, int idx, String s) {
        if (idx >= s.length()) return 0;
        int res = -1;  // did not find method to split;
        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (set.contains(sub)) continue;
            set.add(sub);
            int next = dfs(set, i, s);
            if (next >= 0) res = Math.max(res, next + 1);
            set.remove(sub);
        }
        return res;
    }
}