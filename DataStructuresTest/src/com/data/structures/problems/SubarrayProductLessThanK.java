package com.data.structures.problems;

/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SubarrayProductLessThanK {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		this is a sliding window problem with two pointers.
	 * 		the key aspect to pass this problem is to use the R - L + 1 math to calculate the number of combinations added with a new element.
	 * 		the first time I tried this exercise I tried it with brutforce because I didn't found a way of avoiding that calculation.
	 * 		really cool exercise
	 * 	
	 * 
	 * @score
	 * 		Runtime: 9 ms, faster than 22.61% of Java online submissions for Subarray Product Less Than K.
	 * 		Memory Usage: 105.3 MB, less than 6.29% of Java online submissions for Subarray Product Less Than K.
	 * 
	 * @fail
	 * 		1) used an if instead of a while loop
	 * 		2) Got TLE with first attempt where I tried a bruteforce solution
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int numSubarrayProductLessThanK(int[] nums, int k) {
        //edge cases
        //size 1 ?
        
        
        //when first element is greater 
        //100, 2, 200, 1,4
        //1,2,3,4
        
        if (nums.length == 1)
            return nums[0] < k? nums[0] : 0;
        
        int L = 0, R = 0, product = 1, count = 0;
        
        
        
        while (R < nums.length)
        {
            // add product
            // if start < end and product is bigger, remove last
            // if bigger dont increase number of arrays, else increase
            // increase end
            
            product *= nums[R];
            
            while (product >= k && L < R)
            {
                product /= nums[L];
                L++;
            }
            
            if (product < k)
            {
                count += R - L + 1;
            }
            
            R++;
        }
        
        return count;
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Solution 1
 * 
 * 		same algorithm than my solution but with less code
 * 		cleaver while condition
 * 		and cleaver increase of the answer (works because left can be greater than than right)
 * 
 * 
 * @author Nelson Costa
 *
 */
class SubarrayProductLessThanKSolution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }
}
