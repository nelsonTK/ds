package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz/
 * EASY
 * @author Nelson Costa
 *
 */
public class FizzBuzz {

	static FizzBuzz f = new FizzBuzz();

	public static void main(String[] args) {

		System.out.println(Arrays.toString(f.fizzBuzz(15).toArray()));

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * 
	 * @score	
	 * 		OLD
	 * 		Runtime: 10 ms, faster than 7.61% of Java online submissions for Fizz Buzz.
			Memory Usage: 45.9 MB, less than 5.09% of Java online submissions for Fizz Buzz.

			WITH OPTIMIZATION
			Runtime: 5 ms, faster than 27.21% of Java online submissions for Fizz Buzz.
			Memory Usage: 40.3 MB, less than 84.36% of Java online submissions for Fizz Buzz.

	   @optimizations
	   		I could use variables to avoid calculating the modulus everytime.

	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N) OR O(1)
	 * 
	 * @param n
	 * @return
	 */
	public List<String> fizzBuzz(int n) {

		List<String> ans = new ArrayList<>();
		for (int i = 1; i <= n; i++)
		{
			boolean m3 = i % 3 == 0;
			boolean m5 = i % 5 == 0;

			if(m3 && m5)
			{
				ans.add("FizzBuzz");
			}
			else if (m3)
			{
				ans.add("Fizz");
			}
			else  if (m5)
			{
				ans.add("Buzz");
			}
			else 
			{
				ans.add("" + i);
			}
		}

		return ans;
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * The gist of this solution is to add the possible words to a dictionary and than concatenate in a for look.
 * 
 * this is a good solution if we need to add support for another word, for instance for a new mod.
 * 
 * @author Nelson Costa
 *
 */
class FizzBuzzSolution3 {
	public List<String> fizzBuzz(int n) {

		// ans list
		List<String> ans = new ArrayList<String>();

		// Hash map to store all fizzbuzz mappings.
		HashMap<Integer, String> fizzBizzDict =
				new HashMap<Integer, String>() {
			{
				put(3, "Fizz");
				put(5, "Buzz");
			}
		};

		for (int num = 1; num <= n; num++) {

			String numAnsStr = "";

			for (Integer key : fizzBizzDict.keySet()) {

				// If the num is divisible by key,
				// then add the corresponding string mapping to current numAnsStr
				if (num % key == 0) {
					numAnsStr += fizzBizzDict.get(key);
				}
			}

			if (numAnsStr.equals("")) {
				// Not divisible by 3 or 5, add the number
				numAnsStr += Integer.toString(num);
			}

			// Append the current answer str to the ans list
			ans.add(numAnsStr);
		}

		return ans;
	}
}

/**
 * This solution concatenate the keys results
 * @author Nelson Costa
 *
 */
class FizzBuzzSolution2 {
	public List<String> fizzBuzz(int n) {
		// ans list
		List<String> ans = new ArrayList<String>();

		for (int num = 1; num <= n; num++) {

			boolean divisibleBy3 = (num % 3 == 0);
			boolean divisibleBy5 = (num % 5 == 0);

			String numAnsStr = "";

			if (divisibleBy3) {
				// Divides by 3, add Fizz
				numAnsStr += "Fizz";
			}

			if (divisibleBy5) {
				// Divides by 5, add Buzz
				numAnsStr += "Buzz";
			}

			if (numAnsStr.equals("")) {
				// Not divisible by 3 or 5, add the number
				numAnsStr += Integer.toString(num);
			}

			// Append the current answer str to the ans list
			ans.add(numAnsStr);
		}

		return ans;
	}
}	
