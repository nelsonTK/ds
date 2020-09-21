package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
 * EASY
 * @author Nelson Costa
 *
 */
public class b35SumofAllOddLengthSubarrays {
	/**
	 * @intuition	
	 * 		Apply dinamic programming using window style
	 * 		I could have do it without windows style DP
	 * 
	 * @score
	 * 		Runtime: 3 ms, faster than 50.00% of Java online submissions for Sum of All Odd Length Subarrays.
	 *		Memory Usage: 38.7 MB, less than 50.00% of Java online submissions for Sum of All Odd Length Subarrays
	 *
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N^2)
	 * 
	 * @param arr
	 * @return
	 */
    public int sumOddLengthSubarrays(int[] arr) {
        
        
        int total = 0;
        int [][] dp = new int [arr.length + 1][arr.length + 1];
        for (int wsize = 1; wsize <= arr.length; wsize++)
        {
            for (int start = 1; start <= dp[0].length - wsize; start++)
            {
                int curPosition = start + wsize - 1;
                dp[start][curPosition] = dp[start][curPosition - 1] + arr[curPosition-1];

                if (wsize % 2 == 1 || wsize == 1)
                {
                    total += dp[start][curPosition];
                }
            }
        }
        
        return total;
    }
}

