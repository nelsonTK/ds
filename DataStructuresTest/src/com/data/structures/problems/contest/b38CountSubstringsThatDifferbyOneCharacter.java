package com.data.structures.problems.contest;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/count-substrings-that-differ-by-one-character/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b38CountSubstringsThatDifferbyOneCharacter {
    //N4
	/**
	 * @intuition
	 * 		The intuition is to test all possible substrings of s, cache the result of each one of them.
	 * 		It is a brute force solution and it runs at N^4 if I'm not mistaken.
	 * 
	 * 		So I test all the combinations of s, and then I test all the combinations of t.
	 * 
	 * 		there must be a better implementation
	 * 
	 * @time
	 * 		O(N^4)
	 * 
	 * @space
	 * 		O(N^4)
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
    public int countSubstrings(String s, String t) {
        
        String word = "";
        int ans = 0;
        HashMap<String, Integer> cache = new HashMap<>();
        for(int i = 0; i < s.length(); i++)
        {
            for(int size = 1; size + i <= s.length() ; size++)
            {
                word = s.substring(i, i+size);
                if(!cache.containsKey(word))
                {
                    //cache
                    int count = solve(word, t);
                    cache.put(word, count);
                    ans+=count;
                }
                else
                {
                    ans += cache.get(word);
                }
            }
        }
        
        return ans;
    }
    
    public int solve (String word, String t){
        
        int ans = 0;
        String test;
        for (int i = 0; i < t.length(); i++)
        {
            for (int size = 1;size + i <= t.length(); size++)
            {
                test = t.substring(i, i+size);
                if(word.length() == test.length() && valid(test, word))
                {
                    ans++;
                }
            }
        }
        
        return ans;
    }
    
    public boolean valid(String s, String t)
    {
        int diff = 0;
        
        for (int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != t.charAt(i))
            {
                diff++;
            }
        }
        
        return diff == 1 ? true : false;
    }
}
