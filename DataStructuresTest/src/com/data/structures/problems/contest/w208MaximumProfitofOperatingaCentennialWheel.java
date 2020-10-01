package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/maximum-profit-of-operating-a-centennial-wheel/
 * MEDIUM(5)
 * @author Nelson Costa
 *
 */
public class w208MaximumProfitofOperatingaCentennialWheel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Solution {

		
		/**
		 * 
		 * @score
		 * 		Runtime: 8 ms, faster than 100.00% of Java online submissions for Maximum Profit of Operating a Centennial Wheel.
		 *		Memory Usage: 48.9 MB, less than 100.00% of Java online submissions for Maximum Profit of Operating a Centennial Wheel.
		 * 
		 * @fail
		 * 		1) there was something in my first implementation that was not right, had to take the while from the loop
		 * 		2) I didn't quite understand why extracting the second part of the algo from the for loop did not worked
		 * 
		 * @time
		 * 		
		 * 
		 * @space
		 * 
		 * @param c
		 * @param boardingCost
		 * @param runningCost
		 * @return
		 */
		public int minOperationsMaxProfit(int[] c, int boardingCost, int runningCost) {
			int wait = 0, onBoardingC = 0, profit = 0, maxRotations = 0, maxProfit = 0;
			int totalRotations = 0; 
			boolean updatedMaxProfit = false;

			for (int newC : c)
			{
				wait += newC;
				//mandatory rotation
				if (wait < 4 && wait > 0 ) 
				{
					onBoardingC = wait;
					wait = 0;
				}
				else if (wait >= 4 )
				{
					wait -= 4;
					onBoardingC = 4;
				}

				totalRotations++;
				profit = onBoardingC * boardingCost - 1 * runningCost + maxProfit;

				//check if maximum
				if(profit > maxProfit)
				{
					maxRotations = totalRotations;
					maxProfit = profit;
					updatedMaxProfit = true;
				}

			}


			//calculate remaning positions
			while (wait >= 4)
			{
				wait -= 4;
				onBoardingC = 4;
				totalRotations++;
				profit = onBoardingC * boardingCost - 1 * runningCost + maxProfit;

				//check if maximum
				if(profit > maxProfit)
				{
					maxRotations = totalRotations;
					maxProfit = profit;
					updatedMaxProfit = true;
				}
			}


			//for remaining
			if (wait < 4 && wait > 0)
			{

				onBoardingC = wait;
				totalRotations++;
				profit = onBoardingC * boardingCost - 1 * runningCost + maxProfit;

				//check if maximum
				if(profit > maxProfit)
				{
					maxRotations = totalRotations;
					maxProfit = profit;
					updatedMaxProfit = true;
				}
			}

			return maxProfit >= 0 && updatedMaxProfit ? maxRotations : -1;
		}
	}
}
