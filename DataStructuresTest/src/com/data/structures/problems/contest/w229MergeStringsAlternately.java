package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/merge-strings-alternately/
 * EASY
 * @author Nelson Costa
 *
 */
public class w229MergeStringsAlternately {

	/**
	 * Quite easy actually
	 * 
	 * But there were other approaches that could be done without string builder
	 * @param w1
	 * @param w2
	 * @return
	 */
    public String mergeAlternately(String w1, String w2) {
        
        int a = 0, b = 0, size = Math.min(w1.length(), w2.length());
        StringBuilder sb = new StringBuilder("");
        
        int i = 0;
        while (i < size)
        {
            sb.append(w1.charAt(i));
            sb.append(w2.charAt(i));
            
            i++;
        }
        
        if (w1.length() > w2.length())
        {
            sb.append(w1.substring(i, w1.length()));
        }
        else if (w1.length() < w2.length())
        {
            sb.append(w2.substring(i, w2.length()));            
        }
        
        return sb.toString();
    }
}
