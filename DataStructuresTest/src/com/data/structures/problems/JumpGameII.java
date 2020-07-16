package com.data.structures.problems;

import java.util.Arrays;

public class JumpGameII {

	public static void main(String[] args) {

	}
	
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
    /**
     * 
     * [TIME EXCEEDED]
     * 
    @fail
        1) I read the description and interpreted that zero was not possible
        2) My guard was wrong for the jump
        3) Time exceeded
        
    @time
        O(N^2)
    @space
    	O(N)
       
    **/
    public int jump0(int[] nums) {
        //might not get to the end
        
        if (nums == null || nums.length <= 1)
            return 0;
        
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 0;
        
        for (int i = n - 2; i >= 0; i--)
        {
            if (nums[i] + i >= n - 1)
            {
                dp[i] = 1;
            }
            else
            {           
                
                int jump = 1;
                while (jump <= nums[i])
                {
                    if (dp[i + jump] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i + jump] + 1);
                    jump++;
                }
            }
        }
        
        return dp[0];
    }
    
	/*********************************
	 * SOLUTION 2
	 ********************************/
    /**
     * 
     * @intuition
     * 		My intuition was to greedly advance from the first element distance.
     * 		while traveling the distance save the max element.
     * 		when the number of elements have been exausted, I should update the max reach with the maximum found so far.
     * 		and repeat
     * steps 1 1 1 2 2
     * array[2,1,1,2,3]
     * 
     * 
     * 
     * @score
     		Runtime: 2 ms, faster than 55.82% of Java online submissions for Jump Game II.
			Memory Usage: 43.5 MB, less than 13.79% of Java online submissions for Jump Game II.
    @fail
        1) my jumps where wrong there were situations that I was not counting
        2) calculus of the jump end was wrong, I was mistakenly using the maxreach
        
        @debug
            contest
            
        @time 
        	O(N)
        @space
        	O(N)
    **/
     public int jump(int[] nums) {
         
         int maxReach = 0;
         int jump = nums[0];
         int jumpsMade = 1;
         
         if (nums != null && nums.length <=1)
             return 0;
         
         if(nums[0] >= nums.length - 1)
             return 1;
         
         for (int i = 0; i < nums.length; i++)
         {
             
             maxReach = Math.max(nums[i] + i, maxReach);

             if (jump == 0)
             {        
                 jump=maxReach - i - 1;
                 jumpsMade++;
                 
                 if (maxReach >= nums.length - 1)
                     return jumpsMade;
             }
             else
             {

                 jump--;
                
             }
         }
         return jumpsMade;
     }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * whenever you reach the end of the steps, you update the number of jumps ans update the max steps
 * 
 * @time 	O(N)
 * @space 	O(N)
 * @author Nelson Costa
 *
 */
class JumpGameIISolution1 {
	  public int jump(int[] nums) {
	    int n = nums.length;
	    if (n < 2) return 0;

	    // max position one could reach 
	    // starting from index <= i 
	    int maxPos = nums[0];
	    // max number of steps one could do
	    // inside this jump
	    int maxSteps = nums[0];
	    
	    int jumps = 1;
	    for (int i = 1; i < n; ++i) {
	      // if to reach this point 
	      // one needs one more jump
	      if (maxSteps < i) {
	        ++jumps;
	        maxSteps = maxPos;
	      }
	      maxPos = Math.max(maxPos, nums[i] + i);
	    }
	    return jumps;
	  }
	}
