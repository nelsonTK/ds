package com.data.structures.problems;

/**
 * https://leetcode.com/problems/fibonacci-number/
 * EASY
 * @author Nelson Costa
 *
 */
public class FibonacciNumber {

	public static void main(String[] args) {
		int n = 10;
		FibonacciNumber f = new FibonacciNumber();
		System.out.println("input: " + n);
		System.out.println("fibonacci: " + f.fib(n));
	}
	
	/**
	 * @Intuition
	 * 		Eventually this is Dynamic programming bottom up approach
	 * 
	 * @score
			 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
				Memory Usage: 36 MB, less than 5.51% of Java online submissions for Fibonacci Number.
	 * @alternatives
	 * 		Top down recursive aproach with or without memoization
	 * 
	 * @fail
	 * 
	 * 
	 * 
	 * @time O(N)
	 * @space O(1)
	 * @bcr   O(1) if there is a direct formula for calculating fibonacci number
	 * 
	 * @param n
	 * @return
	 */
    public int fib(int n) {
        
    	if (n == 1 || n == 0)
    		return n;
    	
    	int current = 0;
    	int prev = 1;
    	int beforePrev = 0;
    	
    	for (int i = 2; i <= n; i++)
    	{
    		current = prev + beforePrev;
    		beforePrev = prev;
    		prev = current;
    	}
    	
    	return current;
    }
    
    /**
     * @intuition
     * 		Dynamic programing top-down approach
     * 
     * 
     * @alternatives
     * 		Matrix exponentiation and binets formula but for now is something I don't understand and don't plan to do it in the upcoming weeks
     * 
     * @time 	O(N) because each node is calculated once
     * @space	O(N) depth of the solution tree
     * @bcr		O(1)
     * 
     * @param n
     * @return
     */
    public int fib0(int n) {
    	
    	int [] memo = new int[n + 1];
    	
    	return calc(n, memo);
    }
    
    public int calc(int n, int [] memo)
    {
    	if (n <= 1)
    		return n;
    	
    	if(memo[n] != 0)
    		return memo[n];
    	
    	memo[n] = calc(n - 1, memo) + calc(n - 2, memo);
    	
    	return memo[n];
    }
}
