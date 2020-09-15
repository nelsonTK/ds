package com.data.structures.problems;

/**
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindtheSmallestDivisorGivenaThreshold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition for this problem is to find a value between a minimum and a maximum. 
	 * 		And try out the dividing operation in all the elements to see if the value is valid.
	 * 		
	 * 		for ceiling I used the trick I learned in koko problem. ((p-1)/k) + 1 = math.ceil(num)
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 99.23% of Java online submissions for Find the Smallest Divisor Given a Threshold.
			Memory Usage: 42.1 MB, less than 48.43% of Java online submissions for Find the Smallest Divisor Given a Threshold.
	 * 
	 * @time
	 * 		O(N*Log(W))
	 * 
	 * @space 
	 * 		O(1)
	 * 
	 * @param nums
	 * @param threshold
	 * @return
	 */
    public int smallestDivisor(int[] nums, int threshold) {
        
        int l = 1, r = 1_000_000, mid;
        
        while (l < r)
        {
            mid = l + (r - l)/2;
            
            if (isValid(nums, mid, threshold))
            {
                r = mid;
            }
            else
            {
                l = mid + 1;
            }
        }
        
        return r;
    }
    
    
    private boolean isValid(int [] nums, int mid, int t)
    {
        int total = 0;
        for (int i : nums)
        {
            total += (i - 1)/ mid + 1;
        }
        
        return total <= t;
    }
}
