package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RemoveNthNodeFromEndofList {

	public static void main(String[] args) {

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Nothing special, traverse and map the values
	 * 
	 * @comments
	 * 		This solution could be even more simplified
	 * 		I didn't had to remove the reference the extracted node had to another nodes.
	 * 		The object would still be garbage collected.
	 * 		So it would result in a more concise code
	 * 
	 * @score
	 *		Runtime: 1 ms, faster than 34.39% of Java online submissions for Remove Nth Node From End of List.
	 *		Memory Usage: 39.3 MB, less than 5.08% of Java online submissions for Remove Nth Node From End of List.
	 * 
	 * @optimizations
	 * 		array instead of hashmap
	 * 
	 * @time O(N)
	 * @space O(N)
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {

		if (head == null)
			return null;

		if (n < 0)
			return head;

		HashMap<Integer, ListNode> indexToNode = new HashMap<>();  
		int count = 1;


		while(head != null)
		{
			indexToNode.put(count++, head);
			head = head.next;
		}

		//remove element
		int removeIndex = count - (n - 1) - 1;
		ListNode tmp;
		if (removeIndex > 1 && removeIndex <= count)
		{
			ListNode prev = indexToNode.get(removeIndex - 1);
			tmp = prev.next;
			prev.next = tmp.next;
			tmp.next = null;  
		}
		else if (removeIndex == 1)
		{
			tmp = indexToNode.get(removeIndex);
			tmp.next = null;
			return indexToNode.get(removeIndex + 1);
		}

		return indexToNode.get(1);
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Another very cleaver solution which do not do one pass as they claim but it is still smart
 * 
 * he uses two pointers, first he progresses the first one.
 * 
 * then he progresses both of them until the first one reaches null.
 * 
 * when the first one reaches null we know the second one is in the right position
 * 
 * @author Nelson Costa
 *
 */
class RemoveNthNodeFromEndofListSolution2 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    ListNode first = dummy;
	    ListNode second = dummy;
	    // Advances first pointer so that the gap between first and second is n nodes apart
	    for (int i = 1; i <= n + 1; i++) {
	        first = first.next;
	    }
	    // Move first to the end, maintaining the gap
	    while (first != null) {
	        first = first.next;
	        second = second.next;
	    }
	    second.next = second.next.next;
	    return dummy.next;
	}
}

/**
 * Two pass solution very cleaver
 * 
 * First the user calculates the length
 * 
 * Then he subtracks n to the maximum length
 * 
 * then he moves again from the begining until the element at position length - n.
 * 
 * and finally removes the element
 * 
 * @author Nelson Costa
 *
 */
class RemoveNthNodeFromEndofListSolution1 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int length  = 0;
		ListNode first = head;
		while (first != null) {
			length++;
			first = first.next;
		}
		length -= n;
		first = dummy;
		while (length > 0) {
			length--;
			first = first.next;
		}
		first.next = first.next.next;
		return dummy.next;
	}
}