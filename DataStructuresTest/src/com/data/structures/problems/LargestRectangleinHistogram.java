package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * @author Nelson Costa
 */
public class LargestRectangleinHistogram {

	/** 
	 * @intuition
	 * 	Very tough problem
	 * 	
	 * 
	 * @failed 
	 * 	1)because I didn't had a strictly increasing stack, I was not handling the equal values well; failed I was not thinking in the empty array 
	 * 	2) failed again because I was not calculating right and left side to multiply, only right side..
	 * 	3) I was also not calculating the difference to the left in the first while loop
	 * 	4) failed again my difference to left was conditional but should have been always applied
	 * 	5) failed again because I was ancoring the biggest which caused me to fail when the response was relying on it
	 * 	6) failed I forgot I cannot just update previous all the time, to update the previous i has to be bigger than the current previous
	 * 
	 * @score
	 * 		Runtime: 7 ms, faster than 73.01% of Java online submissions for Largest Rectangle in Histogram.
	 * 		Memory Usage: 40.1 MB, less than 80.05% of Java online submissions for Largest Rectangle in Histogram.
	 * @intuition
	 * 		Beast Question
	 *
	 **/
	public int largestRectangleArea(int[] heights) {

		//the gist lays in finding two elements smaller than a bar
		//to achieve this we should maintain a strictly increasing stack
		//when we find a element smaller than the current we pop the peek of the stack and calculate its max area
		//we add a index -1 to the stack with the min val, so we can calculate for instance the first value, or a value in the middle that is the smallest.

		//1) traverse every number
		//if current number bigger than the peek of the stack add to the stack
		//else we pop the top of the stack and multiply our current bar value by the difference of the indexes with the bar at the top of stack andthen we put our entry in the stack

		//2) when we finish the traversal we will remove the remaining elements applying the same logic
		//while the current element is not index -1,
		//pop and multiply by index differences
		//verify max

		//3) return max

		if (heights == null || heights.length == 0)
			return 0;


		Stack<int[]> stack = new Stack<>();
		stack.push(new int[]{-1,-1});
		int max = 0;
		int [] cur;
		int multiply;
		for(int i = 0; i < heights.length; i++)
		{
			if(heights[i] >= stack.peek()[0])
			{
				stack.push(new int[]{heights[i], i});
			}
			else
			{
				while(stack.peek()[0] > heights[i])
				{
					cur = stack.pop();
					multiply = i - cur[1] + (cur[1] - stack.peek()[1] - 1);
					max = Math.max(cur[0] * multiply, max);
				}

				stack.push(new int[]{heights[i], i});
			}
		}

		int [] previous = stack.peek();
		while (stack.size() != 1)
		{
			cur = stack.pop();
			int diffRight = previous[1] - cur[1] + 1;
			int diffLeft = cur[1] - stack.peek()[1] - 1;
			multiply = diffRight + diffLeft;
			max = Math.max(cur[0] * multiply, max);
			previous = cur[0]> previous[0] ? cur : previous;
		}


		return max;
	}
}
