package com.data.structures.problems;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumCostForTickets {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		The gist of this problem is to start from the end to the begining and calculate the best value for each number.
	 * 		If the day is not in the list of days we copy from the previous day.
	 * 		This is kinda inspired in knapsack but on contrary, first exercise of this time.
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 74.24% of Java online submissions for Minimum Cost For Tickets. 
	 * 		Memory Usage: 36.9 MB, less than 58.10% of Java online submissions for Minimum Cost For Tickets.
	 * 
	 * @fail 
	 * 		I was not thinking in the case where we end the array days, but have not calculated the day one.
	 * 
	 * 
	 * @time
	 * 		O(N*3) => O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 **/
	public int mincostTickets(int[] days, int[] costs) {

		int [] dp = new int [days[days.length - 1] + 1];
		int curDay =days.length - 1;
		int min, prev;
		int [] tExpire = {1,7,30}; //ticket expire after 1, after 7 and after 30

		for (int day = dp.length - 1; day >= 1; day--)
		{
			//if not the same day we coppy the previous value to the current
			if (curDay < 0 || day != days[curDay]){
				dp[day] = dp[day+1];
				continue;
			}
			//if same value we will check which is the best option
			else{
				min = Integer.MAX_VALUE;
				for (int ticket = 0; ticket < costs.length; ticket++)
				{
					//if out of bounds we know the previous value is 0.
					if (day + tExpire[ticket] >= dp.length)
					{
						prev = 0;
					}
					//else we get the value of the last day already calculated after the ticket has expired.
					else
					{
						prev = dp[day + tExpire[ticket]];
					}

					min = Math.min(costs[ticket] + prev, min);

				}

			}

			dp[day] = min;
			curDay--;
		}
		return dp[1];
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * I don't like this solution, I think it is a mess
 * @author Nelson Costa
 *
 */
class MinimumCostForTicketsSolution2 {
    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];

        return dp(0);
    }

    public int dp(int i) {
        if (i >= days.length)
            return 0;
        if (memo[i] != null)
            return memo[i];

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }
}