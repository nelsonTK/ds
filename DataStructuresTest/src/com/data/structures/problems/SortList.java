package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/sort-list/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SortList extends LeetCodeExercise{

	static SortList s = new SortList();
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n3;
		n4.next = n2;
		n2.next = n1;

		ListNode ans = s.sortList(n4);
		System.out.println(ans.val);
	}

	/*********************************
	* SOLUTION  1
	********************************/
	/**
	 * @intuition
	 * 		The problem was solved with the application of merge sort to the listnodes.
	 * 		
	 * 		The challenging part was how to merge the list efficiently.
	 * 
	 * 		What I did was to divide the list in halves which would implied breaking the links between left and right sides.
	 * 
	 * 		Use and auxiliar node to maintain the head at the combine method
	 * 
	 *  @fail 
	 *  	1) stack overflow. The while loop was not okay because of the end of while loops was bounded by null and it should be dynamic
	 *  	2) mergeHalves was wrong the limits where wrong once again for the same reason
	 *  	3) nullpointer exception because of fast pointer and the changes to include size
	 *  	4) a lot of errors related with the pointers
	 *  	5) all nodes were linked and caused a lot of trouble, the solution itself is messy
	 *  
	 * @comment
	 * 		The question asked for a O(1) space complexity, I need to repeat it because my solution don't respect that requirement
	 * 
	 * @score
	 * 		Runtime: 3 ms, faster than 98.34% of Java online submissions for Sort List.
	 *		Memory Usage: 41.5 MB, less than 84.35% of Java online submissions for Sort List.
	 *
	 * @time 
	 *		O(nlogn)
	 *
	 * @space
	 * 		O(nlogn)
	 * 		the stack grows at a maximum of nlogn. nothing more than that because the list is rebuild in place.
	 *
	 * @param head
	 * @return
	 */
    public ListNode sortList(ListNode head) {
        
        //head == null
        if(head == null)
            return head;
        
        ListNode end = head;
        
        while (end != null)
        {
            if (end.next == null)
                break;
            
            end = end.next;
        }
        
        return merge(head, end);
    }
    
    private ListNode merge(ListNode left, ListNode right)
    {
        if (left == right)
            return left;
        
        ListNode slow = left;
        ListNode fast = left;
        
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode rightStart = slow.next;
        slow.next = null;
        
        ListNode l = merge(left, slow);
        ListNode r = merge(rightStart, right);
        return combine(l, r);
    }
    
    private ListNode combine(ListNode left, ListNode right)
    {
        ListNode cur = new ListNode(-1);
        ListNode aux = cur;
        
        while (left != null && right != null)
        {
            if(left.val <= right.val)
            {
                cur.next = left;
                left = left.next;
                cur = cur.next;
            }
            else
            {
                cur.next = right;
                right = right.next;
                cur = cur.next;
            }
        }
        
        
        if (left != null)
        {
            cur.next = left;
        }
        else if (right != null)
        {
            cur.next = right;
        }
        
        return aux.next;
    }
    
}
