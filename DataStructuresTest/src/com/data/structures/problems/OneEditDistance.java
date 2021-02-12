package com.data.structures.problems;

/**
 * https://leetcode.com/problems/one-edit-distance/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class OneEditDistance {

	
/**
 * @intuition
 * 		The intuition is that when one of the strings is bigger than the other by 2 or more than its impossible.
 * 		else if the s is smaller than t we can only insert one element
 * 		if s == size than t then we can only replace
 * 		and all develops from here
 * 
 * @score
 * 		Runtime: 1 ms, faster than 79.53% of Java online submissions for One Edit Distance.
 * 		Memory Usage: 37.8 MB, less than 45.25% of Java online submissions for One Edit Distance.
 * 
 * @fail
 * 		I failed because I was ancoring the wrong word, I did not think if the smallest would be easier to implement
 * 
 * @time 
 * 		O(N)
 * 
 * @space 
 * 		O(1)
 * 
 * @param s
 * @param t
 * @return
 */
    public boolean isOneEditDistance(String s, String t) {
        
        
        if (s.length() > t.length())
            return isOneEditDistance(t, s);
        
        if (Math.abs(s.length() - t.length()) > 1)
            return false;
        
        int sL = s.length(), tL = t.length(), sI = 0, tI = 0, distance = 0;
        
        while (sI < s.length() && tI < t.length())
        {
            char a = s.charAt(sI);
            char b = t.charAt(tI);
            
            if (a != b){
                
            if (sL == tL)
            {
                //can only replace on s
                distance++;
                sI++;
                tI++;
                
            }
            else if (sL < tL){
                //can only insert on s
                distance++;
                tI++;
            }
                
            }
            else
            {
                sI++;
                tI++;
            }
        }
        
        if (sI < s.length() || tI < t.length())
            distance ++;
        
        return distance == 1;
        
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * O(N) O(N) Solution with simple and cleaver implementation.
 * once you find an error compare the remaining string if it's not equal we return false else true.. very smart.
 * @author Nelson Costa
 *
 */
class Solution {
	  public boolean isOneEditDistance(String s, String t) {
	    int ns = s.length();
	    int nt = t.length();

	    // Ensure that s is shorter than t.
	    if (ns > nt)
	      return isOneEditDistance(t, s);

	    // The strings are NOT one edit away distance  
	    // if the length diff is more than 1.
	    if (nt - ns > 1)
	      return false;

	    for (int i = 0; i < ns; i++)
	      if (s.charAt(i) != t.charAt(i))
	        // if strings have the same length
	        if (ns == nt)
	          return s.substring(i + 1).equals(t.substring(i + 1));
	        // if strings have different lengths
	        else
	          return s.substring(i).equals(t.substring(i + 1));

	    // If there is no diffs on ns distance
	    // the strings are one edit away only if
	    // t has one more character. 
	    return (ns + 1 == nt);
	  }
	}
