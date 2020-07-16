package com.data.structures.problems;

/**
 * https://leetcode.com/problems/jump-game/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class JumpGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
     *     	kinda Brute force
     *     	dynamic programing
     * 
     * @score
     * 		Runtime: 398 ms, faster than 18.96% of Java online submissions for Jump Game.
	 * 		Memory Usage: 41 MB, less than 96.47% of Java online submissions for Jump Game.
     * 
	 * @fail
	 *     1) logic mistac in invariant
	 *     2) for invariant was wrong too
	 *     
	 *     
	 * @time   O(N^2)
	 * @space  O(N)  
	 *       
	 * @param nums
	 * @return
	 */
    public boolean canJump(int[] nums) {
        
     if (nums.length == 1)
         return true;
        
     boolean dp [] = new boolean[nums.length];
     dp[nums.length - 1] = true;   
     boolean cur = false;
        
     for (int i = nums.length - 2; i >= 0; i--)
     {         
         for (int jump = 1; jump <= nums[i] && nums[i] > 0; jump++)
         {            
             if (i + jump <= nums.length - 1)
             {
                 cur |= dp[i + jump];
             }
         }
         
         dp[i] = cur;         
         cur = false;
     }
        
        return dp[0];
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * @intuition
 * 		It is an improvement over dp bottom up solution
 * 		Greedy Solution
 * 
 * @score
 * 		Runtime: 1 ms, faster than 98.93% of Java online submissions for Jump Game.
		Memory Usage: 41.2 MB, less than 87.31% of Java online submissions for Jump Game.
 * 
 * @author Nelson Costa
 *
 */
class JumpGameSolution4 {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}


/**
 * @intuition
 * 		implements dp and provides some optimizations like the furthers number to try and early stoppage
 * 
 * 
 * @score
 * 	Runtime: 234 ms, faster than 27.34% of Java online submissions for Jump Game.
	Memory Usage: 41 MB, less than 96.22% of Java online submissions for Jump Game.

 * @author Nelson Costa
 *
 */
enum Index {
    GOOD, BAD, UNKNOWN
}

class JumpGameSolution3 {
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
}