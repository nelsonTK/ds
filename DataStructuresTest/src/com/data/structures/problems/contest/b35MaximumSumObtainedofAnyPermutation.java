package com.data.structures.problems.contest;

import java.util.Arrays;

public class b35MaximumSumObtainedofAnyPermutation {

	/**
	 * [TIME LIMIT EXCEEDED]
	 * 
	 * @intuition
	 * 		The main intuition is to multiply the biggest numbers by the indexes that occurs most time.
	 * 		So it was a greedy solution
	 * 
	 * @fail
	 * 		The modulo trick got me
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N^2)
	 * 
	 * 
	 * @param nums
	 * @param request
	 * @return
	 */
	public int maxSumRangeQuery(int[] nums, int[][] request) {

		int size = (int)1e5+7;
		Integer [] num = new Integer[size];
		Integer [] freq = new Integer[size];
		Arrays.fill(num,0);
		Arrays.fill(freq,0);
		long total = 0;

		int idx = 0;
		for (int i : nums)
		{    
			num[idx++]=i;
		}

		Arrays.sort(request, (a, b) -> Integer.compare(a[0], b[0]));

		for (int i = 0; i < request.length; i++)
		{
			for (int j = request[i][0]; j <=request[i][1]; j++)
			{
				freq[j]++;
			}
		}
		Arrays.sort(num, (a, b) -> Integer.compare(b,a));
		Arrays.sort(freq, (a, b) -> Integer.compare(b,a));

		int index = 0;
		while(index < nums.length)
		{
			total += freq[index] * num[index];
			index++;

		}

		return (int) (total % (1_000_000_000 + 7));
	}
}

/**
 * The goal is add 1 to the start index, and subtract -1 to the element next to the end.
 * If we do prefix sum than all the elements between 1 and -1 will get 1.
 * 
 * @media
 * 		https://imgur.com/kLBojZW
 * 
 * @author Nelson Costa
 *
 */
class MaximumSumObtainedofAnyPermutationSolution1{
	public int maxSumRangeQuery(int[] A, int[][] req) {
		int res = 0, mod = (int)1e9 + 7, n = A.length;
		int[] count = new int[n];
		for (int[] r: req) {
			count[r[0]] += 1;
			if (r[1] + 1 < n)
				count[r[1] + 1] -= 1;
		}
		for (int i = 1; i < n; ++i)
			count[i] += count[i - 1];
		Arrays.sort(A);
		Arrays.sort(count);
		for (int i = 0; i < n; ++i)
			res = (res + A[i] * count[i]) % mod;
		return res;
	}
}