package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/min-stack/
 * EASY
 * @author Nelson Costa
 *
 */
public class MinStack {

	public static void main(String[] args) {


		//Your MinStack object will be instantiated and called as such:
		MinStack obj = new MinStack();
		obj.push(2);
		obj.push(-2);
		obj.push(-52);
		obj.pop();
		int param_3 = obj.top();
		int param_4 = obj.getMin();

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 	@fail
	 * 		1) compiling errors
	 */
	public MinStack() {
		map = new TreeMap<>();
		q = new ArrayDeque<>();
	}

	TreeMap<Integer, Integer> map;
	ArrayDeque<Integer> q;

	/** [Wrong]
	 * I baddly read the question statement
    @time  O(log n)
    @space O(1)
	 */
	public void push(int x) {        
		map.put(x, map.getOrDefault(x, 0) + 1);
		q.addFirst(x);
	}


	/**
    @time  O(1)
    @space O(1)
	 */
	public void pop() {
		Integer removed = q.poll();
		Integer elementCount = map.get(removed) - 1;

		if (elementCount <= 0)
			map.remove(removed);
		else
			map.put(removed, elementCount);
	}


	/**
    @time  O(1)
    @space O(1)
	 */
	public int top() {
		return q.getFirst();
	}

	/**
	 * 
	 * 
    @time  O(1)
    @space O(1)
	 */
	public int getMin() {
		return map.firstKey();
	}
}

