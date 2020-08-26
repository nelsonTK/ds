package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/find-the-closest-palindrome/
 * HARD
 * @author Nelson Costa
 *
 */
public class FindTheClosestPalindrome extends LeetCodeExercise{

	static FindTheClosestPalindromeUnofficialSolution1 f = new FindTheClosestPalindromeUnofficialSolution1();
	static FindTheClosestPalindrome f1 = new FindTheClosestPalindrome();
	public static void main(String[] args) {

		String n = "82162821312";
		n = "1283";
		f1.nearestPalindromic(n);
		f.nearestPalindromic(n);
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * [TIME OUT]
	 * 
	 * @comments
	 * 		My isPalindrome function is way too long. 
	 * 		it could be simplified a lot
	 * 
	 * @fail 1) I was accidentally using log 2 instead of log 10. 2) failed for case 1)
	 * 
	 * @time I thing the bigger the number more difficult it is to find a palindrom, but I can't prove right away. 
	 * 
	 **/
	public String nearestPalindromic0(String n) {
		//string to long
		//expand integer for left and right, for each iteration
		//if both palimdrome return smallest
		//else return the one which is not palindrome

		long num = Long.parseLong(n);
		long left = num - 1;
		long right = num + 1;
		boolean leftPalindrome = false;
		boolean rightPalindrome = false;

		while (!leftPalindrome && !rightPalindrome)
		{
			leftPalindrome = isPalindrome(left);
			rightPalindrome = isPalindrome(right);

			if (leftPalindrome && rightPalindrome)
			{
				return Math.min(left, right) + "";
			}
			else if (rightPalindrome)
			{
				return right + "";
			}
			else if (leftPalindrome)
			{
				return left + "";
			}

			left--;
			right++;
		}

		return "0";
	}


	private boolean isPalindrome(long i)
	{
		if (i == 0)
			return true;

		if (i < 0)
			i = (long) Math.abs(i);

		int intSize = (int)Math.floor(Math.log10(i)) + 1;
		//System.out.println(intSize);
		long [] array = new long[intSize];
		int index = intSize - 1; 
		while (i != 0)
		{
			array[index--] = i % 10;
			i/=10;
		}

		int left = 0;
		int right = intSize- 1;

		while (left < intSize && right >= 0 && left <= right)
		{

			if (array[left] != array[right])
				return false;

			left++;
			right--;
		}        
		return true;
	}



	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		This solution is heavily based in the following principles:
	 * 
	 * 		1) the closest palindrome, is equals to the original number with its left side mirrored
	 * 
	 * 		2) you can find the next or previous palindrome by increasing or decreasing the middle digit and mirroing again
	 * 
	 * 		This doesn't solves all the cases but is a good base.
	 * 
	 * 		What we do is to find a palindrome up, and a palindrom down the original number
	 * 		
	 * 		The one with smaller distance to the original number is the one that is the closest.
	 * 
	 * 		this has a lot of edge cases, and this solution deals with most of them elegantly.
	 * 
	 * 		My solution has many ideas borrowed
	 * 
	 * 		one edgecase is for original numbers like 10, 100, 1000, 1001 etc..
	 * 
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 59.18% of Java online submissions for Find the Closest Palindrome.
	 *		Memory Usage: 38 MB, less than 77.94% of Java online submissions for Find the Closest Palindrome.
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * @space
	 * 		O(N)
	 * 
	 * @param n
	 * @return
	 */
	public String nearestPalindromic(String n) {

		//operand is what I use to change the middle digit
		int operand = (int)Math.pow(10, n.length() / 2);
		long num = Long.parseLong(n);
		long mirror = mirror(num);
		long small = mirror(num/operand*operand-1);
		long large = mirror(num/operand*operand+operand);

		if (mirror < large && mirror > num)
		{
			large = mirror;
		}
		else if (mirror > small && mirror < num)
		{
			small = mirror;
		}

		return Math.abs(num - small) <= Math.abs(num-large)? small  + "": large + "";
	}

	private long mirror(long n)
	{
		char[] chars = (n + "").toCharArray();

		for (int i = 0; i < chars.length - 1 - i; i++)
		{
			chars[chars.length -1 - i] = chars[i];
		}

		return Long.parseLong(new String(chars));
	}

}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * This problem is hellish, to solve optimally
 * 
 * It requires you to have previous knowledge about palindromes, and how to find them.
 * 
 * the fundamental principles includes :
 * 
 * 1) to find a palindrome you can mirror the left part of a number and mirror in the right side.
 * 
 * 2) that will not give you the closest palindrome for sure, but you will have a good candidate for start.
 * 
 * 3) to find the closest and smallest palindrome you need to mirror the original num and then change the middle digit up or down.
 * 
 * 		1001001
 * 			up: 1002001
 * 			dw: 1000001
 * 
 * 		changing the middle element is an operation of adding or subtracting a power of 10 which fits the middle of the number.
 * 
 * 4) this still don't give you the best answer at all cases. you can have 10001
 * 		in this cases I you can subtract the middle element by a power of 10. and mirror the resulting number.
 * 
 * 5) edge cases "999"'s the palindrome is plus 2.
 * 	"10", "100", "1000" the decrease is differente
 * 
 * 
 * 
 * @author Nelson Costa
 *
 */
class FindTheClosestPalindromeUnofficialSolution1 {
	public String nearestPalindromic(String n) {
		int order = (int) Math.pow(10, n.length()/2); // get middle number to increment
		Long ans = Long.valueOf(new String(n));
		Long noChange = mirror(ans);	//mirror string
		Long larger   = mirror((ans/order)*order + order+1); //get larger from original, this math is to zero the right half of the input
		Long smaller  = mirror((ans/order)*order - 1 ); 	 //get smaller from mirror, this way it solves the 9's problem
		if ( noChange > ans) {
			larger = (long) Math.min(noChange, larger);
		} else if ( noChange < ans) {
			smaller = (long) Math.max(noChange, smaller); 
		}       
		return String.valueOf( ans - smaller <= larger - ans ? smaller :larger) ;
	}
	public Long mirror(Long ans) {
		char[] a = String.valueOf(ans).toCharArray();
		int i = 0;
		int j = a.length-1;
		while (i < j) {
			a[j--] = a[i++];
		}
		return Long.valueOf(new String(a));
	} 
}