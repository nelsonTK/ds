package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.data.structures.performance.BenchMark;
import com.data.structures.performance.BenchMarkInput;
import com.data.structures.performance.BenchMarkInputFactory;
import com.data.structures.performance.FunctionInputIntArray;
import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * 
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestIncreasingSubsequence extends LeetCodeExercise
{
	static LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
	static LongestIncreasingSubsequenceSolution1 l1 = new LongestIncreasingSubsequenceSolution1();
	public static void main(String[] args) {
		 
		l.benchMark();
	}
	 
	@Override
	public void benchMark() {
		BenchMark b = new BenchMark();
		BenchMarkInputFactory<FunctionInputIntArray> factory = new BenchMarkInputFactory<>();
		List<BenchMarkInput<FunctionInputIntArray>> benchmarkList = new ArrayList<>();

		benchmarkList.add(factory.create(l::lengthOfLIS0, "S1-Mysol-lengthOfLIS0"));
		benchmarkList.add(factory.create(l::lengthOfLIS, "S2-Mysol-lengthOfLIS"));
		benchmarkList.add(factory.create(l1::lengthOfLIS, "S1-Leetc-lengthOfLIS"));
		b.benchMarkFunctionInputIntArray(benchmarkList, Integer.MIN_VALUE, Integer.MAX_VALUE, 260, 2);
		
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * [TIME LIMITE EXCEEDED]
	 * 
	 * @intuition
	 * 		backtracking solution with no memoization
	 * 		The biggest problem with this solution is that I can have the stack growing like crazy.
	 * 
	 * @fail
	 * 		1) Time limit exceeded
	 * 
	 * @observations 
	 * 		1) I think I can sort and then find the maximum for the minimum
	 * 
	 * @time (N^2)
	 * 
	 * @space O(N)
	 * 
	 **/
    public int lengthOfLIS0(int[] nums) {
        
        if (nums == null || nums.length == 0)
            return 0;
        
        int max = 0;
        for (int i = 0; i < nums.length; i++)
        {
            max = Math.max(solve0(i, 1, nums), max);
        }
        
        return max;
    }
    
    
    private int solve0(int prevIndex, int count, int[]nums){
        
        int max = count;
        for (int i = prevIndex + 1; i < nums.length; i++)
        {
            if (nums[i] > nums[prevIndex])
                max = Math.max(solve0(i, count+1, nums), max);
        }
        
        return max;
    }
    

	 /*********************************
	 * SOLUTION 2
	 ********************************/ 
    /**
     * @intuition
     * 		DP solution where you compare the current value with the previous ones.
     * 		
     * 		you fill the DP value with 1's and from there you do:
     * 
     * 		dp[i] = max(dp[j] + 1, dp[i])	
     * 		j equals to a previous element
     * 		i equals to current element
     * 
     * @score
     * 		Runtime: 11 ms, faster than 62.48% of Java online submissions for Longest Increasing Subsequence.
	 *		Memory Usage: 37.3 MB, less than 82.41% of Java online submissions for Longest Increasing Subsequence.
	 *
	 *
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        
        if(nums == null || nums.length == 0)
            return 0;
        
       
        //fill dp with 1's
        int [] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        
        
        
        //start at position 0 + 1
        
        //use another for to iterate from i - 1 to 0.
        //if the element is smaller, take that dp value and increase by one, do this to every value
        
        for (int i = 1; i < nums.length; i++)
        {
            for (int j = i - 1; j >= 0; j--)
            {
                if (nums[i] > nums[j])
                {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < dp.length; i++)
        {
            ans = Math.max(dp[i], ans);
        }
        
        
        return ans;
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * DP and binary search
 * 
 * if current > last we add to the dp array.
 * 
 * if the current <= last,  replace the element with the new one.
 * 
 * The size of the array is the answer. we could use a arraylist, 
 * 
 * or use a parent array that has for each number its parent index and then in the end we search for the sequence
 * 
 * The search for the place of the number will be made with binary search.
 * 
 * 
 * The solution below do not do exactly this but is a variation of what I've talked above.
 * 
 * @author Nelson Costa
 *
 */
class LongestIncreasingSubsequenceSolution4 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}

/**
 * Recursive Solution
 * @author Nelson Costa
 *
 */
class LongestIncreasingSubsequenceSolution1{

    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }
}

/**
 * Dynamic Programming Solution, concept similar to mine
 * @author Nelson Costa
 *
 */
class LongestIncreasingSubsequenceSolution3 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
