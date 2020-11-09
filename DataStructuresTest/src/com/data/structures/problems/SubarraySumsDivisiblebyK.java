package com.data.structures.problems;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SubarraySumsDivisiblebyK {

	/**
	 * 
	 * Combinatorics formula
	 * 		NCr = N! /(r! * (N - r)!)
	 * 		but in this case our r is always 2.
	 * 		so if we apply the formula and apply cuts it ends up like:
	 * 
	 * 		NCr = (n * (n - 1))/2  //just do a couple of examples on paper and you will see it clearly
	 * 
	 * 
	 * When module is 0 it is an edge case
	 * 		the formula is NCr + N. because the values from begining to current are also valid thats the N adition.
	 * 
	 * Test to:
	 * 		Modulo: negative and positive modulo management
	 * 
	 * 		Test to comulative sum properties / prefix Sum
	 * 
	 * 	@intuition
	 * 		Extremely hard question. based on prefix sum and combinatorics.
	 * 		
	 * 		1) prefix sum
	 * 		2) do module on prefix sum
	 * 		3) elements with same module means elements between that i(exclusive) and j (inclusive) is divisible by k
	 * 		4) so it is the combination of modules grouped, with special care to zero, that we need to add the values from zero to ith
	 * 
	 * @score
	 * 		Runtime: 5 ms, faster than 85.12% of Java online submissions for Subarray Sums Divisible by K.
	 * 		Memory Usage: 42.3 MB, less than 5.71% of Java online submissions for Subarray Sums Divisible by K.
	 * 		
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(k)
	 * 
	 */
	public int subarraysDivByK(int[] original, int k) {

		int [] counts = new int[k];
		int sum = 0, mod;
		for (int i : original)
		{
			sum += i;

			mod = ((sum % k) + k) % k;

			counts[mod]++;
		}

		int result = counts[0];
		for (int n : counts)
		{
			result += (n * (n - 1)) / 2;
		}

		return result;
	}
}

