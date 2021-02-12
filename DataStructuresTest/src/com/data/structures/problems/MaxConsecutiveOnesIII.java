package com.data.structures.problems;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * @author Nelson Costa
 *
 */
public class MaxConsecutiveOnesIII {


	/**
	 * @intuition
	 * 		The intuition is to evaluate the maximum everfound and compare with the window size.
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 19.59% of Java online submissions for Max Consecutive Ones III.
	 * 		Memory Usage: 40.2 MB, less than 71.04% of Java online submissions for Max Consecutive Ones III.
	 * 
	 * 
	 * @fail
	 * 		Fail because I didn't read the question properlly, I missed the fact that we wanted the maximum number of ones
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param a
	 * @param k
	 * @return
	 */
	public int longestOnes(int[] a, int k) {

		int L = 0, R = 0, max = 0, len = 0, n = a.length, res = 0;

		int [] counter = new int[2]; //counter for 0's and 1's

		//increase the counter and valuate max, but I could only count one's
		//while the size of the ""sliding window - max" bigger than k, reduce the window
		//update max size of the window and increase R

		while (R < n)
		{
			counter[a[R]]++;
			if(a[R] == 1)
				max = Math.max(counter[a[R]], max);


			while (R - L + 1 - max > k)
			{
				counter[a[L]]--;
				L++;
			}

			res = Math.max(R-L+1, res);
			R++;
		}

		return res;
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/
class MaxConsecutiveOnesIIISolution1 {
	public int longestOnes(int[] A, int K) {
		int left = 0, right;
		for (right = 0; right < A.length; right++) {
			// If we included a zero in the window we reduce the value of K.
			// Since K is the maximum zeros allowed in a window.
			if (A[right] == 0) K--;
			// A negative K denotes we have consumed all allowed flips and window has
			// more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
			if (K < 0) {
				// If the left element to be thrown out is zero we increase K.
				if (A[left] == 0) K++;
				left++;
			}
		}
		return right - left;
	}
}
