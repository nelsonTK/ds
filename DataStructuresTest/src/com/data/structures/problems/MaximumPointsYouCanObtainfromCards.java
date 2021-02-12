package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/submissions/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MaximumPointsYouCanObtainfromCards {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		This is a Sliding window problem, we do a window of size n - k 
	 * 		and that we try all the windows and see which one yields the best results!
	 *  
	 * @score
	 * 		Runtime: 5 ms, faster than 7.52% of Java online submissions for Maximum Points You Can Obtain from Cards.
	 * 		Memory Usage: 48.3 MB, less than 61.67% of Java online submissions for Maximum Points You Can Obtain from Cards.
	 * 
	 * 	WITHOUT LAMBDA
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Points You Can Obtain from Cards.
	 *		Memory Usage: 48 MB, less than 80.39% of Java online submissions for Maximum Points You Can Obtain from Cards.
	 * 
	 * @fail
	 * 		1) forgot to add the validation of the window size...
	 * 
	 * @alternatives
	 * 		Alternatives could be to use a prefix sum
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 */
	public int maxScore(int[] cards, int k) {

		if (k == cards.length)
			return Arrays.stream(cards).sum();

		int sum = Arrays.stream(cards).sum(); //Total sum
		int wsize = cards.length - k; //window size
		int ans = 0;        
		int L = 0, R = 0, curSum = 0;

		while (R < cards.length)
		{
			curSum += cards[R];

			//if window above what it should be we move left pointer (i.e. we reduce the window) 
			while (R - L + 1 > wsize)
			{
				curSum -= cards[L];
				L++;
			}

			if (R - L + 1  == wsize)
				//I could have use a min, and not subtract. and do the subtraction only on the end.
				ans = Math.max(ans, sum - curSum);
			R++;
		}

		return ans;
	}
}

/**
 * This is a good solution the user uses prefix sum to get the correct value but it relies on the same logic than my solution
 * @author Nelson Costa
 *
 */
class MaximumPointsYouCanObtainfromCardsSolution {
    public int maxScore(int[] cardPoints, int k) {
        // Naive approach: O(2^k)
        // Alternative approach: O(n) to get accumulative sum and O(n - k) to find min.
        if (cardPoints == null || cardPoints.length == 0 || k == 0) {
            return 0;
        }
        
        int n = cardPoints.length;
        // return maxScore(cardPoints, 0, cardPoints.length - 1, k);
        int[] accSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            accSum[i + 1] = accSum[i] + cardPoints[i];
        }
        
        int lenRemaining = n - k;
        int minRemaining = Integer.MAX_VALUE;
        for (int i = lenRemaining; i <= n; i++) {
            minRemaining = Math.min(minRemaining, accSum[i] - accSum[i - lenRemaining]);
        }
        
        return accSum[n] - minRemaining;
    }
}
