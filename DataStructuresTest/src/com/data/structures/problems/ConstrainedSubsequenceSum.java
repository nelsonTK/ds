package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * https://leetcode.com/problems/constrained-subsequence-sum/
 * HARD
 * @author Nelson Costa
 *
 */
public class ConstrainedSubsequenceSum {

	/**
	 * @intuition
	 * 		Tough problem.
	 * 		I used a decreasing monotonic queue logic.
	 * 		I have a video about this exercise in Mega.co.nz.
	 * 		
	 * 		But the queue has at the front the element we are comparing with and testing the k against.
	 * 		and in the last element of the queue we have the smallest element. 
	 * 		when we find and element bigger then the last element in the queue we delete it and add another one.
	 * 		in the queue we add the previous element (in front of the queue with current)
	 * 		there are edge cases to this. you should see your video to understand better.
	 * 
	 * 	    array [10, 3  , 2]
	 * 		front		    back
	 * 		queue [10, 13 , 12]
	 * 			       \/
	 * 			k distance <= 2 to the first element of the queue
	 * 
	 * 		if the distance increases beyond k, we remove the first element and the next greater represents the next best jump
	 * 
	 * @score
	 * 		Runtime: 15 ms, faster than 85.19% of Java online submissions for Constrained Subsequence Sum.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(K)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int constrainedSubsetSum(int[] nums, int k) {
		if (nums.length == 1)
			return nums[0];

		Deque<Pair> q = new ArrayDeque<>();
		int max = Integer.MIN_VALUE, curr;
		q.addLast(new Pair(nums[0], 0));
		max = nums[0];

		for (int i = 1; i < nums.length; i++)
		{

			//WARNING: current can be the start of a new big element

			//if current index  - first > k, remove first

			//calculate current
			//get current + first, or just the current

			//while not empty q & current > peekLast, Remove Last
			//
			//add in the end

			if (i - q.peek().index > k)
			{
				q.removeFirst();
			}
			
			//get current
			curr = (!q.isEmpty()) ? nums[i] + q.peek().val : nums[i];
			
			//if the current plus the previous is smaller than starting a new array from that element we "start a new subsequence" by resetting
			curr = (!q.isEmpty() && curr < nums[i]) ? nums[i] : curr; 
			max = Math.max(max, curr);

			while (!q.isEmpty() && curr >= q.peekLast().val)
			{
				q.removeLast();
			}

			q.addLast(new Pair(curr, i));
		}

		return max;
	}

	class Pair{
		int val;
		int index;

		public Pair(int v, int i)
		{
			val = v;
			index = i;
		}
	}
}
