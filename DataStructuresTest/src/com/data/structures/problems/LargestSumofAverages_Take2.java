package com.data.structures.problems;

/**
 * https://leetcode.com/problems/largest-sum-of-averages/
 * MEDIUM
 * @author Nelson Costa
 */
public class LargestSumofAverages_Take2 {

	/**
	 * @intuition
	 * 		Memoization with the start position and the k length
	 * 
	 * 
	 * @score
	 * 		Runtime: 2 ms, faster than 100.00% of Java online submissions for Largest Sum of Averages.
	 * 		Memory Usage: 36.9 MB, less than 69.07% of Java online submissions for Largest Sum of Averages.
	 * 
	 * @time
	 * 		O(N*N)
	 * 
	 * @space
	 * 		O(N*k)
	 * 
	 **/
	double memo[][];
	public double largestSumOfAverages(int[] a, int k) {        

		memo = new double [a.length][k];

		return calculateMax(0, a, k - 1);
	}

	private double  calculateMax(int start, int [] arr, int k)
	{

		if (k < 0)
			return 0;

		if (memo[start][k] != 0)
			return memo[start][k];

		double result = 0;
		int sum = 0;
		int count = 1;
		for(int i = start; i < arr.length - k; i++)
		{

			sum += arr[i];

			if (k != 0)
				result = Math.max( (double) sum / count +  calculateMax (i + 1, arr, k - 1), result);
			else
				result = (double) sum / count;

			count++;
		}

		memo[start][k] = result;

		return result;
	}

}
