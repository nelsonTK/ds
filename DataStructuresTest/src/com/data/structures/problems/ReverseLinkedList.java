package com.data.structures.problems;

public class ReverseLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseLinkedList r = new ReverseLinkedList();

		ListNode l1 = r.new ListNode(1);
		ListNode l2 = r.new ListNode(2);
				ListNode l3 = r.new ListNode(3);
		l1.next = l2;
				l2.next = l3;
		r.printLinkedList(l1);
		r.printLinkedList(r.reverseList(l1));
	}

	/**
	 * @fail I have not fliped the last node, forgot to add in the end
	 * fail again, even and odd number of elements is different
	 * 
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
		Memory Usage: 39.3 MB, less than 5.04% of Java online submissions for Reverse Linked List.
	 * 
	 * 
	 * @time O(N) N number of nodes - 1
	 * @space O(1) constant time
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {

		if (head == null)
			return null;

		ListNode temp = null;
		ListNode prev = null;
		ListNode cur = head;
		ListNode next = cur.next;

		while (next != null) {
			//flip references
			temp = next.next;
			cur.next = prev;
			next.next = cur;

			//set next iteration
			cur = temp;
			prev = next;
			next = cur != null ? cur.next : null;
		}

		//deal with the last node
		if (cur != null) 
		{
			//odd element number of nodes
			cur.next = prev;
		}
		else
		{
			//even element number of nodes 
			cur = prev;
		}
		
		return cur;
	}

	public void printLinkedList(ListNode head) {

		if (head == null)
		{
			System.out.println("nothing to print");
			return;
		}

		System.out.println();
		while (head != null)
		{
			System.out.print("["+ head.val+ "] -> ");
			head = head.next;
			if (head == null)
			{
				System.out.print("[NULL]");
			}
		}
	}


	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}

class ReverseLinkedListSolution {
	
	public ListNode reverseList(ListNode head) {
	    ListNode prev = null;
	    ListNode curr = head;
	    
	    //very simple approach, go through each node and se next equals to previous.
	    while (curr != null) {
	        ListNode nextTemp = curr.next;
	        curr.next = prev;
	        prev = curr;
	        curr = nextTemp;
	    }
	    return prev;
	}
}
