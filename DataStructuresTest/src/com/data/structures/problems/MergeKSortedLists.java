package com.data.structures.problems;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.ListNode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * HARD
 * @author Nelson Costa
 *
 */
public class MergeKSortedLists extends LeetCodeExercise{

	public static void main(String[] args) {

		MergeKSortedLists m = new MergeKSortedLists();

		//a
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(4);
		ListNode a3 = new ListNode(5);	
		a1.next = a2;
		a2.next = a3;

		//b
		ListNode b1 = new ListNode(1);
		ListNode b2 = new ListNode(3);
		ListNode b3 = new ListNode(4);		
		b1.next = b2;
		b2.next = b3;

		//c
		ListNode c1 = new ListNode(2);
		ListNode c2 = new ListNode(6);	
		c1.next = c2;

		ListNode [] lists = new ListNode[3];
		lists[0] = a1;
		lists[1] = b1;
		lists[2] = c1;

		printListNode(a1);
		printListNode(b1);
		printListNode(c1);

		printListNode(m.mergeKLists(lists));
	}

	/**
	 * @intuition
	 * 		I merge k list with the help of a priority queue that sorts the objects
	 * 		then I use a answer listnode that will point to the first node.
	 * 		And I'll use a last listnode that keeps updating
	 * 
	 * 		It's important to preserve the references, this is probably the trickiest part
	 * 
	 * @fail
	 * 		1) forgot to add a clause that Was identified and draw in my sketch.. what a shame
	 * 
	 * @score
			Runtime: 5 ms, faster than 47.32% of Java online submissions for Merge k Sorted Lists.
			Memory Usage: 41.5 MB, less than 39.35% of Java online submissions for Merge k Sorted Lists.

			Runtime: 4 ms, faster than 81.16% of Java online submissions for Merge k Sorted Lists.
			Memory Usage: 41.4 MB, less than 42.08% of Java online submissions for Merge k Sorted Lists.

	   @Comments 
	   		If I initiallize a comparator and used it as argumant of priority queue I get 4ms and 80%

	   @optimizations
	   		I believe I can optimize if i dont always poll the elements

	 * @time	O(n log k)
	 * @space	O(k)
	 * @bcr		O(n)
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {

		if (lists == null || lists.length == 0)
			return null;

		if (lists.length == 1)
			return lists[0];

		ListNode ans = new ListNode(-1);
		ListNode last = new ListNode(-1);
		ans.next = last;

		Comparator<ListNode> c = new Comparator<ListNode>()
		{
			@Override
			public int compare(ListNode a, ListNode b) {
				return Integer.compare(a.val, b.val);
			}

		};

		PriorityQueue<ListNode> min = new PriorityQueue<ListNode> (c);
		//		PriorityQueue<ListNode> min = new PriorityQueue<ListNode> (
		//				(a,b) -> Integer.compare(a.val, b.val)
		//				);

		for (ListNode n : lists) {
			if (n != null)
				min.offer(n);
		}

		while (!min.isEmpty())
		{
			last.next = min.poll();

			if (last.next.next != null)
			{
				min.offer(last.next.next);
			}

			//update reference without jeoperdizing answer list references
			last = last.next;
		}

		return ans.next.next;
	}

}



/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * very cleaver solution for merging k lists, the way it does is to merge pairs only once
 * It transforms a k problem in a set of merge of 2 problem (Divide and conquer)
 * In this question there is a set of challenges which is to calculate the intervals and came up with this solution iteratively
 * 
 * [0][1] [2][3]
 * 	\		/
 *  [0]	  [2]
 * 		V
 * 	   [0]
 * 
 * @time  O(N logk)
 * @space O(1)
 * @author Nelson Costa
 *
 */
class MergeKSortedListsSolution5 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode h = new ListNode(0);
		ListNode ans=h;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				h.next = l1;
				h = h.next;
				l1 = l1.next;
			} else {
				h.next = l2;
				h = h.next;
				l2 = l2.next;
			}
		}
		if(l1==null){
			h.next=l2;
		}
		if(l2==null){
			h.next=l1;
		} 
		return ans.next;
	}


	public ListNode mergeKLists(ListNode[] lists) {
		if(lists.length==0){
			return null;
		}
		int interval = 1;
		while(interval<lists.length){
			System.out.println(lists.length);
			for (int i = 0; i + interval< lists.length; i=i+interval*2) {
				lists[i]=mergeTwoLists(lists[i],lists[i+interval]);            
			}
			interval*=2;
		}

		return lists[0];
	}
}


/**
 * Same as my solution but is uses a external comparator instead a lambda comparator.
 * I also appreciate how he handles the references issues by setting the point equals to Head.
 * Sweet
 * 
 * 		Runtime: 4 ms, faster than 81.16% of Java online submissions for Merge k Sorted Lists.
		Memory Usage: 41.3 MB, less than 42.63% of Java online submissions for Merge k Sorted Lists.


 * @author Nelson Costa
 *
 */
class MergeKSortedListsSolution3 {

	public ListNode mergeKLists(ListNode[] lists) { 
		Comparator<ListNode> cmp;
		cmp = new Comparator<ListNode>() {  
			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.val-o2.val;
			}
		};

		Queue<ListNode> q = new PriorityQueue<ListNode>(cmp);
		for(ListNode l : lists){
			if(l!=null){
				q.add(l);
			}        
		}
		ListNode head = new ListNode(0);
		ListNode point = head;
		while(!q.isEmpty()){ 
			point.next = q.poll();
			point = point.next; 
			ListNode next = point.next;
			if(next!=null){
				q.add(next);
			}
		}
		return head.next;
	}
}
