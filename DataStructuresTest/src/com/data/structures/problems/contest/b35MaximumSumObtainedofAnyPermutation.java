package com.data.structures.problems.contest;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b35MaximumSumObtainedofAnyPermutation {


	/*********************************
	 * SOLUTION 1
	 ********************************/
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
	public int maxSumRangeQuery0(int[] nums, int[][] request) {

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



	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * 
	 * [UPSOLVING]
	 * 
	 * @intuition
	 * 		the intuition works on top of my previous solution logic. 
	 * 		Essentially multiply the biggest number for the biggest frequency.
	 * 
	 * 		But I use a few powerfull things here.
	 * 
	 * 		the sweepline algorithm to improve how I mark the frequency of the elements.
	 * 
	 * @time 
	 * 		O(NLogN)
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param nums
	 * @param requests
	 * @return
	 */
	public int maxSumRangeQuery(int[] nums, int[][] requests) {
		int n = nums.length;
		int [] freq = new int[n];
		int mod = 1_000_000_007;
		
		//Mark beginning and start of each interval
		int start, end;
		for (int[] r : requests)
		{
			start   = r[0];
			end     = r[1];

			if(end +1 < n)
				freq[end+1]--;            
			freq[start]++;
		}

		//Comulative sum, at the end all elements will have 
		for (int i = 1; i < freq.length; i++)
		{
			freq[i] += freq[i - 1];
		}

		//sort from beginning to end
		Arrays.sort(nums);
		Arrays.sort(freq);

		long ans = 0;
		for (int i = n - 1; i >=0; i--)
		{
			ans += freq[i]*nums[i];
		}

		return (int)(ans % mod);
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