package com.data.structures.problems.contest;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/second-largest-digit-in-a-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class b48SecondLargestDigitinaString {

	/**
	 * not sure if this was the best possible, I could not thing in a better solution
	 * 
	 * @time
	 * 		nlogn
	 * 
	 * @space 
	 * 		n
	 * @param s
	 * @return
	 */
    public int secondHighest(String s) {
        TreeSet<Integer> p = new TreeSet<Integer>((a,b)-> Integer.compare(b,a));

        for (char c : s.toCharArray())
        {
            if (Character.isDigit(c) )
            {
                p.add(c - '0');
            }
        }
        
        if (p.size() >= 2)
        {
            p.pollFirst();
            return p.first();
        }
        return -1;
    }
}
