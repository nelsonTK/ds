package com.data.structures.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ReverseWordsinaString {

	/** 
	 * @intuition
	 * 		3 pass solution using string api
	 * 
	 * @fail
	 * 		1) didn't thought in the case with the last element is a space
	 * 		2) didn't thought about string " "
	 * 
	 * @score
	 *       Runtime: 4 ms, faster than 71.56% of Java online submissions for Reverse Words in a String.
	 *       Memory Usage: 39.9 MB, less than 53.71% of Java online submissions for Reverse Words in a String.
	 *	
	 * @time
	 *		O(N)
	 *
	 * @space
	 *		O(N)
	 **/
	public String reverseWords(String s) {
		if (s == null)
			return s;

		s = s.trim();
		if (s.length() == 0 )
			return s;

		String [] words = s.split("\\s+");
		StringBuilder sb = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--)
		{
			if(!words[i].equals(""))
			{ 
				sb.append(words[i] + " ");
			}
		}

		sb.setLength(sb.length() - 1);

		return sb.toString();
	}
}

/**
 * Well done using the string API
 * @author Nelson Costa
 *
 */
class ReverseWordsinaStringSolution1 {
	  public String reverseWords(String s) {
	    // remove leading spaces
	    s = s.trim();
	    // split by multiple spaces
	    List<String> wordList = Arrays.asList(s.split("\\s+"));
	    Collections.reverse(wordList);
	    return String.join(" ", wordList);
	  }
	}