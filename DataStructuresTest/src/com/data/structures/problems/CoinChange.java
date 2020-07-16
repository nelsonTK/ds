package com.data.structures.problems;

import java.util.Arrays;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/coin-change/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CoinChange extends LeetCodeExercise{

	static CoinChange c = new CoinChange();

	public static void main(String[] args) {

		int [] coins = stringToArray("[1, 2, 5]");
		int amount = 2310;
		coins = stringToArray("[1,2147483647]");
		amount = 2;
		coins = stringToArray("[1, 3]");
		amount = 2;
		coins = stringToArray("[2]");
		amount = 3;

		System.out.println(c.coinChange(coins, amount));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	        @fail
	            1) stackoverflow
	            2) bad math at binary search invariant
	            3) error on assuming that I would catch all values inside binary search code
	            4) error on solving the binary search code situation
	            5) didn't had into account integer overflow
	            6) didnt cover the case where array is size 2... was spotter as a vulnerability but I failed
	            7) this is fundamentally wrong I though that the biggest coin would always be in the answer
	 **/
	public int coinChange0(int[] coins, int amount) {
		//guards
		if (coins == null || coins.length == 0)
			return -1;

		if (coins.length == 1)
			return amount % coins[0] == 0 ? amount/coins[0] : -1;


			int ans = solve(coins.length - 1, amount, coins, 0, 0);

			return ans;

	}

	public int solve(int high, int amount, int[] coins, int sum, int coinsSum)
	{


		if (sum == amount)
			return 1;

		if (sum > amount)
			return -1;


		int low = 0, mid = 0;
		while (high > 0)
		{
			low = 0;

			while (low < high)
			{
				mid = low + (high + low)/2;

				if (amount - (coins[mid] + sum) == 0)
				{
					return 1;
				}
				else if (amount - (coins[mid] + sum) > 0)
				{
					low = mid + 1;
				}
				else if (amount - (coins[mid] + sum) < 0)
				{
					high = mid;
				}
			}

			if (amount - (coins[low] + sum) == 0)
			{
				return 1;

			}
			else if (coins[low] > 0)
			{       
				int result = solve (high, amount, coins, sum + coins[low], coinsSum + 1);
				if (result > 0)
					return result + 1;
			}

			high--;
		}

		return -1;
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 	dp inspired solution, but a little bit distorted.
	 * 	the base case is all coins will start with 1.
	 * 	and from there the other values are built.
	 * 
	 * 	it is fundamental to understand that you cannot use a greedy solution, it just don't work as a solution
	 * 
	 * @score
	  		Runtime: 25 ms, faster than 29.39% of Java online submissions for Coin Change.
			Memory Usage: 39.1 MB, less than 61.89% of Java online submissions for Coin Change.
	 *
	 *  @fail
	 *       1) Arrays out of bounds, I was using coins array intead of dp's array
	 *       2) forgot to update the coin variable
	 *       3) putter the wrong amount of coins in the first element
	 *       4) I was not initiallizing dp values for coin indexes properly
	 *       5) array of of bounts wen coins are bigger than the amount
	 *
     *  @debug
     *  		yes
	 **/
	public int coinChange1(int[] coins, int amount) {
		//guards
		if (amount == 0)
			return 0;

		int [] dp = new int[amount + 1];
		int target, coinsUsed, coin;
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[0] = 0;
		//initiallize dp array
		for (int i : coins)
		{
			if (i < dp.length)
				dp[i] = 1;
		}

		for (int i = 1; i <= amount; i++)
		{
			for (int j = 0; j < coins.length; j++)
			{
				target = i;
				coin = coins[j];
				coinsUsed = 0;

				while (coin < dp.length && target > 0 && coin <= target && dp[coin] != Integer.MAX_VALUE)
				{
					target -= coin;
					coinsUsed += dp[coin];
					coin = target;
				}

				if (target == 0)
				{
					dp[i] = Math.min(coinsUsed, dp[i]);
				}
			}
		}

		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}
	
	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * @intuition
	 * 		proper implementation of dp solution
	 * 		starting from the bottom
	 * 		base case 0 = 0.
	 * 		and all other values are built from 
	 * 
	 * @score
	 * 		Runtime: 13 ms, faster than 52.61% of Java online submissions for Coin Change.
			Memory Usage: 39 MB, less than 75.89% of Java online submissions for Coin Change.
	 * 
	 * 
	 * @fail
	 * 		1) coins.length instead of coins.length - 1
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {

		if (amount == 0)
			return 0;

		int [] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i <= amount; i++)
		{
			for (int j = 0; j < coins.length; j++)
			{
				if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE)
					dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
			}
		}

		return dp[amount] ==  Integer.MAX_VALUE ? -1 : dp[amount];
	}
}



/**
 * DP Solution almost equals to mine, the min diference is the max value
 * which allows one validation less, this is cleaver because it allows us to avoid overflow
 * 
 * @author Nelson Costa
 *
 */
class CoinChangeSolution3 {
	public int coinChange(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}
}


/**
 * Top down approach
 * @author Nelson Costa
 *
 */
class CoinChangeSolution2 {

	public int coinChange(int[] coins, int amount) {
		if (amount < 1) return 0;
		return coinChange(coins, amount, new int[amount]);
	}

	private int coinChange(int[] coins, int rem, int[] count) {
		if (rem < 0) return -1;
		if (rem == 0) return 0;
		if (count[rem - 1] != 0) return count[rem - 1];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = coinChange(coins, rem - coin, count);
			if (res >= 0 && res < min)
				min = 1 + res;
		}
		count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[rem - 1];
	}
}