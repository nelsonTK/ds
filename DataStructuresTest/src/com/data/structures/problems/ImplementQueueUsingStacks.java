package com.data.structures.problems;

import java.util.Stack;

public class ImplementQueueUsingStacks {
	
	
	/**
	 * @Intuition
	 * 		this is like the obvious implementation of a queue using stacks
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement Queue using Stacks.
	 *		Memory Usage: 37.1 MB, less than 65.80% of Java online submissions for Implement Queue using Stacks.
	 * 
	 * @optimization
	 * 		having a stack for poping, and a stack for pushing
	 * 
	 * @time
	 * 		push
	 * 			O(N)
	 * 
	 * @space
	 * 		push
	 * 			O(N)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class MyQueue {

		Stack<Integer> s = new Stack<>();

		/** Initialize your data structure here. */
		public MyQueue() {

		}

		/** Push element x to the back of queue. */
		public void push(int x) {
			Stack<Integer> tmp = new Stack<>();

			while(!s.isEmpty())
			{
				tmp.push(s.pop());
			}

			s.push(x);


			while(!tmp.isEmpty())
			{
				s.push(tmp.pop());
			}
		}

		/** Removes the element from in front of queue and returns that element. */
		public int pop() {
			return s.pop();
		}

		/** Get the front element. */
		public int peek() {
			return s.peek();
		}

		/** Returns whether the queue is empty. */
		public boolean empty() {
			return s.isEmpty();
		}
	}
}
