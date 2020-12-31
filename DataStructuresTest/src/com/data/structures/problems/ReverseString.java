package com.data.structures.problems;

/**
 * https://leetcode.com/problems/reverse-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class ReverseString {

	class Solution {
	    public void reverseString(char[] s) {
	        
	        
	        dfs(s, 0);
	    }
	    
	    private void dfs(char [] s, int index)
	    {
	        
	        if (index >= s.length / 2)
	        {
	            return;
	        }
	        
	        dfs(s, index + 1);
	        char tmp = ' ';
	        tmp = s[index];
	        s[index] = s[s.length - 1 - index];
	        s[s.length - 1 - index] = tmp;
	    }
	}
	
}
