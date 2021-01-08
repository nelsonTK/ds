package com.data.structures.problems;

/**
 * https://leetcode.com/problems/implement-strstr/
 * EASY
 * @author Nelson Costa
 *
 */
public class ImplementstrStr {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		This is a problem where we want to search a pattern in a string, I used KMP
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
    public int strStr(String haystack, String needle) {
        if (haystack.length()==0 && needle.length() == 0)
            return 0;
            
        if (haystack.length()!=0 && needle.length() == 0)
            return 0;
        
        if (haystack.length() < needle.length())
            return -1;
        
        int [] prefix = buildPrefixArray(needle);
        return searchKMP(prefix, haystack, needle);
    }
    
    
	private int [] buildPrefixArray(String str) {
		
		int len = 0;
		int i = 1;
		int [] arr = new int[str.length()];
		
		while(i < str.length()) {
			
			//while they are equal move both pointers
			while(i < str.length() && str.charAt(len) == str.charAt(i))
			{
				arr[i] = len + 1;
				len++;
				i++;
			}
			
			
			//while they are different update j
			while (i < str.length() && len != 0 && str.charAt(len) != str.charAt(i))
			{
				len = arr[len - 1];
				
			}
			
			//if they are equal move both forward and update the position i
			if (i < str.length() && str.charAt(len) == str.charAt(i))
			{
				arr[i] = len + 1;
				len ++;
				i ++;
			}
			//if they are not equal we just move
			else if (i < str.length() && str.charAt(len) != str.charAt(i))
			{
				arr[i] = arr[len];
				i++;
			}
		}
		
		return arr;
		
	}

	private int searchKMP(int [] arr, String str, String pattern) {
		
		int strPointer = 0;
		int arrPointer = 0;
		
		while (strPointer < str.length())
		{
			//move pointers forward if they are equal characters
			while (strPointer < str.length() && str.charAt(strPointer) ==  pattern.charAt(arrPointer))
			{
				arrPointer++;
				strPointer++;
				
				if (arrPointer == pattern.length())
					return strPointer - arrPointer;
			}
			
			//while not equal find the best new start
			while(strPointer < str.length() && arrPointer != 0 && str.charAt(strPointer) !=  pattern.charAt(arrPointer))
			{
				arrPointer = arr[arrPointer - 1];
			}
			
			//compare the best start with the current string position
			if (strPointer < str.length() && str.charAt(strPointer) !=  pattern.charAt(arrPointer)) 
			{
				strPointer++;
			}
			else if (strPointer < str.length() && str.charAt(strPointer) ==  pattern.charAt(arrPointer)) 
			{
				strPointer++;
				arrPointer++;
			}
		}
		
		return -1;
	}
}
