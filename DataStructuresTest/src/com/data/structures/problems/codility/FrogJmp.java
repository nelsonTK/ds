package com.data.structures.problems.codility;
/**
 * https://app.codility.com/demo/results/trainingZD6AWQ-PEE/
 * https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/start/
 * EASY
 * @author Nelson Costa
 *
 */
public class FrogJmp {

	/**
	 * Too easy problem
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public int solution(int X, int Y, int D) {
	        // write your code in Java SE 8
	        
	        
	        if (X == Y)
	        return 0;
	        
	        return (int) Math.ceil((double)( Y - X )/D);
	    }
	}
}
