package com.data.structures.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * EASY
 * @author Nelson Costa
 *
 */
public class BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
//		int [] a = {7,1,5,3,6,4};
		int [] a = {7,6,4,3,1};
		BestTimeToBuyAndSellStock b = new BestTimeToBuyAndSellStock();
		System.out.println("input: " + Arrays.toString(a));
		System.out.println("profit: " + b.maxProfit(a));
	}
	
	/**
	 * @intuition
	 * 		in each position of the array we have an idea of the biggest element that is comming and we test the profit of selling there
	 * 		against our final profit variable. the biggest wins
	 * 
	 * @score
	 * 		Runtime: 5 ms, faster than 16.33% of Java online submissions for Best Time to Buy and Sell Stock.
			Memory Usage: 39.3 MB, less than 23.01% of Java online submissions for Best Time to Buy and Sell Stock.
	 * 
	 * @time  O(nlog n [+ O(n)])
	 * @space O(N)
	 * @bcr   O(N) | not sure if it is possible to achieve this. it was possible and the solution is behind
	 * 
	 * @param a
	 * @return
	 */
    public int maxProfit(int[] a) {
        
    	if (a == null || a.length <= 1)
    		return 0;
    	
    	
    	int profit = 0;
    	
    	PriorityQueue<int[]> max = new PriorityQueue<int[]>(a.length, 
    			(x, y) -> Integer.compare(y[0], x[0]));
    	
    	for (int i = 0; i < a.length; i++) {
			max.offer(new int[] {a[i], i});
		}
    	
    	for (int i = 0; i < a.length; i++) {
			
    		//while peek is outdated remove peak
    		while (!max.isEmpty() && max.peek()[1] <= i)
    		{
    			max.poll();
    		}
    		
    		if (!max.isEmpty())
    		{
    			profit = Math.max(max.peek()[0] - a[i], profit);
    		}
		}
    	
    	return profit;
    }
}