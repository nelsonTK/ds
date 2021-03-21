package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * MEDIUM https://leetcode.com/problems/binary-trees-with-factors/
 * 
 * @author Nelson Costa
 *
 */
public class BinaryTreesWithFactorsMedium {

	/**
	 * 
	 * @intuition
	 * 		The gist is to find how many times a number occur and then sum the values in the end.
	 * 		The extra dificulty was in having all the values well configured for the first case
	 * 		and having the things write with the hashmap, I had an hard time to figured it out
	 * 
	 * 
	 * @fail
	 * 		A Lot of errors, some stupid.
	 * 		mod value was wrong
	 * 		didnt though in the case of sorting
	 * 		struggled with a way to combine the values
	 * 		
	 * 
	 * @score
	 * 		Runtime: 22 ms, faster than 87.61% of Java online submissions for BinaryTrees With Factors.
	 * 		Memory Usage: 39.2 MB, less than 55.75% of Java online submissions for Binary Trees With Factors.
	 * 
	 **/
	public int numFactoredBinaryTrees(int[] arr) {

		// numbers dont sum for themselves
		// hash to have the sum for each value
		//

		HashMap<Integer, Integer> map = new HashMap<>();
		long[] dp = new long[arr.length];
		Arrays.sort(arr);
		Arrays.fill(dp, 1);

		// add val indexes
		for (int i = 0; i < arr.length; i++)
			map.put(arr[i], i);

		int target, left;
		for (int i = 0; i < arr.length; i++) {
			target = arr[i];

			for (int j = 0; j < i; j++) {
				left = arr[j];

				// if the result of division is integer
				if (target % left == 0) {
					int right = target / left;
					if (map.containsKey(right)) {
						dp[i] = dp[i] + (dp[j] * dp[map.get(right)]);
					}
				}
			}
		}

		long ans = 0;

		for (long l : dp)
			ans += l;

		return (int) (ans % 1_000_000_007);
	}
}


/**
 * 
 * This solution achieved what I wanted to achieve which was to do all in one loop.
 * 
 * @author Nelson Costa
 *
 */
class BinaryTreesWithFactorsMediumUnofficialSolution
{
    public int numFactoredBinaryTrees(int[] A) {
        long res = 0L, mod = (long)1e9 + 7;
        Arrays.sort(A);
        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            dp.put(A[i], 1L);
            for (int j = 0; j < i; ++j)
                if (A[i] % A[j] == 0)
                    dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);
            res = (res + dp.get(A[i])) % mod;
        }
        return (int) res;
    }
}