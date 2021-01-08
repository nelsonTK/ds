package com.data.structures.algos;

import java.util.Arrays;

public class KMP {

	static KMP kmp = new KMP();
	public static void main(String[] args) {
		String searchStr = "dhalkaj88dhalk8ajd halkajdha";
		String pattern = "lkajdha";
		int [] prefix = kmp.buildPrefixArray(pattern);
		kmp.searchKMP(prefix, searchStr, pattern);
		System.out.println(Arrays.toString(prefix));
		
		System.out.println(kmp.searchKMP(prefix, searchStr, pattern));
	}
	
	/**
	 * I can simplify this implementation by removing inner while loops
	 * @param pattern
	 * @param searchStr
	 * @return
	 */
	public int searchKMP(String pattern, String searchStr) {

        if (searchStr.length()==0 && pattern.length() == 0)
            return 0;
            
        if (searchStr.length()!=0 && pattern.length() == 0)
            return 0;
        
        if (searchStr.length() < pattern.length())
            return -1;
		
		
		int [] prefix = kmp.buildPrefixArray(pattern);
		return kmp.searchKMP(prefix, searchStr, pattern);
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
