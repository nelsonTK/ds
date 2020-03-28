package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation
 * EASY
 * 2020/03/26
 * @author Nelson Costa
 *
 */
public class LetterCasePermutation {
	ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		String s = "a1b245";
		LetterCasePermutation l = new LetterCasePermutation();
		l.letterCasePermutation(s);
		System.out.println(Arrays.toString(l.list.toArray()));
	}


	public List<String> letterCasePermutation(String s) {
		StringBuilder sb = new StringBuilder(s);    	
		permute(sb, 0);

		return list;
	}

	/**
	 * 
	 * 		Runtime: 6 ms, faster than 46.32% of Java online submissions for Letter Case Permutation.
			Memory Usage: 42.4 MB, less than 8.00% of Java online submissions for Letter Case Permutation.
	 * 
	 * 
	 * My Solution tree
	 * it ignores the no eligible characters
	 * 
	 * 
	  N
	  0				  		  a
	 				/					\
	  1			  a						  A
	  		  /  	  \				  /  	  \
	  2		b			B			b			B
		 /    \		 / 	  \		 /    \		 /    \
	  3	c		C	c		C	c		C	c		C


	  @time O(2^N) que number. level 0, 1 processing, level 2, 4 processing. 
	  		time complexity tells how the algorithm grows with scale
	  		N is the level in the tree

	 * @space O(N)
	 * @param sb
	 * @param index
	 */
	public void permute(StringBuilder sb, int index)
	{
		while (index < sb.length())
		{
			if (Character.isLetter(sb.charAt(index)))
			{
				break;
			}
			index++;
		}


		if (index >= sb.length())
		{
			list.add(sb.toString());
			return;
		}

		permute(getStringWithLowercaseChar (index, sb), index + 1);
		permute(getStringWithUppercaseChar (index, sb), index + 1);
	}


	private StringBuilder getStringWithLowercaseChar(int index, StringBuilder sb) {
		if (index < sb.length() && Character.isUpperCase(sb.charAt(index)))
		{
			sb.replace(index, index + 1, sb.substring(index,index + 1).toLowerCase());
		}

		return sb;
	}

	private StringBuilder getStringWithUppercaseChar(int index, StringBuilder sb) {
		if (index < sb.length() && Character.isLowerCase(sb.charAt(index)))
		{
			sb.replace(index, index + 1, sb.substring(index,index + 1).toUpperCase());  		
		}

		return sb;
	}
}
