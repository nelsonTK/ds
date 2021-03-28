package com.data.structures.problems.contest;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class w234NumberofDifferentIntegersinaString {
    public int numDifferentIntegers(String s) {
        int i = 0; 
        Set<Integer> set = new HashSet<>();
        int curNum = 0;
        while (i < s.length())
        {
            if (i < s.length() && Character.isLetter(s.charAt(i)))
            {
                i++;
                continue;
            }
            
            curNum = 0;
            
            while (i < s.length() &&  Character.isDigit(s.charAt(i)))
            {
                curNum = curNum * 10 + s.charAt(i) - '0';
                i++;
            }
            set.add(curNum);
        }
        
        return set.size();
    }
}
