package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/jump-game/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class JumpGame_Take2 {


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The gist of this solution is to have track of the maximum variable
	 * 		that tracks what is the maximum reach for each number
	 * 		if somewhere along the way one index cannot be reached than it is not possible.
	 * @score
	 *     Runtime: 2 ms, faster than 42.80% of Java online submissions for Jump Game.
	 *     Memory Usage: 41.1 MB, less than 42.44% of Java online submissions for Jump Game.
	 *     
	 * @time
	 * 		
	 * 
	 * @space
	 * 		
	 **/
	public boolean canJump0(int [] nums)
	{
		if (nums == null)
			return false;

		if (nums.length == 1)
			return true;

		int max = nums[0];

		for (int i = 1; i < nums.length; i++)
		{
			if (max >= i)
			{
				if (i + nums[i] >= nums.length - 1)
					return true;

				max = Math.max(i + nums[i], max);
			}
			else
				return false;
		}

		return false;
	}




	/*********************************
	 * SOLUTION 2
	 ********************************/


	/**
	 * @intuition
	 * 		Memoization Solution; 
	 * 		this was more for practice of memoization, I knew it was going to be slower.
	 * 
	 * 		many things could have been improved in the code
	 * 		The problem I see with my solution is that I start from the end and therefore if we have a begining that is impossible I'll calculate all elements before first
	 * 		
	 * 		Also What I did and was not asked was to find the minimum number of jumps...
	 * @score
	 * 		Runtime: 1468 ms, faster than 5.00% of Java online submissions for Jump Game.
	 *		Memory Usage: 43.5 MB, less than 13.77% of Java online submissions for Jump Game.
	 * 
	 * @time
	 * 		O(N2)
	 * 		
	 * @space
	 * 		O(N)
	 * 		
	 * @fail
	 * 		1) plus one errors in for loop
	 * 		2) 
	 * 
	 * @param memo
	 * @param index
	 * @param nums
	 * @return
	 */
	public boolean canJump1(int[] nums) {
		int [] memo = new int [nums.length];
		Arrays.fill(memo, -1);

		//start from the end index

		for (int i = nums.length - 1; i >= 0; i--)
		{
			calcJumps(memo, i, nums);

		}

		return memo[0] != Integer.MAX_VALUE;
	}

	private int calcJumps(int [] memo, int index, int[] nums){

		if (index == nums.length - 1)
		{
			memo[index] = 0;
			return 0;
		}

		if (index < 0 || nums[index] == 0)
		{
			memo[index] = Integer.MAX_VALUE;
			return Integer.MAX_VALUE;
		}

		if (memo[index] != -1)
		{
			return memo[index];
		}

		int jumpLen = nums[index];
		int minJump = Integer.MAX_VALUE;
		int curCalc;
		for (int i = index+1; i < nums.length && i <= index + jumpLen; i++)
		{
			curCalc = calcJumps(memo, i, nums);
			if (curCalc != Integer.MAX_VALUE)
			{
				minJump = Math.min(1 + curCalc, minJump);
			}

		}

		memo[index] = minJump;

		return memo[index];
	}


	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 		This is a linear DP problem N^2 not charming at all
	 * 
	 * @score
	 * 		Runtime: 234 ms, faster than 31.89% of Java online submissions for Jump Game.
	 * 		Memory Usage: 41.4 MB, less than 29.43% of Java online submissions for Jump Game.
	 * 
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 * @param nums
	 * @return
	 */
	public boolean canJump(int[] nums) {

		int n = nums.length;
		boolean [] dp = new boolean[nums.length];
		dp[n - 1] = true;

		int jumpLen;
		for (int i = n - 2; i >= 0; i--)
		{
			jumpLen = Math.min(i + nums[i], n - 1);
			for (int j = i + 1; j <=  jumpLen; j++)
			{
				if(dp[j])
				{
					dp[i] = true;
					break;
				}
			}
		}

		return dp[0];
	}
}


/**
 * Cool solution
 * I like how they solve the furthestJump issue
 * Memoization
 * 
 * @score
 * 		Runtime: 871 ms, faster than 10.48% of Java online submissions for Jump Game.
 *		Memory Usage: 43.5 MB, less than 13.84% of Java online submissions for Jump Game.
 * 
 * 
 * @author Nelson Costa
 *
 */
class JumpGame_Take2Solution {
	Index[] memo;

	public boolean canJumpFromPosition(int position, int[] nums) {
		if (memo[position] != Index.UNKNOWN) {
			return memo[position] == Index.GOOD ? true : false;
		}

		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
			if (canJumpFromPosition(nextPosition, nums)) {
				memo[position] = Index.GOOD;
				return true;
			}
		}

		memo[position] = Index.BAD;
		return false;
	}

	public boolean canJump(int[] nums) {
		memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;
		return canJumpFromPosition(0, nums);
	}

	enum Index {
		GOOD, BAD, UNKNOWN
	}
}