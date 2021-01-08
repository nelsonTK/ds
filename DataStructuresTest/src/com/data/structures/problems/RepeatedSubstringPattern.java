package com.data.structures.problems;

public class RepeatedSubstringPattern {


    /**
     * @intuition
     * 		the intuition is to do half of KMP to solve the problem.
     * 		in the end of the array we will have the length of the repeating part
     * 		if we subtract s.len by the repeating part we get the non repeating part
     * 		after that we just have to repeat the non repeating part n times. and check if it is the same string.
     * 
     * 		we could avoid this calculation, if I understood better what the nonrepeating part was, because I did a big confusion
     * 		
     * 		I was thinking that if the prefix is repeated multiple times the count will be always increasing but its false, it will reset. e.g.
     * 
     * 			abdababab
     * 			000121212 (lps)
     * 	
     * 		and not
     * 
     * 			000123456 (lps)
     * 
     * 		so the final optimization would be the realization that the s.len must be multiple of non repeating part.
     * 		
     * @score
     * 		Runtime: 6 ms, faster than 86.94% of Java online submissions for Repeated Substring Pattern.
     * 		Memory Usage: 39.4 MB, less than 77.60% of Java online submissions for Repeated Substring Pattern.
     * 
     * @time
     * 		
     * @space
     * 		
     * 
    **/
    public boolean repeatedSubstringPattern(String s) {
        
        //create longest prefix array
        //if the last number is 0 //its  the end
        //else get length - last number to get arguably the non non repeating part
        //after repeat that part length/(length - last) if it is modulo == 0;
        
        int [] array = new int [s.length()];
        int len = 0, i = 1, n = s.length();
        
        while (i < s.length())
        {
            if (s.charAt(len) == s.charAt(i)){
                array[i++] = ++len;
            }
            else {
                if (len == 0)
                {
                    array[i++] = 0;
                }
                else
                {
                    len = array[len - 1];
                }
            }
        }
            
        if (array[n - 1] == 0)
            return false;
            
        int nonRepeating = n - array[n - 1]; //extract non repeating
            
        if (n % nonRepeating != 0)
        {
            return false;
        }
            
        int repeat = n / nonRepeating;
        
        String nonRepStr = s.substring(0, nonRepeating);
        
        StringBuilder sb = new StringBuilder("");
        
        while (repeat > 0){
            sb.append(nonRepStr);
            repeat--;
        }
        
        return sb.toString().equals(s);
        
    }
}

/**
 * the principles used are similar to mine but are are used more proficiently.
 * 
 * he doesn't even need to try the string
 * 
 * states that n must be divisible by the non repeating part (length % num == 0) if not than it cannot be true
 * 
 * observations, the end of the lps array will tell the length of the repeating prefix.
 * 
 * if we subtract that length with the total length we will have the non repeating. and total length should be a multiple of that.
 * 
 * because the array[i] value can never be greater than the longest common prefixsufix. e.g.
 * 
 * ababdababababab, the longest prefix sufix is 4, and the last element lenght is 2.
 * the lenght is 15, so 15 - 2 is 13. 15 is not a multiple of 13.
 * 
 * @author Nelson Costa
 *
 */
class UnofficialSolution {
    public boolean repeatedSubstringPattern(String s) {
        int[] next = longestPrefixSuffix((s+"#").toCharArray());//
        if(next[next.length-1]==0) return false;// 
        int num = s.length() - next[next.length-1];
        if(s.length()%num !=0) return false;// num
        
        return true;
    }
    
	public static int[] longestPrefixSuffix(char[] str) {
		if (str.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[str.length];
		next[0] = -1;
		next[1] = 0;

		for (int cn = 0, i = 2; i < next.length;) {
			if (str[i - 1] == str[cn]) {
				next[i] = cn + 1;
				i++;
				cn++;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}
}
