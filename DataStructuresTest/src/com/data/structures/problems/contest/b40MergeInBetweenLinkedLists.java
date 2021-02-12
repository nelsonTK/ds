package com.data.structures.problems.contest;

import com.data.structures.problems.ds.ListNode;

/**
 * 
 * @author Nelson Costa
 *
 */
public class b40MergeInBetweenLinkedLists {

class Solution {
	
	/**
	 * @intuition
	 * 		Very Simple intuition
	 * 		I just have some milestones and save those milestones and link the nodes...
	 * 
	 * @score
	 *     	Runtime: 1 ms, faster than 100.00% of Java online submissions for Merge In Between Linked Lists.
	 *     	Memory Usage: 43 MB, less than 100.00% of Java online submissions for Merge In Between Linked Lists.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param list1
	 * @param a
	 * @param b
	 * @param list2
	 * @return
	 */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        
        
        ListNode ans = new ListNode(-1);
        ans.next = list1;
        ListNode lStart, lEnd; 
        int i = 0;
        
        //find first Milestone
        while (i < a - 1)
        {
            list1 = list1.next;
            i++;
        }
        
        lStart = list1;
        
        //Find second Milestone
        while (i < b)
        {
            list1 = list1.next;
            i++;
        }
        
        lEnd = list1;
        
        ListNode l2End = list2;
        
        //Find third Milestone
        while (l2End.next != null)
        {
            l2End = l2End.next;
        }
        
        
        //Join Milestones
        lStart.next = list2;
        l2End.next = lEnd.next;
        
        return ans.next;
    }
}
}
