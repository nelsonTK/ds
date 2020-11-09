package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/partition-list/
 * @author Nelson Costa
 *
 */
public class PartitionList {

	/**
	 * @intuition
	 *		In this solution I just wanted to do something that worked.
	 *		I was in a hurry it was the end of the day.
	 *
	 * 		A better solution would be to use two nodes one of the smallest and otther for equal or greater than.
	 * 
	 * @fail
	 * 		
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {

		if (head == null)
			return head;

		List<ListNode> below = new ArrayList<>();
		List<ListNode> above = new ArrayList<>();
		ListNode cur = head;
		while (cur != null)
		{
			if(cur.val < x)
				below.add(new ListNode(cur.val));
			else
				above.add(new ListNode(cur.val));

			cur = cur.next;
		}

		ListNode ans = new ListNode(-1);
		cur = null;

		if (below.size() > 0)
			cur = below.get(0);
		else if(above.size() > 0)
			cur = above.get(0);

		ans.next = cur;

		for (int i = 1; i < below.size(); i++)
		{
			cur.next = below.get(i);
			cur = cur.next;
		}

		int start = below.size() == 0 ? 1 : 0;

		for (int i =  start; i < above.size(); i++)
		{
			cur.next = above.get(i);
			cur = cur.next;
		}

		return ans.next;
	}
}


class PartitionListSolution {
    public ListNode partition(ListNode head, int x) {

        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }
}