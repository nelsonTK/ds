package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/validate-stack-sequences/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ValidateStackSequences {

	/**
	 * @intuition
	 * 		Is one of those problems that are hard to proof, and I went for a harder implementation
	 * 		Despite I've found an easier one and is actually the optimal one
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 95.06% of Java online submissions for Validate Stack Sequences.
	 * 		Memory Usage: 38.7 MB, less than 57.62% of Java online submissions for Validate Stack Sequences.
	 * 
	 * 
	 * @time
	 * 		O(N)
	 *
	 * @space
	 * 		O(N)
	 * 
	 * 
	 **/
	public boolean validateStackSequences(int[] pushed, int[] popped) {

		Stack<Integer> stack = new Stack<>();
		int j = 0;

		for (int i = 0; i < pushed.length; i++)
		{
			stack.push(pushed[i]);

			while(!stack.isEmpty() && j < popped.length && popped[j] == stack.peek())
			{
				stack.pop();
				j++;
			}

		}

		return stack.isEmpty();
	}
}
