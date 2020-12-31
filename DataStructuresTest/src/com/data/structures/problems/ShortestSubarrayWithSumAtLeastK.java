package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 * HARD
 * @author Nelson Costa
 *
 */
public class ShortestSubarrayWithSumAtLeastK {


	/**
	 * @intuition
	 * 		Very tough problem.
	 * 		concepts, prefix sum and monotonic queue.
	 * 		concept have monotonic increasing queue
	 * 		check the diference between current and first point of the queue.
	 * 		if you dont master prefix sum it will be a nightmare to understand this.
	 * 		
	 * 
	 * 
	 * @score
	 * 		Runtime: 34 ms, faster than 16.08% of Java online submissions for Shortest Subarray with Sum at Least K.
	 *		Memory Usage: 91.6 MB, less than 5.51% of Java online submissions for Shortest Subarray with Sum at Least K.
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
    public int shortestSubarray(int[] nums, int k) {
        
        int [] sum = new int[nums.length + 1];
        int min = Integer.MAX_VALUE;
        
        for (int i = 1; i < sum.length; i++)
        {
            sum[i] = nums[i - 1] + sum[i - 1];
        }
        
        int cur = 1;
        //save the indexes of the numerous commulative points
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        
        while (cur < sum.length)
        {
        	//exclude elements from the queue that cannot be the best possibility, is impossible, just put it in the paper and see.
            while (!queue.isEmpty() && sum[cur] <= sum[queue.getLast()])
            {
                queue.removeLast();
            }
            
            //get the difference between the first element in the queue and the current element
            while (!queue.isEmpty() && sum[cur] - sum[queue.getFirst()] >= k)
            {
                min = Math.min(cur - queue.getFirst(), min);
                queue.removeFirst();
            }
            
            queue.addLast(cur);
            cur++;
        }
        
        return min == Integer.MAX_VALUE? -1 : min;
        
    }
}
