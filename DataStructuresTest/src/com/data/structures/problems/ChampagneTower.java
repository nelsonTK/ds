package com.data.structures.problems;

/**
 * https://leetcode.com/problems/champagne-tower/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ChampagneTower {

    //Runtime: 5 ms, faster than 40.94% of Java online submissions for Champagne Tower.
    //Memory Usage: 39.3 MB, less than 25.17% of Java online submissions for Champagne Tower.    
    //
    //
	/**
	 * @intuition
	 * 		This was a great exercise for DP, multidimentional arrays and pascal triangle
	 * 		The observations are dope.
	 * 		The gist is to start with the total value at the top, and always divide the over flow by 2 for each cup on its side.
	 * 		If there is no overflow you don't do this.
	 * 		
	 * @score
	 * 		Runtime: 5 ms, faster than 40.94% of Java online submissions for Champagne Tower.
	 * 		Memory Usage: 39.3 MB, less than 25.17% of Java online submissions for Champagne Tower.  
	 * 
	 * 	@fail
	 * 		failed with wrong pointers to dp , distraction mistake, lack of verification
	 * 		forgot to truncate the output if value bigger than 1
	 * 
	 * @time
	 * 		O(100 * 100) => O(1)
	 * 
	 * @space
	 * 		O(100 * 100) => O(1)
	 * 
	 * @param poured
	 * @param query_row
	 * @param query_glass
	 * @return
	 */
    public double champagneTower(int poured, int query_row, int query_glass) {
        
        //create triangle
        double [][] dp = new double[100 + 1][];
        for (int i = 0; i < 100 + 1; i++)
        {
            dp[i] = new double[i + 1];
        }
        
        dp[0][0] = poured;
        
        for (int row = 0; row < 100; row++)
        {
            for (int glass = 0; glass < dp[row].length; glass++)
            {
                
                if (dp[row ][glass] > 1)
                {
                    dp[row + 1][glass] = (dp[row ][glass] - 1)/2 + dp[row + 1][glass];
                    dp[row + 1][glass + 1] = (dp[row][glass] - 1)/2 + dp[row + 1][glass + 1];
                }  
            }
        }
        
        //I could have used the max function
        return dp[query_row][query_glass] > 1 ? 1 : dp[query_row][query_glass];
    }
}
