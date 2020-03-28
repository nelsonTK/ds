package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Queue;

public class NthTribonacciNumber {

	public static void main(String[] args) {	    
	    int n = 7;
	    NthTribonacciNumber t = new NthTribonacciNumber();
	    System.out.println(n);
	    System.out.println(t.tribonacci(n));

	}

	/**
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for N-th Tribonacci Number.
Memory Usage: 36.6 MB, less than 10S0.00% of Java online submissions for N-th Tribonacci Number.
	 * 
	 * This is my iterative solution.
	 * I could have done a recursive solution but I think it was too much of an expense.
	 * 
	 * @failed when assigning the value to the result variable. it is not accomulated. I should have tested that manually.
	 * 
	 * @time O(n)
	 * @space O(1)
	 * @param n
	 * @return
	 */
    public int tribonacci(int n) {
       
    	if (n < 0 || n > 37)
    		throw new IllegalArgumentException();
    	
    	if (n == 0)
        {
        	return 0;
        }
        
        if (n == 1 || n == 2)
        {
        	return 1;
        }
        
        int [] tribonacci = new int[] {1, 1, 0};
        int result = 0;
        
        for (int i = 0; i <= n - 3; i++) { //starts with 3
			result = tribonacci[0] + tribonacci[1] + tribonacci[2];
			tribonacci[2] = tribonacci[1];
			tribonacci[1] = tribonacci[0];
			tribonacci[0] = result;
		}
        
        return result;
    }
}
