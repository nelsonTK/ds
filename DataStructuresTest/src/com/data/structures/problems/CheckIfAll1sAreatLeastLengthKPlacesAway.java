package com.data.structures.problems;

public class CheckIfAll1sAreatLeastLengthKPlacesAway {


	/**
	 * @intuition
	 * 		I used sliding window and two pointers to find out the invalid window size
	 * 		the essence is to find the first one and set the left pointer to that 1, and advance the right pointer.
	 * 		from there the goal is to find the next one.
	 * 
	 * @alternative
	 * 		Just to count ones.
	 * 
	 * @fail
	 * 		I failed because I forgot to break in the first loop
	 * @param nums
	 * @param k
	 * @return
	 */
    public boolean kLengthApart(int[] nums, int k) {
        
        //edgecases, len 0, 
        
        int L = -1, R = 0;
        
        if (k == 0)
            return true;
        
        //find the first one
        for (int i = 0; i < nums.length; i++)
        {
           if(nums[i]==1)
           {
               L = i;
                break;
           }
        }
        
        //if no one is found return true;
        if (L == -1)
            return true;
        
        R = L + 1;
        
        while (R < nums.length)
        {
            if (nums[R] == 1)
            {
                if (R - L - 1 < k)
                    return false;
                else
                    L = R;
            }
            
            R++;
        }
        
        
        return true;
        
    }
}

class CheckIfAll1sAreatLeastLengthKPlacesAwaySolution {
    public boolean kLengthApart(int[] nums, int k) {
        // initialize the counter of zeros to k
        // to pass the first 1 in nums
        int count = k;
        
        for (int num : nums) {
            // if the current integer is 1
            if (num == 1) {
                // check that number of zeros in-between 1s
                // is greater than or equal to k
                if (count < k) {
                    return false;    
                }
                // reinitialize counter
                count = 0;
                
            // if the current integer is 0
            } else {
                // increase the counter
                ++count;    
            } 
        }        
        return true;
    }
}
