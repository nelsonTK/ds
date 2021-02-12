package com.data.structures.problems;

public class ClimbingStairs_Take2 {

	/*********************************
	 * SOLUTION 1
	 ********************************
	/**
	 * @Intuition
	 * 		Memoization Solution to Solve the Staircase problem
	 * 		The time complexity is O(N) because we calculate each number only once	
	 * 		The subproblem is N = calc(N-1, memo) + calc(N-2, memo)
	 * 
	 * @score
	 * 		100%
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs0(int n) {

		Integer [] memo = new Integer[n + 1];
		memo[1] = 1;

		return calc (n, memo);        
	}

	//the first implementation
	@SuppressWarnings("unused")
	private int calc0(int n, Integer[] memo)
	{
		if(memo[n] != null)
		{
			return memo[n];
		}

		if (n < 0)
			return 0;

		if (n == 0)
			return 1;

		int ways = 0;
		ways = calc0(n - 2, memo);
		ways += calc0(n - 1, memo);


		memo[n] = ways;

		return memo[n];
	}

	//The Second implementation with improved readability
	private int calc(int n, Integer[] memo)
	{
		if (n < 0)
			return 0;

		if (n == 0)
			return 1;

		if(memo[n] != null)
		{
			return memo[n];
		}

		memo[n] = calc(n - 2, memo) + calc(n - 1, memo);

		return memo[n];
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		Intuition is to solve with dynamic programing
	 * 		the subproblem is dp[i] = dp[i - 1]dp[i - 2];
	 * 		
	 * 		The most interesting aspect about the problem is the deep relation between DP and Recursive approach
	 * 		In this case I can clearly see that there is a connection between the memoization approach and the DP approach.
	 * 		The connection is the subproblem that is the same. I loved to have discovered this by myself.
	 * 		Despite being a pretty obvious observation. The thing was that at the time I didn't study both Memoization and DP at the same time.
	 * 		Let's see how it goes from here...
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {

		if (n == 1)
			return 1;

		int [] dp = new int [n + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++)
		{
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];

	}


	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * @intuition
	 * 		Memoization solution with inverse logic for memoization
	 * 		resulting in less code.
	 * @param n
	 * @return
	 */
	public int climbStairs1(int n) {

		Integer [] memo = new Integer[n + 1];
		memo[1] = 1;

		return calc2(n, memo);        
	}

	private int calc2(int n, Integer[] memo)
	{

		if (n < 0)
			return 0;

		if (n == 0)
			return 1;

		if(memo[n] == null)
		{
			memo[n] = calc2(n - 2, memo) + calc2(n - 1, memo);
		}

		return memo[n];
	}
}
