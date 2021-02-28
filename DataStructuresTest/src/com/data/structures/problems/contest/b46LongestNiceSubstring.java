package com.data.structures.problems.contest;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-nice-substring/
 * EASY
 * @author Nelson Costa
 *
 */
public class b46LongestNiceSubstring {


	/**
	 * @intuition
	 * 		I should have tried brute force at first easy questions are not worthy my efforts
	 */
    HashMap<Character, Integer> hash = new HashMap<>();
    public String longestNiceSubstring(String s) {
        
        String ans = "";
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = i + 1; j <= s.length(); j++)
            {
                if (isNice(i, j, s) && j - i > ans.length())
                {
                    ans= s.substring(i, j);
                }
            }
        }
        
        return ans;
    }
    
    private boolean isNice(int l, int r, String s)
    {
        HashSet<Character> set = new HashSet<>();
        for (int i = l; i < r; i++)
        {
            set.add(s.charAt(i));
        }
        
        for (int i = l; i < r ; i++)
        {
            if (!set.contains(swap(s.charAt(i))))
            {
                return false;
            }
        }
        
        return true;
    }
    

    private char swap(char c)
    {
        return Character.isLowerCase(c)?  Character.toUpperCase(c) :  Character.toLowerCase(c);
    }
}
