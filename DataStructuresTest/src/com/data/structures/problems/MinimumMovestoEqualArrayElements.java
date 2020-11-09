package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 * EASY
 * @author Nelson Costa
 *
 */
public class MinimumMovestoEqualArrayElements {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is instead of changing all elements, change only the greatest element
	 * 
	 * @score
	 * 		Runtime: 10 ms, faster than 27.42% of Java online submissions for Minimum Moves to Equal Array Elements.
	 * 		Memory Usage: 39.6 MB, less than 8.83% of Java online submissions for Minimum Moves to Equal Array Elements.
	 * 
	 * @fail
	 * 		I failed many times becaus I misunderstood the problem.
	 * 		I understood that we could only change 2 elements at a time.
	 * 
	 * @time
	 * 		O(nlog n)
	 * 
	 * @space
	 * 		O(1)
	 **/
	public int minMoves(int[] nums)
	{
		Arrays.sort(nums);
		int moves = 0;

		for (int i = nums.length-1; i >= 0; i--)
		{
			moves += nums[i] - nums[0];
		}

		return moves;
	}
}


/*********************
* OTHERS SOLUTIONS
/**
 * Same line of though than my solution
 */
class MinimumMovestoEqualArrayElementsSolution3 {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }
}

/**
 * Mathematical approach
 * @author Nelson Costa
 *
 */
class MinimumMovestoEqualArrayElementsSolution5 {
    public int minMoves(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i];
            min = Math.min(min, nums[i]);
        }
        return moves - min * nums.length;
    }
}

