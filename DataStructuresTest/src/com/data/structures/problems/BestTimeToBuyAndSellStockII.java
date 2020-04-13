package com.data.structures.problems;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * EASY
 * @author Nelson Costa
 *
 */
public class BestTimeToBuyAndSellStockII {

	public static void main(String[] args) {
		BestTimeToBuyAndSellStockII b = new BestTimeToBuyAndSellStockII();
		
		int [] prices = new int[]{7,1,5,3,6,4};
		
		//prices = new int[]{1,2,3,4,5};
		
		System.out.println("Max profit: " + b.maxProfit(prices));
	}
	
    public int maxProfit1(int[] prices) {
        
    	if(prices == null || prices.length == 0 || prices.length == 1)
    		return 0;
    	
    	return trade(prices, -1, false, 0);
    }

    /**[WORKS BUT IS TIMEOUT AND RESULTS FROM AMBIGUITY OF THE QUESTION]
     * 
     * @failed sell calculations were wrong
     * timeout failed again
     * realized that the question was not explicit about allowing 
     * sell and buying in nthe same day, this was the tricky part
     * 
     * @time O(2^N)
     * @space O(N)
     * 
     * @param prices
     * @param day
     * @param holding
     * @param profit
     * @return
     */
	private int trade(int[] prices, int day, boolean holding, int profit) {

		if(day + 1 >= prices.length)
			return profit;
			
		if (holding)
		{
			//is holding, can sell or skip
			return Math.max(
					//sell
					trade(prices, day + 1, false, profit + prices[day + 1]), 
					//nothing
					trade(prices, day + 1, holding, profit));
		}
		else
		{
			//is not holding, can buy or skip
			return Math.max(
					//buy
					trade(prices, day + 1, true, profit - prices[day + 1]),
					//nothing
					trade(prices, day + 1, holding, profit));
		}
	}
	
	/**
	 * 		Runtime: 1 ms, faster than 93.39% of Java online submissions for Best Time to Buy and Sell Stock II.
			Memory Usage: 39.2 MB, less than 17.14% of Java online submissions for Best Time to Buy and Sell Stock II.

	 * @time 	O(N)
	 * @space 	O(1)
	 * @param prices
	 * @return
	 */
    public int maxProfit(int[] prices) {
        
    	int maxprofit = 0;
    	
    	for (int i =  0; i < prices.length - 1; i ++)
    	{
    		if (prices[i] < prices[i + 1] )
    		{
    			maxprofit += prices[i + 1] - prices[i]; 
    		}
    	}
    	
    	return maxprofit;
    }
}
