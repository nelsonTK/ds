package com.data.structures.problems;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * EASY
 * 
 * @author Nelson Costa
 *
 */
public class ValidPalindromeII {

	public static void main(String[] args) {

		ValidPalindromeII v = new ValidPalindromeII();
		String s = "ab ba";
		System.out.println(s);
		System.out.println("is a valid palindrome? " + v.validPalindrome(s));
	}

	public boolean validPalindrome(String s) {

		return validate(0, s.length() - 1, s, 2);
	}

	/**
	 * 		Runtime: 5 ms, faster than 92.60% of Java online submissions for Valid Palindrome II.
			Memory Usage: 40.7 MB, less than 5.55% of Java online submissions for Valid Palindrome II.
	 * 
	 * 
	 * [NOT BAD]
	 * 
	 * Recursivity and Iterativity mixed up together, really enjoyed this exercise
	 * I didn't even removed the character because it was not necessary
	 * 
	 * @time I believe this is O(N/2 + 1) => O(N)
	 * @space I believe this is O(1), given my estimations it takes 3 calls to solve any string problem
	 * 
	 * @param i start index
	 * @param j end index
	 * @param s string
	 * @param skipLetters max number of skipchars before words it considered invalid
	 * @return
	 */
	private boolean validate(int i, int j, String s, int skipLetters) {

		//skipLetters plafond run out
		if (skipLetters < 0)
		{
			return false;
		}	
		
		//go to next pair until you find something different
		while (i < s.length() && j >= 0 && s.charAt(i) == s.charAt(j) && i <= j)
		{
			i++;
			j--;
		}
		
		//we reached the end of our string with no errors.
		if (i > j)
		{
			return true;
		}

		//If we get here is because we are not in the end of the list
		return validate(i + 1, j, s, skipLetters - 1) | 
			   validate(i, j - 1, s, skipLetters - 1);
	}
}

/**
 * this guy's solution says that after you jump a faulty pair the following characters must be palindrome.
 * Is an efficient solution but is not flexible in the sense that it is tailor made for 1 error, my solution allows configurable skipchar Value.
 * Both this and my solution behave in tO(N) sO(1)
 * further more he uses this complex aritmetic to fo the for loop, is disturbing, but well it's a different way of thinking and we got to respect that.
 * 
 * @author leetcode
 *
 */
class ValidPalindromeIISolution {
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
}
