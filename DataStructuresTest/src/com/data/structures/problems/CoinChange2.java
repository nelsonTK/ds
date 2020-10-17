package com.data.structures.problems;

/**
 * https://leetcode.com/problems/coin-change-2/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CoinChange2 {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
    /**
    * @intuition
    * 		The sense of this question is using the coin vs not using the coin.
    * 		we start with zero coins and increase the number of coins by one.
    * 		for each iteration.
    * 		in the x axis we have all possible coins from 0 to the desirable amount.
    * 		in the y axis we have the coins used, from zero coins to all the coins
    * 		the position y - 1; x tells us what it is if we dont use the new coin
    * 		the position y ; x - newcoin tells us how it is to use the coin, where x is the coin amount.
    * 		dp[y][x - newcoin] means to search if there is a coin at which we can sum the newcoin to return the desired amount
    * 
    * 			not using = sum dp[r + 1][c] -> 
    *			using current = sum coin dp[r][c - coins[coinIndex]];
    *
    *	PROOFING
    * 		The toughest challenge here was to understand that actually it this method is mathematically proof,
    * 		something that I continue to not be able to proof that it will work for very large numbers...
    * 
    * 		So this solution for this problem is kinda useless to me, but the pattern is interesting
    * 
    * @optimization
    * 		I could use only one array
    * 
    * @score
    * 		Runtime: 9 ms, faster than 30.38% of Java online submissions for Coin Change 2.
    * 		Memory Usage: 45.7 MB, less than 37.65% of Java online submissions for Coin Change 2.
    * 
    * @fail for case where coins are zero
    * 
    * @time
    * 		O(Amout * coins)
    * 
    * @space 
    * 		O(Amount * coins)
    *
    **/
    public int change(int amount, int[] coins) {
        
        //len * number of coins
        if (coins == null || amount == 0 && coins.length == 0)
            return 1;
        
        if (amount != 0 && coins.length == 0)
        {
            return 0;
        }
        
        int [][] dp = new int[coins.length + 1][amount + 1];
        
        for (int i = 0; i < dp.length; i++)
        {
            dp[i][0] = 1;
        }
        
        int coin = 0;
        for (int r = 1; r < dp.length; r++)
        {
            for (int c = 1; c < dp[r].length; c ++)
            {
                dp[r][c] = dp[r - 1][c];
                
                if (c - coins[coin] >= 0)
                {
                    dp[r][c] += dp[r][c - coins[coin]];
                }
           }
            
            coin++;
        }
        
        return dp[dp.length - 1][dp[0].length - 1];
        
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This solution is very good in the sense that it only uses one array
 * but the logic is still the same than my solution
 * I really like this question
 * @author Nelson Costa
 *
 */
class CoinChange2Solution1 {
	  public int change(int amount, int[] coins) {
	    int[] dp = new int[amount + 1];
	    dp[0] = 1;

	    for (int coin : coins) {
	      for (int x = coin; x < amount + 1; ++x) {
	        dp[x] += dp[x - coin];
	      }
	    }
	    return dp[amount];
	  }
	}
