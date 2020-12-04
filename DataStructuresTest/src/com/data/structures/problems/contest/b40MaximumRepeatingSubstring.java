package com.data.structures.problems.contest;

public class b40MaximumRepeatingSubstring {
	
	/**
	 * @intuition
	 * 		Simple algorithm so solve the problem	
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Repeating Substring.
	 * 		Memory Usage: 37.3 MB, less than 66.67% of Java online submissions for Maximum Repeating Substring.
	 * 
	 * @fail
	 * 		Out of bounds for the input word
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param s
	 * @param word
	 * @return
	 */
    public int maxRepeating(String s, String word) {
        
        int max = 0, cur = 0;
        boolean prev = false;
        int i = 0;
        
        while (i < s.length())
        {
            if (isEqual(i, s, word))
            {
                if (!prev)
                {
                    prev = true;
                    cur = 1;
                }
                else
                {
                    cur++;
                }
                max = Math.max(cur, max);
                i += word.length();
            }
            else
            {
                prev = false;
                cur = 0;
                i++;
            }
        }
        
        return max;
    }
    
    private boolean isEqual(int i, String s, String w)
    {
        int wordIndex = 0;
        while (i < s.length() && wordIndex < w.length())
        {
            if(s.charAt(i) != w.charAt(wordIndex))
                return false;
            
            i++;
            wordIndex++;
        }
        
        if(wordIndex < w.length())
            return false;
        
        return true;
    }
}
