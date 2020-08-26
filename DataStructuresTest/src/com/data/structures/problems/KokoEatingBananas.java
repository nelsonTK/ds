package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/koko-eating-bananas/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class KokoEatingBananas extends LeetCodeExercise{
	
	static KokoEatingBananas k = new KokoEatingBananas();
	public static void main(String[] args) {
		int [] piles = stringToArray("[3,6,7,11]");
		int h = 8;
		k.minEatingSpeed(piles, h);
	}

	/**
	 * @intuition
	 * 		The intuition is use binary tree to find the minimal k,
	 * 		and at each iteration we for each our array. this is better that do it linearly
	 * 		
	 * 		The biggest challenge in this question was to understand exactly what they were asking
	 * 
	 * @score
	 *		Runtime: 41 ms, faster than 10.71% of Java online submissions for Koko Eating Bananas.
	 *		Memory Usage: 40.1 MB, less than 88.25% of Java online submissions for Koko Eating Bananas.
	 *
	 * @time
	 * 		O(N*Log(M))
	 * 		M equals to max value possible
	 *
	 * @param piles
	 * @param h
	 * @return
	 */
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 1_000_000_000;
        int mid ;
        while (l < r)
        {
            mid = l + (r - l)/2;
            
            if (canEat(piles, mid, h))
            {
                r = mid;
            }
            else
            {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private boolean canEat(int [] piles, int k, int h)
    {
        int timeTook = 0;
        
        for (int p : piles)
        {
            p -= k;
            timeTook++;
            if (p > 0)
            {
                timeTook += (int) Math.ceil(((double)p) / k);
            }
        }
        
        return timeTook <= h;
    }
}

/**
 * This solution uses the same concept than mine, but is written much more efficiently
 * 
 * (p-1) / K + 1 means ceiling
 * @author Nelson Costa
 *
 */
class KokoEatingBananasSolution1 {
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p: piles)
            time += (p-1) / K + 1;
        return time <= H;
    }
}
