package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 * EASY
 * @author Nelson Costa
 *
 */
public class KidsWiththeGreatestNumberofCandies {

	public static void main(String[] args) {

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Just a straigth forward solution
	 * 
	 * @fail
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Kids With the Greatest Number of Candies.
	 *		Memory Usage: 39 MB, less than 99.31% of Java online submissions for Kids With the Greatest Number of Candies.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param candies
	 * @param extraCandies
	 * @return
	 */
	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

		List<Boolean> canDoIt = new ArrayList<>();

		//getMax
		int max = Integer.MIN_VALUE;
		for (int c : candies)
		{
			max = Math.max(c, max);
		}

		//fiil answer
		for(int c : candies)
		{
			canDoIt.add(c + extraCandies >= max ? true : false);
		}

		return canDoIt;
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Same concept but using Streams
 * @author Nelson Costa
 *
 */
class KidsWiththeGreatestNumberofCandiesUnofficialSolution1 {

	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		int max = Arrays.stream(candies).max().getAsInt();
		return Arrays.stream(candies).mapToObj(candy -> candy + extraCandies >= max).collect(Collectors.toList());
	}
}