package com.data.structures.problems;

/**
 * https://leetcode.com/problems/arranging-coins/
 * EASY
 * @author Nelson Costa
 *
 */
public class ArrangingCoins {

	public static void main(String[] args) {
		ArrangingCoins a = new ArrangingCoins();
		
		int n = 1804289383;
		System.out.println("n: " + n);
		System.out.println("the full stair case is: " + a.arrangeCoins(n));
	}
	
	/**
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Arranging Coins.
			Memory Usage: 37 MB, less than 5.26% of Java online submissions for Arranging Coins.
	 * 
	 * @fail
	 * 		1) I failed because I miss the invocation of coins required, I inputed n instead of mid
	 * 		2) failed forgot to add long to the result of coinsRequired, I had note it but I was careless
	 * 		
	 * 
	 * @intuition
	 * 		In this solution the search space is from 1 to n.
	 * 		Then for each element in the search space I calculate the sum of all previous integers and itself 
	 * 		Then we compare if the value obtained, is greater of lesser than our n.
	 * 		if greater we go to left, if greater we go to right
	 * 		The answer will be stored in the right pointer
	 * 
	 * @alternative 
	 * 		could be to do linear search
	 * 
	 * @optimizations 
	 * 		If I was sure a possible optimization would be to cut the search space to half size.
	 * 		But I'm not completly sure
	 * 
	 * @time O(log n)
	 * @bcr	 O(log n) | Missed, it was possible to acomplish in O(1) with matematical formula	
	 * @space O(1)
	 * 
	 * @param n
	 * @return
	 */
    public int arrangeCoins(int n) {
        
    	if (n == 1 || n == 0)
    		return n;
    	
    	int left = 1;
    	int right = n;
    	int mid;
    	
    	while (left <= right)
    	{
    		mid = left + (right - left)/2;
    		
    		if (coinsRequired(mid) > n)
    		{
    			right = mid - 1;
    		}
    		else //(coinsRequired(n) <= n)
    		{
    			left = mid + 1;
    		}
    	}
    	
    	return right;
    }
    
    
    /**
     * 
     * Carl Gauss
     * formula to calculate the sum of all the nums from 1 to n.
     * Beautiful formula
     * 
     * @param n
     * @return
     */
    public long coinsRequired(int n) {
    	return (long) n * (n + 1)/2;
    }

}
