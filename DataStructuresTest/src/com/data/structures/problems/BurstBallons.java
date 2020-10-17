package com.data.structures.problems;

/**
 * https://leetcode.com/problems/burst-balloons/
 * HARD
 * @author Nelson Costa
 *
 */
public class BurstBallons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	/*********************************
	 * SOLUTION 1
	 ********************************/
	//I Messed up with the windows sizes, I was having my window too short
	/**
	 * @intuition
	 * 		The intuition is very tough to explain for this question.
	 * 		
	 * 		Instead of iterativelly trying to find all possible ways to burst all ballons we start for the end.
	 * 
	 * 		And from the end. Like we assume that a given ballon was the last to be burst.
	 * 
	 * 		And in that situation the result of that burst is the result of the sum of all bursts at its left, and at it's right plus the burst of its value itseft
	 * 
	 * 		that would be 1* num * 1.
	 * 
	 * 		so the formula to solve this problem is:
	 * 			[burst sum from left] + lastBurst + [burst sum from right]
	 * 			then we apply the same to the left side recursively untill the size is 1. (if we do it top down)
	 * 			
	 * 			left + cur + right = solution. 
	 * 
	 * 			We need to find out which one is the solution. 
	 * 
	 * 			A way of doing this is using a sliding window with size from 1 to n. and calculate the maximum value for each window size and start position.
	 * 
	 * 			that simulates firstly when no ballon has been burst (window size == 1)
	 * 			then simulates when one of the ballons where bursted (window size == 2)
	 * 			and later when all the ballons were bursted (window size == n), which will hold the greatest value.
	 * 			so for each window size we will always have the greatest value.
	 * 
	 * 			More notes on the algorithm in my personal notes about this problem.
	 * 			
	 * 
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 68.89% of Java online submissions for Burst Balloons.
	 *		Memory Usage: 37.5 MB, less than 42.81% of Java online submissions for Burst Balloons.
	 * 
	 * @recurrence
	 * 		T(N) = T(N-1)
	 * 
	 * @time
	 * 		N^3
	 * 
	 * @space
	 * 		N^2
	 * 
	 * @param nums
	 * @return
	 */
	public int maxCoins(int[] nums) {
		// create dp matrix size == nums.length + 2
		// add new array nums +2 len
		// fill the array from i =  i < nums.length (+1 when adding to new array)
		// fill position 0 and n with 1; 

		//create for from 1 to len of nums as window (winLen)
		//iterate from 1 to len of num - window (winStart)
		//iterate from 1 to 1 + winSize - 1 (lastBurst)

		//dp[winStart][winstart+winlen-1] = //Math.max();
		//winEnd = winStart + winlen - 1

		//int cur = array[lastBurst] * array[winEnd + 1] * array[winStart - 1];
		//if  (lastBurst + 1 >= winEnd) right is zero;
		//int right = dp[lastBurst + 1][winEnd];
		//if  (winstart >= lastBurst-1) left is zero;
		//int left =  dp[winStart][lastBurst-1];//--dp[lastBurst - 1][winStart];

		// dp[winStart][winEnd] = Math.max(left + cur + right, dp[winStart][winEnd]);

		if (nums == null || nums.length == 0)
			return 0;

		int [] arr = new int[nums.length + 2]; 
		int [][] dp = new int [arr.length][arr.length];


		//convert old array to [1, nums,  1]
		for (int i = 0; i < nums.length; i++)
		{
			arr[i + 1] = nums[i];
		}
		arr[0] = 1;
		arr[arr.length - 1] = 1;

		//perform dp with window logic
		for (int winlen = 1; winlen <= nums.length; winlen++)
		{
			for (int winStart = 1; winStart <= nums.length + 1 - winlen; winStart++)
			{
				for (int lastBurst = winStart; lastBurst < winStart + winlen; lastBurst++ )
				{
					int winEnd = winStart + winlen - 1;                    
					int cur = arr[lastBurst] * arr[winEnd + 1] * arr[winStart - 1];
					int right = dp[lastBurst + 1][winEnd];
					int left = dp[winStart][lastBurst - 1];

					dp[winStart][winEnd] = Math.max(left + cur + right, dp[winStart][winEnd]);
				}
			}
		}
		return dp[1][arr.length-2];
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * I cannot understand this solution quite well but I see similarities with my solution
 * @author Nelson Costa
 *
 */
class BurstBallonsSolution2{

	public int maxCoins(int[] nums) {
		// reframe the problem
		int n = nums.length + 2;
		int[] new_nums = new int[n];

		for(int i = 0; i < nums.length; i++){
			new_nums[i+1] = nums[i];
		}

		new_nums[0] = new_nums[n - 1] = 1;

		// dp will store the results of our calls
		int[][] dp = new int[n][n];

		// iterate over dp and incrementally build up to dp[0][n-1]
		for (int left = n-2; left > -1; left--)
			for (int right = left+2; right < n; right++) {
				for (int i = left + 1; i < right; ++i)
					// same formula to get the best score from (left, right) as before
					dp[left][right] = Math.max(dp[left][right],
							new_nums[left] * new_nums[i] * new_nums[right] + dp[left][i] + dp[i][right]);
			}

		return dp[0][n - 1];
	}
}