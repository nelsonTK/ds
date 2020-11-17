package com.math.problems;

public class EvenFibonaccinumbers {

	public static void main(String[] args) {
		int limit = 4_000_000;
		System.out.println(solve(limit));
	}

	/**
	 * 
	 * You are the 766769th person to have solved this problem.
	 * This problem had a difficulty rating of 5%. The highest difficulty rating you have solved remains at 5%.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param limit
	 * @return
	 */
	private static long solve(int limit) {
		
		int beforePrevious = 1;
		int previous = 2;
		int sum = 2, tmp = 0;
		for (int i = 3; sum <= limit;i++)
		{
			tmp = beforePrevious + previous;
			sum += (tmp) % 2 == 0? tmp : 0;
			beforePrevious = previous;
			previous = tmp;
			System.out.println(i);
		}
		
		return sum;
	}
}
