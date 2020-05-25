package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

public class HouseRobber extends LeetCodeExercise{
	static HouseRobber h = new HouseRobber();
	public static void main(String[] args) {

		int [] nums = stringToArray("[1,2,3,1]");
		System.out.println(h.rob(nums));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/

	/**
	 * 
	 * 	@intuition
	 * 		to calculate all the possibilities of amounts to rob, I used DP.
	 * 		the optimal subproblem is: 
	 * 			the maximum amount that can be robbed is the maximum between the current house and the best value of the houses not next but before the next.
	 * 			And the previous house plus the best value of the houses before the house previous to the previous house.
	 * 			confusing han?
	 * 
	 * 			Recursive Formula/subproblems (and how to fill the table )
	 * 				G(n) = Max(Nums[n] + G(n - 2), N[n-1] + G(n -3))
	 * 
	 * 			Base Case
	 *    			G(0) = 0;
	 *     			G(1) = Nums[0];

    	@fail
    		1) the dp formula was not perfect, forgot to add the prev to the equation

    	@time  O(N)
    	@space O(N)

	 **/
	public int rob0(int[] nums) {

		if (nums == null || nums.length == 0)
		{
			return 0;
		}

		int [] dp = new int[nums.length + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		int max = nums[0];
		int prev;
		for (int i = 1; i < nums.length; i++)
		{
			prev = i - 2 < 0 ? 0 : i - 2;

			dp [i + 1] = Math.max(dp[i - 1] + nums[i], nums[i - 1] + dp[prev]);
			max = Math.max(dp[i + 1], max);
		}

		return max;
	}



	/*********************************
	 * SOLUTION 1
	 ********************************/

	/**
	 * 	[ Cleaner Code]
	 * 
	 * 
	 *  @comments
	 *  	this solution can be even more simplified
	 * 
	 * 	@intuition
	 * 		to calculate all the possibilities of amounts to rob, I used DP.
	 * 		the optimal subproblem is: 
	 * 			the maximum amount that can be robbed is the maximum between the current house and the best value of the houses not next but before the next.
	 * 			And the previous house plus the best value of the houses before the house previous to the previous house.
	 * 			confusing han?
	 * 
	 * 			Recursive Formula/subproblems (and how to fill the table )
	 * 				G(n) = Max(Nums[n] + G(n - 2), N[n-1] + G(n -3))
	 * 
	 * 			Base Case
	 *    			G(0) = 0;
	 *     			G(1) = Nums[0];

		@score
			1) 	Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
				Memory Usage: 38.8 MB, less than 5.26% of Java online submissions for House Robber.

    	@fail
    		1) the dp formula was not perfect, forgot to add the prev to the equation

    	@time  O(N)
    	@space O(N)

	 **/
	public int rob1(int[] nums) {

		if (nums == null || nums.length == 0)
		{
			return 0;
		}

		if (nums == null || nums.length == 1)
		{
			return nums[0];
		}

		int [] dp = new int[nums.length + 1];
		int max = Math.max(nums[0], nums[1]);
		dp[0] = 0;
		dp[1] = nums[0];
		dp[2] = max;

		for (int i = 2; i < nums.length; i++)
		{

			dp [i + 1] = Math.max(dp[i - 1] + nums[i], nums[i - 1] + dp[i - 2]);
			max = Math.max(dp[i + 1], max);
		}

		return max;
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**
	 * 	[ Cleaner Code]
	 * 
	 * 
	 *  @comments
	 *  	this solution can still be even more simplified
	 *  	The indexes is are a mess, and I didn't needed to add the index zero.
	 * 
	 * 	@intuition
	 * 		to calculate all the possibilities of amounts to rob, I used DP.
	 * 		the optimal subproblem is: 
	 * 			the maximum amount that can be robbed is the maximum between the current house and the best value of the houses not next but before the next.
	 * 			And the previous house plus the best value of the houses before the house previous to the previous house.
	 * 			confusing han?
	 * 
	 * 			Recursive Formula/subproblems (and how to fill the table )
	 * 				G(n) = Max(Nums[n] + G(n - 2), N[n-1] + G(n -3))
	 * 
	 * 			Base Case
	 *    			G(0) = 0;
	 *     			G(1) = Nums[0];

		@score
			1) 	Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
				Memory Usage: 38.8 MB, less than 5.26% of Java online submissions for House Robber.

    	@fail
    		1) the dp formula was not perfect, forgot to add the prev to the equation

    	@time  O(N)
    	@space O(N)

	 **/
	public int rob(int[] nums) {

		if (nums == null || nums.length == 0)
		{
			return 0;
		}

		if (nums == null || nums.length == 1)
		{
			return nums[0];
		}

		int [] dp = new int[nums.length + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		dp[2] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++)
		{
			dp [i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
		}

		return dp[nums.length];
	}


}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * DP Solution with optimized space complexity.
 * 
 * it does not look like DP, but it is because we start from the bottom, very elegant
 * 
 * @time 	O(N)
 * @space 	O(1)
 * @author Nelson Costa
 *
 */
class HouseRobberSolution1{
	public int rob(int[] num) {
		int prevMax = 0;
		int currMax = 0;
		for (int x : num) {
			int temp = currMax;
			currMax = Math.max(prevMax + x, currMax);
			prevMax = temp;
		}
		return currMax;
	}
}

/**
 * Youtube Solution
 * @author Nelson Costa
 *
 */
class HouseRobberSolution2{

	public int rob(int[] nums) { 
		int n = nums.length;
		if( n == 0 ) return 0;

		if(n == 1) return nums[0];

		if(n == 2) return Math.max(nums[0], nums[1]);                        

		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);                                 

		for(int i = 2; i < n; i++){
			dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
		}                        
		return dp[n-1];
	}
}