package com.data.structures.problems;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CombinationSumIV {

	/**
	 * @intuition
	 * 		This is another problem with a pattern similar to knapsack (unbounded)
	 * 		Number of ways style problem.
	 * 		We will calculate the number of ways to get to all the numbers until the target, with all the elements used.
	 * 
	 * 		We will calculate for 1 to n with 1 element of the possibilities/array, than with 2 elements, then with 3, than calculate 2 with the same process, till n.
	 * 
	 * 		subproblem: 
	 * 			[cur target] = Sum([cur target] - [candidates]) number of ways
	 * 		
	 * 		base case:
	 * 			1 element for target values from 1 to Target
	 * 
	 * 		example:
	 * 			target 5
	 * 			elements : 2,3,4,5
	 * 
	 * 			result of first iteration:
	 * 				2 (element in calculation)
	 * 				1 2 3 4 5 (target calculations)
	 *              0 1 0 1 0 (number of ways of getting to  target with element 2)
	 *  				
	 *  		Why
	 *  			2 = 1 because 2     = 2;
	 *  			4 = 1 because 2 + 2 = 4;
	 * 
	 * 		And we will do this adding to a new number in each iteration		
	 * 
	 * @score
	 * 		Runtime: 9 ms, faster than 7.49% of Java online submissions for Combination Sum IV.
	 * 		Memory Usage: 37.2 MB, less than 25.00% of Java online submissions for Combination Sum IV.
	 * 
	 * @time
	 * 		O(N*M*M)
	 * 		N - array size
	 * 		M - target size
	 * 		K - target
	 * 
	 * @space
	 * 
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
    public int combinationSum4(int[] nums, int target) {
        
        int [][] dp = new int[nums.length + 1][target + 1];
        
        //base case
        for (int i = 1; i < dp.length; i++)
        {
                dp[i][0] = 1;
        }
        
        for (int number = 1; number < dp.length; number++)
        {
            for(int t = 1; t < dp[number].length; t++)
            {
                for (int used = 0; used < number; used++)
                {
                    if(t - nums[used] >= 0)
                        dp[number][t] += dp[number][t - nums[used]];
                }
            }
        }
        
        return dp[dp.length - 1][dp[0].length - 1];
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * One Array Solution
 * @author Nelson Costa
 *
 */
class CombinationSumIVSolution2 {

    public int combinationSum4(int[] nums, int target) {
        // minor optimization
        // Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int combSum = 1; combSum < target + 1; ++combSum) {
            for (int num : nums) {
                if (combSum - num >= 0)
                    dp[combSum] += dp[combSum - num];
                // minor optimizaton, early stopping
                // else
                //     break;
            }
        }
        return dp[target];
    }
}
