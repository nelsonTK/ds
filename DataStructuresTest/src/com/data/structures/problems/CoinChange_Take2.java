package com.data.structures.problems;

import java.util.HashMap;

public class CoinChange_Take2 {

	    /**
	     * @intuition
	     * 		the memoization here takes place because you calculate the minimum value just once, 
	     * 		starting from top down, but you can store the values going up.
	     * 
	     * @score
	     * 		Runtime: 152 ms, faster than 6.14% of Java online submissions for Coin Change.
	     * 		Memory Usage: 40.2 MB, less than 20.51% of Java online submissions for Coin Change.
	     * 
	     * @time
	     * 		O(N * Coins)
	     * 
	     * @space
	     * 		O(N)
	    **/
	    public int coinChange(int[] coins, int amount) {
	        HashMap<Integer, Integer> memo = new HashMap<>();
	        int ans = calculate (coins, amount, memo);
	        return ans == Integer.MAX_VALUE ? -1 : ans;
	    }
	    
	    
	    private int calculate(int [] coins, int amount,  HashMap<Integer, Integer> memo )
	    {
	        
	        if (amount < 0)
	            return -10;
	        
	        if (amount == 0)
	            return 0;
	        
	        if (memo.get(amount) != null)
	            return memo.get(amount);
	        
	        int min = Integer.MAX_VALUE;
	        
	        for (int i = 0; i < coins.length; i ++)
	        {
	            int result = calculate(coins, amount - coins[i], memo) + 1;
	            
	            if (result > 0 && min > result)
	            {
	                min = result;
	            }
	        }
	        memo.put(amount, min);
	        return min;
	    }

}
