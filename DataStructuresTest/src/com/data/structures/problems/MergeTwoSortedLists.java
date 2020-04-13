package com.data.structures.problems;

import java.util.Stack;

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1->2->4, 1->3->4

		MergeTwoSortedLists m = new MergeTwoSortedLists();
		ListNode a1 = m.new ListNode(1); 
		ListNode a2 = m.new ListNode(2); 
		ListNode a3 = m.new ListNode(4); 
		a1.next = a2; a2.next = a3;


		ListNode b1 = m.new ListNode(2); 
		ListNode b2 = m.new ListNode(3); 
		ListNode b3 = m.new ListNode(4); 
		ListNode b4 = m.new ListNode(5); 
		ListNode b5 = m.new ListNode(6); 
		b1.next = b2; 
		b2.next = b3; 
		b3.next = b4; 
		b4.next = b5;

		m.printList(a1);
		m.printList(b1);

		//		m.printList(m.mergeTwoLists1(a1, b1));
		//		m.printList(m.mergeTwoListsRecursive(a1, b1));
		m.printList(m.mergeTwoLists(a1, b1));
		//		m.printList(m.reverse(m.mergeTwoLists1(a1, b1)));
	}

	/**[WRONG]
	 * the rational was to keep the first list and add nodes appropriately but I failed 
	 * mainly because I forgot to address the return of the object
	 * 
	 * In the end I just end up traversing the lists in an ordered manner
	 * 
	 * performed in place sorting
	 * 
	 * @failed... what about the first node?
	 * failed about references... thinking like I could preserve the references...
	 * 
	 * 
	 * @time  O(m + n)
	 * @space O(1)
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode mergeTwoLists0(ListNode a, ListNode b) {

		if (a == null)
			return b;

		if (b == null)
			return a;

		ListNode tmp;
		ListNode aPrev = null;
		ListNode ans = a.val > b.val ? b : a;

		while (a != null && b != null)
		{
			if (a.val > b.val)
			{
				if (aPrev != null)
				{
					aPrev.next = b;
				}				
				tmp = b.next;
				b.next = a;
				aPrev = b;
				b = tmp;
			}
			else
			{
				a = a.next;
			}
		}

		if (b != null)
		{	
			aPrev.next = b;
		}

		return ans;
	}



	/**
	 * nothing fancy.
	 * performed in place sorting
	 * 		Runtime: 1 ms, faster than 9.28% of Java online submissions for Merge Two Sorted Lists.
			Memory Usage: 39.4 MB, less than 17.17% of Java online submissions for Merge Two Sorted Lists.


	 * @failed... I was deleting the last node next elements
	 * 
	 * 
	 * @time  O(2(m + n))
	 * @space O(m + n)
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode mergeTwoLists1(ListNode a, ListNode b) {

		if (a == null)
			return b;

		if (b == null)
			return a;

		Stack<ListNode> stack = new Stack<ListNode>();

		while (a != null && b != null)
		{
			if (a.val > b.val)
			{
				stack.add(b);
				b = b.next;
			}
			else
			{				
				stack.add(a);
				a = a.next;
			}
		}

		if (b != null)
		{	
			stack.add(b);
		}
		else if(a != null) 
		{
			stack.add(a);
		}

		ListNode prev = stack.peek().next;
		ListNode curr = null;

		while (!stack.empty())
		{
			curr = stack.pop();
			curr.next = prev;
			prev = curr;
		}

		return curr;
	}

	/**
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
			Memory Usage: 38.7 MB, less than 19.53% of Java online submissions for Merge Two Sorted Lists.
	 * 
	 * 
	 * @time  O(m+n)
	 * @space O(1)
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode mergeTwoLists2(ListNode a, ListNode b) {

		if (a == null)
			return b;

		if (b == null)
			return a;

		b = reverse(b);
		a = reverse(a);

		ListNode tmp;
		ListNode prev = null;

		while (a != null && b != null)
		{
			if (a.val >= b.val)
			{
				tmp = a.next;
				a.next = prev;
				prev = a;
				a = tmp;
			}
			else
			{
				tmp = b.next;
				b.next = prev;
				prev = b;
				b = tmp;
			}
		}

		while (a != null)
		{
			tmp = a.next;
			a.next = prev;
			prev = a;
			a = tmp;
		}
		while (b != null)
		{
			tmp = b.next;
			b.next = prev;
			prev = b;
			b = tmp;
		}
		return prev;
	}


	public ListNode reverse(ListNode curr) {

		ListNode tmp;
		ListNode prev = null;
		while(curr != null)
		{
			tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
		}

		return prev;
	}


	/**
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
		Memory Usage: 39 MB, less than 19.53% of Java online submissions for Merge Two Sorted Lists.
	 * 
	 * The goal here is to have a anchor node. 
	 * Use a previous node that links the anchor with all the other nodes in the right order.
	 * This was not easy at all
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode a, ListNode b) {

		if (a == null)
			return b;

		if (b == null)
			return a;

		//safe guards
		ListNode beforeFirst = new ListNode(-1);
		ListNode prev = beforeFirst;

		while(a != null && b != null)
		{
			if (a.val <= b.val)
			{
				prev.next = a;
				a = a.next;
			}
			else
			{
				prev.next = b;
				b = b.next;
			}

			prev = prev.next; //this is to allow me to keep linking references.
		}

		if (a != null)
		{
			prev.next = a;
		}

		if (b != null)
		{
			prev.next = b;
		}

		return beforeFirst.next;
	}

	/**
	 * Recursive Solution of the same problem
	 * had troubles trying to represent the possibilities of this problem
	 * 
	 * @time  O(m + n)
	 * @space O(m + n)
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode mergeTwoListsRecursive(ListNode a, ListNode b) {

		if(a == null)
			return b;

		if (b == null)
			return a;

		if (a.val <= b.val)
		{
			a.next = mergeTwoListsRecursive(a.next, b);
			return a;
		}
		else
		{
			b.next = mergeTwoListsRecursive(a, b.next);
			return b;
		}		
	}



	public void printList(ListNode node) {

		while (node != null)
		{
			System.out.print(node.val + " --> ");
			node = node.next;
		}

		System.out.println("NULL");
	}


	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}

/**
 * This solution solves what I was not capable of solving by myself
 * which was how to preserve the first node.
 * This was a very very cool exercise to work referencing skills
 * The rational was the same I tried to apply in my first solution, but this was the correct execution
 * 
 * @author Nelson Costa
 *
 */
class MergeTwoSortedListsSolution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// maintain an unchanging reference to node ahead of the return node.
		ListNode prehead = new ListNode(-1);

		ListNode prev = prehead;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}

		// exactly one of l1 and l2 can be non-null at this point, so connect
		// the non-null list to the end of the merged list.
		prev.next = l1 == null ? l2 : l1;

		return prehead.next;
	}
}