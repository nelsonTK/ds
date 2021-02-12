package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumSwapstoGroupAll1sTogether {

	/**
	 * 
	 * @intuition
	 * 		The intuition is to form a sliding window with the size of the number of one's
	 * 		And then slide through the array and number of zeros in that sliding window are the number of zeros.
	 * 
	 * 		my implementation starts by counting the number of ones and then positioned the window
	 * 		and then slide.
	 * 
	 * @score
	 * 		Runtime: 7 ms, faster than 50.34% of Java online submissions for Minimum Swaps to Group All 1's Together.
	 * 		Memory Usage: 49.3 MB, less than 65.24% of Java online submissions for Minimum Swaps to Group All 1's Together.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 	
	 **/
	public int minSwaps0(int[] data) {

		//count the number of ones
		//for a window of size #numberOfOnes count the zeros


		int ones = 0;
		int zeros = 0;
		int L = 0, R = 0;
		int min = Integer.MAX_VALUE;

		//count ones
		for (int i = 0; i < data.length; i++)
		{
			ones = data[i] == 1? ones + 1 : ones;
		}


		while (R < ones)
		{
			if (data[R] == 0)
				zeros++;

			R++;
		}

		if (ones == 0)
			return 0;

		min = zeros;


		while (R < data.length)
		{    
			if (data[R] == 0)
				zeros++;

			if (data[L] == 0)
				zeros--;

			min=Math.min(zeros, min);

			R++;L++;
		}

		return min;
	}



	/**
	 * @intuition
	 * 		is a sliding window problem where we count the ones and see how many zeros are in the window
	 * 
	 * 
	 * @fail
	 * 		forgot about the all zeros scenario
	 * 
	 * 
	 * @score
	 * 		Runtime: 13 ms, faster than 7.90% of Java online submissions for Minimum Swaps to Group All 1's Together.
	 * 		Memory Usage: 49.3 MB, less than 65.24% of Java online submissions for Minimum Swaps to Group All 1's Together.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * 
	 * @space
	 * 		O(1)
	 * 
	 **/
	public int minSwaps(int[] data) {

		int ones = (int) Arrays.stream(data).filter(i -> i == 1).count();
		int zeros = 0, L = 0, min = Integer.MAX_VALUE;

		if (ones == 0)
			return 0;

		for (int R = 0; R < data.length; R++)
		{
			if (data[R] == 0)
				zeros++;

			if (R - L + 1 >= ones)
			{ 
				min = Math.min(min, zeros);

				if (data[L] == 0)
					zeros--;

				L++;
			}
		}

		return min;

	}
}

/**
 * Great implementation following sliding window pattern
 * 
 * you move the windows according to the window size is bellow or greater than the number of ones.
 * @author Nelson Costa
 *
 */
class MinimumSwapstoGroupAll1sTogetherSolution1{
	public int minSwaps(int[] data) {
		int ones = Arrays.stream(data).sum();
		int cnt_one = 0, max_one = 0;
		int left = 0, right = 0;

		while (right < data.length) {
			// updating the number of 1's by adding the new element
			cnt_one += data[right++];
			// maintain the length of the window to ones
			if (right - left > ones) {
				// updating the number of 1's by removing the oldest element
				cnt_one -= data[left++];
			}
			// record the maximum number of 1's in the window
			max_one = Math.max(max_one, cnt_one);
		}
		return ones - max_one;
	}
}