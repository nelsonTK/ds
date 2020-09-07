package com.data.structures.problems;

/**
 * https://leetcode.com/problems/palindrome-number
 * EASY
 * @author Nelson Costa
 *
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		My solution is about checking each elements of the number individually
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 62.44% of Java online submissions for Palindrome Number.
	 *		Memory Usage: 39 MB, less than 61.34% of Java online submissions for Palindrome Number.
	 * 
	 * @time
	 * 		O(log10(x))
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {

		if (x < 0)
			return false;

		int size = (int) Math.ceil(Math.log10(x));
		int left = 0;
		int right = 0;

		while (x != 0)
		{
			size = size - 1 < 0 ? 0 : size - 1;
			int pow = (int)Math.pow(10,(size));

			left = (int) (x/pow);
			right = x % 10;

			if (left != right)
				return false;

			x = (int)(x - (x /pow)*pow);
			x /=10;
			size--;
		}
		return true;
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * Solution is about reversing the number and then see if they are equal
 * @author Nelson Costa
 *
 */
class PalindromeNumberUnofficialSolution{
	public boolean isPalindrome(int num){
		if(num < 0) return  false; 
		int reversed = 0, remainder, original = num;
		while(num != 0) {
			remainder = num % 10; // reversed integer is stored in variable
			reversed = reversed * 10 + remainder; //multiply reversed by 10 then add the remainder so it gets stored at next decimal place.
			num  /= 10;  //the last digit is removed from num after division by 10.
		}
		// palindrome if original and reversed are equal
		return original == reversed;
	}
}