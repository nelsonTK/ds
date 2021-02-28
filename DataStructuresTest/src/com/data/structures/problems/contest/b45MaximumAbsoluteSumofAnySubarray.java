package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b45MaximumAbsoluteSumofAnySubarray {


	/*
	 * way too complex comparing with other solutions... but well.. It worked
	 * 
	 * it could be simplified to finding the highest and lowest point of a comulative sum
	 * 
	 * 
	 */
    public int maxAbsoluteSum(int[] nums) {
        
        
        int [] pfx = new int[nums.length+1]; 
        
        //calc Comulative sum
        for (int i = 1; i < pfx.length; i++)
        {
            pfx[i] = nums[i-1] + pfx[i - 1];
        }
        
        int [][] maxSumAheadIdx = new int[nums.length + 1][2];
        int curMax = Integer.MIN_VALUE;
        int curMin = Integer.MAX_VALUE;
        int curMaxIdx = -1;
        int curMinIdx = -1;
        //calc Max comulative sum ahead
        for (int i = pfx.length - 1; i >= 0; i--)
        {
            if(pfx[i] > curMax)
            {
                curMax = pfx[i];
                curMaxIdx = i;
            }
            if(pfx[i] < curMin)
            {
                curMin = pfx[i];
                curMinIdx = i;
            }
            
            maxSumAheadIdx[i]= new int []{ curMaxIdx, curMinIdx };
        }
        
        //search for the max subarray, between, current element alone, and the sum to max
        curMax = Integer.MIN_VALUE;
        for (int i = 0; i < pfx.length; i++)
        {
            curMax = Math.max(curMax, Math.abs(pfx[maxSumAheadIdx[i][0] ] - pfx[i ]));
            curMax = Math.max(curMax, Math.abs(pfx[maxSumAheadIdx[i][1] ] - pfx[i ]));
            if (i > 0)
            curMax = Math.max(curMax, Math.abs(nums[i -1]));

        }
        
        return curMax;
    }
}



class b45MaximumAbsoluteSumofAnySubarraySolution {
    public int maxAbsoluteSum(int[] nums) {
        int min = 0;
        int max = 0;
        
        int sum=0;
        for(int num:nums){
            sum+=num;
            min = Math.min(sum, min);
            max = Math.max(sum, max);
        }
        
        return max-min;
    }
}