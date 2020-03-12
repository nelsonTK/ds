package com.data.structures.ds;

public class PriorityQueue extends MaxHeap {
	
	public PriorityQueue() {
		super();
	}
	
	/**
	 * get the top priority element.
	 * @return
	 */
	public int peek() {		
		return size > 0 ? array[0] : -1;
	}
	
	/**
	 * Remove the top priority Element
	 */
	public void remove() {
		delete(0);
	}
	
	/**
	 * get and remove the top priority element
	 * @return
	 */
	public int poll() {
		
		int topPriority = 0;
		
		int peek =  getElementAt(topPriority);
		delete(topPriority);
		
		return peek;
	}
	
	/**
	 * Add Element to the priority queue
	 */
	public void add(int element) {
		super.add(element);
	}
}
