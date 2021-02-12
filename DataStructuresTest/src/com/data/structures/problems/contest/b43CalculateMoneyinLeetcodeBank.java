package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/calculate-money-in-leetcode-bank/
 * EASY
 * @author Nelson Costa
 *
 */
public class b43CalculateMoneyinLeetcodeBank {
	
	/**
	 * @intuition
	 * 		I could have better coding
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public int totalMoney(int n) {
	        
	        int sum = 1;
	        int curr = 1;
	        int start = 1;
	        for (int i = 1; i < n; i++){
	            if ((i + 1) % 7 == 0)
	            {
	                curr++;
	                sum += curr;                
	                curr = start;
	                start++;
	            }
	            else
	            {
	                curr++;
	                sum+= curr;
	            }
	        }
	        return sum;
	    }
	}
}
