package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.Node;

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList extends LeetCodeExercise{

	public static void main(String[] args) {
		//		4,2,5,1,3
		Node a1 = new Node(0);
		Node a3 = new Node(3);
		Node a5 = new Node(5);
		Node a2 = new Node(2, a1, a3);
		Node a4 = new Node(4, a2, a5);
		ConvertBinarySearchTreeToSortedDoublyLinkedList c = new ConvertBinarySearchTreeToSortedDoublyLinkedList();
		Node ans = c.treeToDoublyList(a1);

		int stopValue = ans.val;
		int loops = 1;


		//testing
		while (loops >= 0 && ans != null)
		{
			if(ans.val == stopValue)
			{
				if (--loops < 0)
					break;
			}
			System.out.print(ans.val + " -> " );
			ans = ans.right;
		}

	}

	public Node head;
	public Node tail;


	/**
	 * 
	 * @intuition
	 * 		head is discovered on tree traversal
	 * 		Tail is returned at the end of the traversal
	 * 		current node sets the right for the previous. and its left for the previous recurently
	 * 		if null previous is returned instead of null
	 * 		connection head and tail is made in the end
	 * 
	 * @score
		  	Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
			Memory Usage: 39.2 MB, less than 6.90% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
	
	 * @optimizations
	 * 
	 * 
	 * @alternatives
	 * 		We might would find a iterative aproach to this that would reduce space complexity to O(1)
	 * 
	 * @fail
	 * 		1) forgot to add connection to the own node if it's just one
	 * 
	 * @time O(N)
	 * @space O(N) worst case skewed tree is O(N)
	 * @bcr  O(N)
	 * 
	 * @param root
	 * @return
	 */
	public Node treeToDoublyList(Node root) {

		if (root == null)
			return null;

		if (root.right == null && root.left == null)
		{
			root.right = root;
			root.left = root;
			return root;
		}

		head = new Node(Integer.MAX_VALUE);
		tail = new Node(Integer.MIN_VALUE);

		tail = convert(null, root);

		head.left = tail;
		tail.right = head;

		return head;
	}

	private Node convert(Node prev, Node cur)
	{

		if (cur == null)
			return prev;

		prev = convert(prev, cur.left);

		if (prev != null)
		{
			prev.right = cur;
			cur.left = prev;
		}

		// setting element to head if it is lower
		if (head.val > cur.val)
			head = cur;


		return convert(cur, cur.right);
	}

}


/**
 * Same principles than my solution but with no return in the helper function.. 
 * which is interesting to see
 * 
 * Essentially they use the last as previous, and I use a explicit previous
 * 
 * @author Nelson Costa
 *
 */
class ConvertBinarySearchTreeToSortedDoublyLinkedListSolution {
	  // the smallest (first) and the largest (last) nodes
	  Node first = null;
	  Node last = null;

	  public void helper(Node node) {
	    if (node != null) {
	      // left
	      helper(node.left);
	      // node 
	      if (last != null) {
	        // link the previous node (last)
	        // with the current one (node)
	        last.right = node;
	        node.left = last;
	      }
	      else {
	        // keep the smallest node
	        // to close DLL later on
	        first = node;
	      }
	      last = node;
	      // right
	      helper(node.right);
	    }
	  }

	  public Node treeToDoublyList(Node root) {
	    if (root == null) return null;

	    helper(root);
	    // close DLL
	    last.right = first;
	    first.left = last;
	    return first;
	  }
	}
