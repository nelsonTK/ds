package com.data.structures.problems.codility;

/**
 * https://app.codility.com/demo/results/trainingQDR47C-AN5/ (80%, forgot about int limits)
 * https://app.codility.com/demo/results/trainingGX35CB-TCA/
 * @author Nelson Costa
 *
 */
public class PermMissingElem {

	// you can also use imports, for example:
	// import java.util.*;

	// you can write to stdout for debugging purposes, e.g.
	// System.out.println("this is a debug message");

	/**
	 * @intuition
	 * 		this is just a math problem the trickiest thing in here was the int limits...
	 * 		I should be careful to not let the limit pass
	 * 
	 * 		trickier than leetcode's version, because of that detail, I should have tested if we go of bounds
	 * 	
	 * @author Nelson Costa
	 *
	 */
	class Solution {
		public int solution(int[] a) {

			if (a == null || a.length == 0)
				return 1; // this is one or zero missing, but I'll go with the 1

			long n = a.length + 1;
			long total =  (n * (n + 1)) / 2;//(n * (n - 1))/2;
			long sum = 0;

			for (int i = 0; i < a.length; i++)
			{
				sum += a[i];
			}

			return (int) (total - sum);
			/*
	        Trying to find out carl gauss formula
	        (1 * 0)/2           => 0
	        (2 * (2 - 1))/2     => 2*1/2 = 1
	        (3 * (2))/2         => 3    = 0 + 1 + 2
	        (4 * 3)/2           => 6    = 0 + 1 + 2 + 3
	        (5 * 4)/2           => 10    = 0 + 1 + 2 + 3 + 4

	        ((n - 1) * ((n -1) - 1)) / 2
	        ((4 - 1) * (3 - 1)) / 2
	        (3 * 2) / 2 => 6/2 => 3

	        ((n + 1) * ((n + 1) - 1)) / 2  => THIS IS THE CARL GAUSS FORMULA which translates to ((n + 1) * (n))/2

	        n = 4
	        (5 * (5 - 1))/2 => 
	        (5 * 4)/2       => 10 => 0 + 1+ 2+ 3+ 4
			 */
		}
	}

}
