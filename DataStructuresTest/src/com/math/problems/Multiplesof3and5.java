package com.math.problems;

/**
 * https://projecteuler.net/problem=1
 * EASY
 * @author Nelson Costa
 *
 */
public class Multiplesof3and5 {

	public static void main(String[] args) {
		int input = 1000;
		System.out.println(solve(input));
	}

	/**
	 * @score
	 * 		You are the 963833rd person to have solved this problem.
	 * 		This problem had a difficulty rating of 5%. The highest difficulty rating you had previously solved was 0%. 
	 * 
	 * @time 
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * @param max
	 * @return
	 */
	private static int solve(int max) {
		
		int sum = 0;
		
		for (int i = 1; i < max; i++)
		{
			if(i % 3 == 0)
			{
				sum +=i;
			}
			else if (i % 5 == 0)
			{
				sum +=i;
			}
		}
		
		return sum;
	}
}
