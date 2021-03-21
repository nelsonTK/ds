package com.data.structures.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CheckIfaStringContainsAllBinaryCodesofSizeK {
    /*
    Runtime: 100 ms, faster than 56.76% of Java online submissions for Check If a String Contains All Binary Codes of Size K.
Memory Usage: 45.6 MB, less than 79.54% of Java online submissions for Check If a String Contains All Binary Codes of Size K.

optimizations include early stopage
    **/
	/**
	 * @intuition
	 * 		Brute force solution
	 * 		the goal is to create a sliding window and save all the windows inside and check if the number of elements equals the 2^k possibilities.
	 * 
	 * 
	 * @time
	 * 		O(NK)
	 * 
	 * @space
	 * 		O(NK)
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
    public boolean hasAllCodes(String s, int k) {
        Set<Integer> set = new HashSet<>();
        
        int curNumber = 0, winSize;
        for (int i = 0; i < s.length(); i++)
        {
            
            curNumber = s.charAt(i) - '0';
            winSize = 1;
            while (i + winSize < s.length() && winSize < k )
            {
                curNumber =  (curNumber << 1) +   s.charAt(i + winSize) - '0';                
                winSize++;
            }
            
            if (winSize == k)
            {
                set.add(curNumber);
            }
        }
        return set.size() == (1 << k);
    }
}
