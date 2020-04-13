package com.data.structures.problems;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * 125
 * EASY
 * @author Nelson Costa
 *
 */
public class ValidPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidPalindrome v = new ValidPalindrome();
		String s = "A man, a plan, a canal: Panama";
		s = "race a car";
		s = "0P";
		s = "ab2a";
		System.out.println(s);
		System.out.println(v.isPalindrome(s));
	}

	/**
	 * OPTIMIZATION:
	 * 
	 * 	Runtime: 2 ms, faster than 97.60% of Java online submissions for Valid Palindrome.
		Memory Usage: 39.7 MB, less than 22.32% of Java online submissions for Valid Palindrome.
	 * 
	 * 		DESCRIPTION:
	 * 			Removed isLower and UpperCase and replaced explicit char validation with .isDigit
	 * 
	 * 
	 * 
	 * 		Runtime: 4 ms, faster than 42.19% of Java online submissions for Valid Palindrome.
			Memory Usage: 40 MB, less than 19.64% of Java online submissions for Valid Palindrome.
	 * 
	 * 
	 * @failed forgot to lowercase, forgot to add minus to the j counter, mistake on equals sign == instead of !=
	 * failed at leetcode because I didn't read that it was alphanumeric character
	 * mistake on the signs greater of less than
	 * mistake on copy pasting and not exchangin i per j
	 * @time O(N)
	 * @space O(1)
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s) {

		if(s == null)
		{
			return true;
		}

		int i = 0;
		int j = s.length() - 1;

		while (i <= j)
		{

			while (i <= j && !Character.isLetterOrDigit(s.charAt(i)))
			{
				i++;
			}

			while (i <= j && !Character.isLetterOrDigit(s.charAt(j)))
			{
				j--;
			}

			if(i <= j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
			{
				return false;
			}

			i++;
			j--;
		}
		return true;
	}
}
