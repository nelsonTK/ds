package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SubarraySumEqualsK extends LeetCodeExercise {

	static SubarraySumEqualsK s  = new SubarraySumEqualsK();

	public static void main(String[] args) {
		int k = 1;

		int [] a = stringToArray("[1,-1,2]");
		k = 100;
		//		a = stringToArray("[28,54,7,-70,22,65,-6]");
		a = stringToArray("[1,1,1]");
		k = 2;

		printArray(a);
		System.out.println(s.subarraySum(a, k));




	}

	/**
	 * @intuition
	 * 		Prefix sum problem I discover this kind of problemms in a w188 contest
	 * 
	 * @score
	 		Runtime: 366 ms, faster than 5.01% of Java online submissions for Subarray Sum Equals K.
			Memory Usage: 42 MB, less than 5.43% of Java online submissions for Subarray Sum Equals K.
	 * 
	 * @fail
	 * 		1) forgot to update curPrefix
	 * 		2) didn't think in negative numbers
	 * 		2.1) error I was cutting the iteration too early,
	 * 			as I was not counting negative numbers its not clear that finding a bigger number is definitive
	 * 
	 * @optimizations
	 * 		I could use a variable instead of an array for the prefix
	 * 
	 * @debug 
	 * 		yes
	 * 
	 * @time	O (N^2)
	 * @space	O (N)
	 * 
	 * @param a
	 * @param k
	 * @return
	 */
	public int subarraySum0(int[] a, int k) {

		if (a == null || a.length < 1)
			return 0;

		int ans = 0, curPrefix = 0, j = 0; 
		int[] prefix = new int[a.length];

		prefix[0] = a[0];

		for (int i = 1; i < a.length; i++) {
			prefix[i] = a[i] + prefix[i - 1];
		}

		for (int i = 0; i < a.length; i++) {
			j = i;

			while (j < a.length)
			{
				if (prefix[j] - curPrefix == k)
				{
					ans++;
				}
				j++;
			}

			curPrefix = prefix[i];
		}    	

		return ans;
	}


	/**
	 * @intuition
	 * 		Nothing fancy applied
	 * 
	 * @score
			Runtime: 363 ms, faster than 5.01% of Java online submissions for Subarray Sum Equals K.
			Memory Usage: 40.8 MB, less than 13.04% of Java online submissions for Subarray Sum Equals K.
	 * 
	 * 
	 * @fail
	 * 		1) I was forgetting to reset comulative sum
	 * 
	 * @time	N^2
	 * 
	 * @space	O(1)
	 * 
	 * @param a
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] a, int k) {

		if (a == null || a.length < 1)
			return 0;

		int ans = 0, j = 0; 

		int curSum = 0;

		for (int i = 0; i < a.length; i++) {
			j = i;
			curSum = 0;
			while (j < a.length)
			{
				curSum += a[j];

				if (curSum == k)
				{
					ans++;
				}
				j++;
			}
		}    	

		return ans;
	}

}

/**
 * Solution with HashMap
 * 
 * @intuition
 * 		the difference between two indexes of comulative sum indicates 
 * 		there is a a sum equals to the difference between the two indices (start exclusive, end inclusive)
 * 			
 * 		if the difference between two contiguous comulative sums are the same it means the difference in between is zero.
 * 		if we extend this, the diference between comulative i and j is equal to k.
 * 		
 * 		i - j = k => i - k = j
 * 
 * 		The author adds zero (0, 1) to the hashmap so that the algorithm can work seemenglessly for zero too
 * 		the case could be 
 * 
 * 		K = 7.
 * 		A = [7]
 * 		HM = [(0,1)]
 *		A[0] - K = 0 => zero is in the hashmap, so we increase the result. 
 *		it means that the comulative sum of that element from the begining is 7. 
 *		not the easiest or most obvious way of solving this
 * 
 * 		the goal is then see if we have an element in the array that we can subtract and get the target value (e.g. 7)
 * 		also when we are creating the hashmap we count the number of entries of comulative sum.
 * 		that way if we have two elements in the same comulative sum we don't need to go to those indexes twice.
 * 
 * 
 * @time O(N)
 * @space O(N)
 * @author Nelson Costa
 *
 */
class SubarraySumEqualsKSolution4 {
	public int subarraySum(int[] nums, int k) {
		int count = 0, sum = 0;
		HashMap < Integer, Integer > map = new HashMap < > ();
		map.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k))
				count += map.get(sum - k);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
}