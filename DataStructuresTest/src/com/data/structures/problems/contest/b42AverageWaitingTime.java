package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/average-waiting-time/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b42AverageWaitingTime {
	
    /**
     * @intuition
     * 		I started of thinking in DP and evolve to optimized space version
     * 	
     * @fail forgot about int limits
    **/
    public double averageWaitingTime(int[][] customers) {
        
        int offset = 0, prevEnd = 0;
        int sum = 0;
        long total = 0;
        
        for(int i = 0; i < customers.length; i++)
        {
            //if previous ends after the start of current customer we add offset
            if (prevEnd > customers[i][0])
            {
                offset = prevEnd - customers[i][0];
            }
            else
            {
                offset = 0;
            }
            
            
            //add waiting time, offset + task duration
            sum = offset + customers[i][1];
            total += sum;
            
            //update the end of this customer to the new value
            prevEnd = customers[i][0] + sum;
        }
        
        return total/(double) customers.length;
    }
}
