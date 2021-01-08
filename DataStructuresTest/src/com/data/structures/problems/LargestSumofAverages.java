package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

public class LargestSumofAverages extends LeetCodeExercise{

	static LargestSumofAverages l = new LargestSumofAverages();

	public static void main(String[] args) {
		int [] A = stringToArray("[9,1,2,3,9]");
		int K = 3;
		l.largestSumOfAverages(A, K);

	}


	/**
	 * @intuition
	 * 		calculate the best for 1 job, and 1 group, 2 jobs and 1 groups, 3 jobs and 1 group n jobs and 1 groups.
	 * 		then calculate for 2 jobs and 2 groups, than 3 jobs and 2 groups than n jobs and 2 groups
	 * 		and in the last position of the dp we will find our result.
	 * 
	 * 		Prefix sum used to avoid doing a lot of calculations
	 * 
	 *  	the dp[1][x] will have the averages for cutting in the first place, than second, than third.
	 *  
	 *  	this is a very tough dp problem
	 *  
	 *  @score
	 *  	Runtime: 9 ms, faster than 30.50% of Java online submissions for Largest Sum of Averages.
	 *  	Memory Usage: 37 MB, less than 68.89% of Java online submissions for Largest Sum of Averages.
	 *  
	 *  @time
	 *  
	 *  @space
	 *  
	 *  
	 *      
	 */
	public double largestSumOfAverages(int[] A, int K) {


		double [] prefix = new double[A.length + 1];
		//prefix[0] = A[0];

		//create prefix sum, which is used to calculate the average
		for (int i = 1; i < prefix.length; i++)
		{
			prefix[i] = prefix[i - 1] + A[i - 1];
		}


		if (K > A.length)
		{

			return prefix[A.length];
		}

		double [][] dp = new double[K + 1][A.length + 1];

		//calculate the average for one cut at every position possible
		for (int i = 1; i <= A.length; i++)
		{
			dp[1][i] = prefix[i]/(i);
		}

		//for k - 1 cuts
		for (int k = 2; k <= K; k ++)
		{
			//each job possible
			for (int jobs = k; jobs < dp[k].length; jobs++) //jobs starts at k, and means that if you have 2 jobs you can have 1 cut
			{
				//try the cuts possible
				for (int tryCut = 1; tryCut < jobs ; tryCut++)
				{
					//try out all possible cuts to see which one yields the maximum result                	  	
					dp[k][jobs] = Math.max(dp[k][jobs], 
							dp[k - 1][jobs-tryCut] + 
							(prefix[jobs] - prefix[jobs- tryCut])/tryCut);//(jobs - (jobs- tryCuts  + 1)));
				}
			}
		}

		return dp[K][A.length];
	}


}
