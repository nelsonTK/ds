package com.data.structures.problems;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PalindromicSubstrings {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		This is a bad solution for palindromic substrings
	 * 		I was confused by the output provided, I thought that given the output it was not possible to achieve a solution by expanding around the center.
	 * 		But probably what I needed was to hand those edge cases.
	 * 
	 * 	@time
	 * 		N^2
	 * 
	 * @space
	 * 		N
	 * 
	 * @param s
	 * @return
	 */
    public int countSubstrings(String s) {
        
        if (s == null)
            return 0;
        
        int count = 0;
        for (int start = 0; start < s.length(); start++)
        {
            for (int size = 1; size + start <= s.length(); size++)
            {
                String cur = s.substring(start, size + start);
                
                if (isPalindromic(cur))
                {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    
    public boolean isPalindromic(String s)
    {
        //0, 1, 2, 3, 4 | size 5
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
            {
                return false;
            }
        }
        
        return true;
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Expanding around the center
 * @author Nelson Costa
 *
 */
class PalindromicSubstringsSolution1 {
    public int countSubstrings(String S) {
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2*N-1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}
