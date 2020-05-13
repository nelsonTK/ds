package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class AddTwoNumbers extends LeetCodeExercise{

	public static void main(String[] args) {

	}

	/**
	 * @intuition
	 * 		I just added the result of the sum to one of the lists
	 * 
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Two Numbers.
			Memory Usage: 39.3 MB, less than 99.69% of Java online submissions for Add Two Numbers.
			
			I believe that If I remove the heavy logic of verifying nulls I can achieve 0ms
	 * 
	 * @fail
	 * 		1) don't update the current
	 * 		2) I was rewriting cur reference inside the method
	 * 		3) Forgot the case where the carry is the only one left (bad test cases)
	 * 
	 * @time 	O(N)
	 * @space 	O(1)
	 * @bcr  	O(N)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode a, ListNode b) {

		ListNode pre = new ListNode(-1);
		ListNode cur = pre;
		int carry = 0;
		int sum = 0;

		while (a != null || b != null)
		{

			if (a != null && b != null)
			{
				sum = a.val + b.val + carry;
				carry = sumNode(a, cur, sum);
				a = a.next;
				b = b.next;
			}
			else if (a == null)
			{
				sum = b.val + carry;
				carry = sumNode(b, cur, sum);
				b = b.next;
			}
			else // b == null
			{
				sum = a.val + carry;
				carry = sumNode(a, cur, sum);
				a = a.next;
			}

			cur = cur.next;

		}

		if (carry > 0)
			cur.next = new ListNode(carry);

		return pre.next;
	}

	/**
	 * return carry
	 * @param node
	 * @param cur
	 * @param sum
	 * @return
	 */
	private int sumNode(ListNode node, ListNode cur, int sum) {

		if (sum > 9)
		{
			node.val = sum - 10;
			cur.next = node;
			return 1; // carry
		}
		else
		{
			node.val = sum;
			cur.next = node;
			return 0;
		}

	}
}

/**
 * Very similar aproach to mine but it subtle differences
 * 
 * 1) they use extra Space and I dont
 * 2) They do a brilliant work with the logic of abstracting the sum of whether or not the lists are null
 * 
 * @author Nelson Costa
 *
 */
class AddTwoNumbersSolution1{

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null) p = p.next;
			if (q != null) q = q.next;
		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

}
