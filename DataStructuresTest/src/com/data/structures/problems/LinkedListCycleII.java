package com.data.structures.problems;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LinkedListCycleII {

	public static void main(String[] args) {

	}


	/**
	 * 
	 * @intuition
	 * 		This solution is entirely based in floyd algorithm theory
	 * 		which can be breakdown in two phases
	 * 			1) detect cycle
	 * 			2) find the cycle entry node
	 * 				2.1) the distance between first node and entry point and the 
	 * 					distance between the node where the intersection happend (doing next operations is equals)
	 * 				2.2) if we move new pointers from start and from the intersection, one step at a time they will meet at the entrance of the cycle.
	 * 
	 * 	
	 * 
	 * 
	 * @score
			Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
			Memory Usage: 39.8 MB, less than 42.96% of Java online submissions for Linked List Cycle II.	
	 * 
	 * @fail
	 * 		1) forgot to add stop condition for the while loop
	 * 
	 * @time
	 * @space
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {

		if (head == null || head.next == null)
			return null;

		ListNode p1 = head;
		ListNode p2 = head;

		//phase 1 find the meeting point
		while (p2 != null && p2.next != null)
		{
			p1 = p1.next;
			p2 = p2.next.next;

			if (p2 == p1)
				break;
		}

		if (p2 == null || p2.next == null)
			return null;

		//phase 2 finds the entry point
		p1 = head;
		//p2 equals to intersection
		while (p1 != p2)
		{
			p1 = p1.next;
			p2 = p2.next;
		}

		return p2;
	}
}
