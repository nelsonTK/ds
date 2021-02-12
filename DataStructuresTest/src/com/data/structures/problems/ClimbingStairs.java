package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * EASY
 * @author Nelson Costa
 *
 */
public class ClimbingStairs {

	private int n;
	private HashMap<Integer,Integer> memo = new HashMap<>();

	public static void main(String[] args) {
		ClimbingStairs c = new ClimbingStairs();
		int n = 5;
		System.out.println(c.climbStairs(n));
	}

	public int climbStairs(int n) {
		this.n = n;
		return calculateCombinations(0);
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
		Memory Usage: 36.2 MB, less than 5.26% of Java online submissions for Climbing Stairs.
		
		Backtracking Pattern identified quite easy and Dynamic Programming (What a fancy Name....)
		Applied Memoization (Top down) to the recursive solution.
		A better approach would be a Bottom Up Approach.
		
		Ou melhor ainda usar a abordagem de calculo de fibonacci numbers.
		
		we can also do it with log n time complexity by multiplying matrix.

		@time  O(N) each element is calculated once
		@space O(N)
	 * @param stair
	 * @return
	 */
	private int calculateCombinations(int stair) {

		if (n == 0)
			return n;

		if (stair > n)
			return 0;

		if (stair == n)
			return 1;

		int result =  0;

		Integer resultCached = memo.get(stair);
		
		if (resultCached == null)
		{
			result = calculateCombinations(stair + 1);

			result += calculateCombinations(stair + 2);

			memo.put(stair, result);
		} 
		else
		{
			result = resultCached;
		}
		
		return result;
	}
}
