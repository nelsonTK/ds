package com.data.structures.problems;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 * EASY
 * 
 * @author Nelson Costa
 *
 */
public class ConvertBinaryNumberInALinkedListToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode n1 = new ListNode(0);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		ConvertBinaryNumberInALinkedListToInteger c = new ConvertBinaryNumberInALinkedListToInteger();
		System.out.println(c.getDecimalValue(n1));

	}

	/**		Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Number in a Linked List to Integer.
			Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Convert Binary Number in a Linked List to Integer.
	 * 
	 * very easy solution
	 * @space O(1)
	 * @time O(1) because it is up to 30 nodes.
	 * @param head
	 * @return
	 */
	public int getDecimalValue(ListNode head) {
		
		int r = 0;
		
		do {
			r = r << 1;
			r += head.val;
			head = head.next;
		}
		while(head != null);

		return r;
	}

}
