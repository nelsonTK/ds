package com.data.structures.problems;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 * EASY
 * @author Nelson Costa
 *
 */
public class RemoveLinkedListElements {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		is a simple problem the trickest stuff is dealing with the references but other than that is quite simple
	 * 
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 80.58% of Java online submissions for Remove Linked List Elements.
	 * 		Memory Usage: 40 MB, less than 7.74% of Java online submissions for Remove Linked List Elements.
	 * 
	 * @fail
	 * 		I was not thinking in null for current in the main while
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElements(ListNode head, int val) {

		if (head == null)
			return null;

		ListNode ans = new ListNode(-1);
		ListNode cur = new ListNode(-1);
		cur.next = head;
		ans.next = cur;


		while(cur != null && cur.next != null)
		{
			while (cur.next != null && cur.next.val == val)
			{
				cur.next = cur.next.next;
			}

			cur = cur.next;
		}

		return ans.next.next;   
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * Its a different way of doing the same as me
 * @author Nelson Costa
 *
 */
class RemoveLinkedListElementsSolution1 {
	public ListNode removeElements(ListNode head, int val) {
		ListNode sentinel = new ListNode(0);
		sentinel.next = head;

		ListNode prev = sentinel, curr = head;
		while (curr != null) {
			if (curr.val == val) prev.next = curr.next;
			else prev = curr;
			curr = curr.next;
		}
		return sentinel.next;
	}
}