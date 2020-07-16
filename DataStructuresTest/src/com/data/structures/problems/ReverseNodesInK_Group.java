package com.data.structures.problems;

import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * HARD
 * @author Nelson Costa
 *
 */
public class ReverseNodesInK_Group {
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
    /** 
     * @intuition
     * 		It is like just to count for the groups and revert them.
     * 		Nothing particularly fancy
     * 
     * 		1)	search for the end of the group.
     * 		2)  reverse de sub list
     * 		3)
     * 		
     * @score
     * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
			Memory Usage: 39.2 MB, less than 95.76% of Java online submissions for Reverse Nodes in k-Group.
     * 
     * @time 
     * 		O(N) = O(3N)
     * 
     * @space
     * 		O(1)
     * 
     * @fail
     * 		1) forgot to update beginning...
    **/
    public ListNode reverseKGroup(ListNode head, int k) {
        
        //guards        
        if (head == null || k == 1)
            return head;
        
        
        ListNode end,           //node that ends a group
            begining = null,    //node before grupo start
            node = head,        //current node
        	preAns = new ListNode(-1);
        
        //get first element of the first Group Set 
        ListNode firstNode = getGroupEnd(node, k); //O(N)

        preAns.next = firstNode;
            
        while(node != null)
        {
            end = getGroupEnd(node, k); //O(N)

            if (end != null)
            {
                //Gets the begining of next iteration & reverses the linkedlist
                begining = reverseList(begining, node,  end.next, k); //O(N)
                node = begining.next;
            }
            else
            {
                node = null;
            }
        }        
        return preAns.next;
    }
    
    /**
     * 
     * @param beg is the element before the group starts
     * @param cur
     * @param end
     * @param k
     * @return
     */
    private ListNode reverseList(ListNode beg, ListNode cur, ListNode end, int k){
        
        ListNode prev = end, tmp, begining = cur;
        while (k > 0){
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
            --k;
        }
        
        //update begining node's next element to the new first element of the group
        if(beg != null)
        {
            beg.next = prev;
        }
        
        return begining;
    }
    
    /**
     * get last element of a group
     * @param node
     * @param k
     * @return
     */
    private ListNode getGroupEnd(ListNode node, int k){
        
        while (--k > 0 && node!=null){
            node = node.next;
        }
        
        return node;
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
class ReverseNodesInK_GroupSolution2 {
    
    public ListNode reverseLinkedList(ListNode head, int k) {
        
        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains 
        // atleast k nodes.
        ListNode new_head = null;
        ListNode ptr = head;
        
        while (k > 0) {
            
            // Keep track of the next node to process in the
            // original list
            ListNode next_node = ptr.next;
            
            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;
            
            // Move on to the next node
            ptr = next_node;
            
            // Decrement the count of nodes to be reversed by 1
            k--;
        }
            
            
        // Return the head of the reversed list
        return new_head;
    
    }
            
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode ptr = head;
        ListNode ktail = null;
        
        // Head of the final, moified linked list
        ListNode new_head = null;
        
        // Keep going until there are nodes in the list
        while (ptr != null) {
            
            int count = 0;
            
            // Start counting nodes from the head
            ptr = head;
            
            // Find the head of the next k nodes
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count += 1;
            }

            // If we counted k nodes, reverse them        
            if (count == k) {
                
                // Reverse k nodes and get the new head
                ListNode revHead = this.reverseLinkedList(head, k);
                
                // new_head is the head of the final linked list
                if (new_head == null)
                    new_head = revHead;
                
                // ktail is the tail of the previous block of 
                // reversed k nodes
                if (ktail != null)
                    ktail.next = revHead;
                    
                ktail = head; 
                head = ptr;
            }
        }
            
         // attach the final, possibly un-reversed portion
        if (ktail != null)
            ktail.next = head;
        
        return new_head == null ? head : new_head;
    }
}

/**
 * Recursive Top Solution
 * 
 */
class ReverseNodesInK_GroupUnofficialSolution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        
        int len = 0;
        ListNode temp = head;
        while(temp !=null){
            len++;
            temp = temp.next;
        }
        
        if(len<k) return head; //Base case
        
        int count =0;
        
        while(curr != null && count<k){
            //System.out.print(" curr "+ curr.val);
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        
        if(next !=null){
            //System.out.println(" curr "+ curr.val+ " next "+ next.val);
            head.next = reverseKGroup(next,k);
        }
        return prev;
    }
    
}
