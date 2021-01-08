package com.data.structures.problems;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 * HARD
 * @author Nelson Costa
 *
 */
public class MinimumDifficultyofaJobSchedule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	class Solution1{
		/***
		 * 
		 * @intuition
		 * 		The intuition is to start with zero cuts, for an array of size 1, 2, 3, n
		 * 		then to calculate the next row with one cut, for all the jobs, and then calculate for two cuts for all jobs (that apply).
		 * 		
		 * 		The matrix gets like this:
		 * 
		 * 		___
		 * 		\ |
		 * 		 \|
		 * 
		 * 		One of the challenges I found was that I didn't found a way of finding the maximum value so I user a cache matrix to store the max value for each combination of i j.
		 * 
		 * 		But there is a way
		 * 		
		 * 		the cut is after the number.. this is fabulous.
		 * 
		 * 
		 * @score
		 * 		Runtime: 42 ms, faster than 11.21% of Java online submissions for Minimum Difficulty of a Job Schedule.
		 * 		Memory Usage: 39.6 MB, less than 5.62% of Java online submissions for Minimum Difficulty of a Job Schedule.
		 * 
		 * @time
		 * 		O(n^3*d)
		 * 
		 * @space
		 * 		O(n * d)
		 * 
		 ***/
		public int minDifficulty(int[] jobdiff, int d) {
			/*
        difficuly of a day is the maximum
        difficulty of a scheddule is the sum of all jobs
        minimum difficulty of a job sche


            time n * n * n * d
			 */
			//jdiff >= 1

			if (d > jobdiff.length)
				return -1;

			int tmp = Integer.MIN_VALUE;

			Integer [][] cache = new Integer[jobdiff.length][jobdiff.length];

			int [][] dp = new int[d][jobdiff.length];

			//get maximum 
			//fill first row with max value
			for (int i = 0; i < dp[0].length; i++)
			{
				tmp = Math.max(tmp, jobdiff[i]);
				dp[0][i] = tmp;
			}


			for (int cuts = 1; cuts < d; cuts++)
			{
				for (int job = cuts; job < dp[cuts].length; job++)
				{
					dp[cuts][job] = Integer.MAX_VALUE;

					for (int tryCut = 1; tryCut <= job - cuts  + 1; tryCut++)
					{
						dp[cuts][job] = 
								Math.min(
										dp[cuts][job], 
										dp[cuts - 1][job-tryCut] + MaxOf(job - tryCut + 1, job, cache, jobdiff));
					}
				}
			}

			return dp[d - 1][jobdiff.length - 1];
		}

		private int MaxOf(int start, int end, Integer [][] cache, int [] jobdiff)
		{
			int max = Integer.MIN_VALUE;

			if (cache[start][end] != null)
			{
				return cache[start][end];
			}

			for (int i = start; i <= end; i++)
			{
				max = Math.max(jobdiff[i], max);
			}

			cache[start][end] = max;

			return cache[start][end];
		}
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/***
	 * 
	 * @intuition
	 * 		The same solution than the above but with the improvement of the calculation of the max value that is made on the go
	 * 
	 * @score
	 * 		Runtime: 14 ms, faster than 23.91% of Java online submissions for Minimum Difficulty of a Job Schedule.
	 * 		Memory Usage: 42.1 MB, less than 5.50% of Java online submissions for Minimum Difficulty of a Job Schedule.
	 * 
	 * @time
	 * 		O(N^2*d)
	 * 
	 * @space
	 * 		O(N ^2 * D)
	 * 
	 * 
	 ***/
	public int minDifficulty(int[] jobdiff, int d) {

		if (d > jobdiff.length)
			return -1;

		int max, tmp = Integer.MIN_VALUE;

		int [][] dp = new int[d][jobdiff.length];

		//get maximum 
		//fill first row with max value
		for (int i = 0; i < dp[0].length; i++)
		{
			tmp = Math.max(tmp, jobdiff[i]);
			dp[0][i] = tmp;
		}

		for (int cuts = 1; cuts < d; cuts++)
		{
			for (int job = cuts; job < dp[cuts].length; job++)
			{
				dp[cuts][job] = Integer.MAX_VALUE;
				max = jobdiff[job];
				for (int tryCut = 1; tryCut <= job - cuts  + 1; tryCut++)
				{
					max = Math.max(max, jobdiff[job-tryCut+1]);
					dp[cuts][job] = Math.min(dp[cuts][job], dp[cuts - 1][job-tryCut] + max);
				}
			}
		}

		return dp[d - 1][jobdiff.length - 1];
	}
}