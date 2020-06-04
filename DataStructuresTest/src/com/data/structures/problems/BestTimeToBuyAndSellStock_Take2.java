package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * EASY
 * @author Nelson Costa
 *
 */
public class BestTimeToBuyAndSellStock_Take2 {

	public static void main(String[] args) {
		int [] a = {7,6,4,3,1};
		BestTimeToBuyAndSellStock b = new BestTimeToBuyAndSellStock();
		System.out.println("input: " + Arrays.toString(a));
		System.out.println("profit: " + b.maxProfit(a));
	}

	/**
	 * 
	 * @intuition
	 * 		This is a DP problem, where the table is the input itself.
	 * 		We start in the end and go till the beginning of the array.
	 * 		the answer is in the max variable.
	 * 
	 * 		sub problem:
	 * 			dp[i] = Max (dp[i], dp[i + 1])
	 * 
	 * 		
	 * 		In simple terms, What I do is to compare the current price with the highest possible price that is still to coming.
	 * 
	 * @score
			Runtime: 1 ms, faster than 99.18% of Java online submissions for Best Time to Buy and Sell Stock.
			Memory Usage: 39.1 MB, less than 25.66% of Java online submissions for Best Time to Buy and Sell Stock.
			
	   @fail
	        1) I was increasing the index instead of decreasing
	        2) forgot to skip the first element of dp
	        3) wrong signal I add i - 1 instead of i + 1
	        4) max variable was wrongly setup with the last element of the array
	        5) order of subtraction is wrong


	    @debug
	        yes (contest level)

	    @time    O(N)
	    @space   O(1)

	 **/
    public int maxProfit(int[] prices) {
        
    	if (prices == null)
        	return 0;
        
        int n = prices.length - 1;
        
        int max = 0, tmp;
        
        for (int i = n - 1; i >= 0; i--)
        {
            tmp = prices[i];
            
            prices[i] = Math.max(prices[i], prices[i + 1]);
            
            max = Math.max(prices[i + 1] - tmp, max);
        }
        
        return max;
    }
}

/**
 * Better solution in O(N) Time
 * The logic is simple the min is always behind and we update as we progress
 * My initial solution was diferent, 
 * the max is always at the front. and that subtle different led to a more complicate and less performant algorithm
 * 
 * @author Nelson Costa
 *
 */
class BestTimeToBuyAndSellStockSolution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
