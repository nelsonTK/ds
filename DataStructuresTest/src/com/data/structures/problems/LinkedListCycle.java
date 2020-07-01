package com.data.structures.problems;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle/solution/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LinkedListCycle {

	public static void main(String[] args) {

	}

	

	/**
	 * @intuition
	 * 		fast and slow runner. 
	 * 			the intuition is that if I have two pointers at different speeds 
	 * 			if one is faster than the other they will eventually meet if there is a cycle
	 * 			else null will be returned.
	 * @score
			Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
			Memory Usage: 39.5 MB, less than 69.39% of Java online submissions for Linked List Cycle.
	 * 
	 * @fail
	 *      1)  forgot edge case for listnode element of size 1
	 *      
	 * @time	O(N + K)
	 * 			N is the steps till enter cycle
	 * 			K is the number of laps that need to be performed inside the cycle
	 * 				K = difference between fast and slow runner position / difference in speed between runners
	 * @space   O()
	 *      
	 * @param head
	 * @return
	 */
    public boolean hasCycle(ListNode head) {
        //fast and slow pointer
        
        if (head == null || head.next == null)
            return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null)
        {
            slow = slow.next;
            fast = fast.next;
            fast = fast == null? fast : fast.next;
            
            if (slow == fast)
                return true;
        }
        
        if (fast == null)
            return false;
        
        return true;
    }
}
