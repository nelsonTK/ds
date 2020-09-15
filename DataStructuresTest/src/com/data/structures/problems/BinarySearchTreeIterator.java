package com.data.structures.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/peeking-iterator
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class BinarySearchTreeIterator extends LeetCodeExercise{

	static BinarySearchTreeIterator b = new BinarySearchTreeIterator();

	public static void main(String[] args) {

		TreeNode t = stringToTreeNode("[4,5,6,21,3]");
		BSTIterator obj = b.new BSTIterator(t);
		int param_1 = obj.next();
		boolean param_2 = obj.hasNext();
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * 
	 * 
	 * 	@intuition
	 * 	 	I had a Linked List with the always the most immediate smaller element
	 * 	 	I had a getSmaller method that always update my list
	 * 
	 * 	 	next 	
	 * 	 		would return the first element of the linkedlist and remove that first element, 
	 * 	 		and then check if there is a right element to explore
	 * 	 		I had troubles indicating the average TC of this case.
	 * 	 	hasnext
	 * 	 		I would check if the next linkedlist node is null, if it is than I would return false;
	 * 
	 * 
	 * 	@score
	 * 		Runtime: 28 ms, faster than 14.43% of Java online submissions for Binary Search Tree Iterator.
	 * 		Memory Usage: 56.5 MB, less than 5.02% of Java online submissions for Binary Search Tree Iterator.
	 * 
	 * 	@optimization
	 * 		I could do this iteratively
	 * 
	 * 	@fail
	 * 
	 **/

	class BSTIterator {


		TreeNode root;
		LNode next;

		public BSTIterator(TreeNode r) {
			root = r;
			getSmallest(root);
		}

		/** @return the next smallest number */

		/**
		 *  O(N)
	    	O(1) average
		 * @return
		 */
		public int next() {
			TreeNode nextNode = next.val;

			next = next.next;

			if (nextNode.right != null)
			{
				getSmallest(nextNode.right);
			}

			return nextNode.val;
		}

		/**
		 * O(N)
		 * fills the list with the smallest elements
		 * @param node
		 */
		private void getSmallest(TreeNode node){

			if (node == null)
				return;

			//create new Lnode add to next
			LNode lNode = new LNode(node, next);
			next = lNode;

			getSmallest(node.left);
		}

		/**
		 * O(1)
		 * @return
		 */
		public boolean hasNext() {
			return next == null ? false : true;
		}

		class LNode {
			TreeNode val;
			LNode next;

			public LNode(TreeNode n)
			{
				val = n;
				next = null;
			}

			public LNode(TreeNode v, LNode n)
			{
				val = v;
				next = n;
			}
		}
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * This is the exact same approach then mine but with stack implementation4
 * 
 * @score
 * 		Runtime: 28 ms, faster than 14.43% of Java online submissions for Binary Search Tree Iterator.
		Memory Usage: 50.9 MB, less than 12.56% of Java online submissions for Binary Search Tree Iterator.
 * 
 * @author Nelson Costa
 *
 */
class BinarySearchTreeIteratorSolution2 {

	Stack<TreeNode> stack;

	public BinarySearchTreeIteratorSolution2(TreeNode root) {

		// Stack for the recursion simulation
		this.stack = new Stack<TreeNode>();

		// Remember that the algorithm starts with a call to the helper function
		// with the root node as the input
		this._leftmostInorder(root);
	}

	private void _leftmostInorder(TreeNode root) {

		// For a given node, add all the elements in the leftmost branch of the tree
		// under it to the stack.
		while (root != null) {
			this.stack.push(root);
			root = root.left;
		}
	}

	/**
	 * @return the next smallest number
	 */
	public int next() {
		// Node at the top of the stack is the next smallest element
		TreeNode topmostNode = this.stack.pop();

		// Need to maintain the invariant. If the node has a right child, call the 
		// helper function for the right child
		if (topmostNode.right != null) {
			this._leftmostInorder(topmostNode.right);
		}

		return topmostNode.val;
	}

	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		return this.stack.size() > 0;
	}
}


/**
 * Unofficial solution, is the top solution but its not complient with the question requirements
 */
class BinarySearchTreeIteratorUnofficialSolution1 {
	Queue<Integer> al;
	int index = 0;

	public BinarySearchTreeIteratorUnofficialSolution1(TreeNode root) {

		al = new LinkedList<>();
		helper(root);
	}

	/** @return the next smallest number */
	public int next() {
		return al.poll();
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return al.size() > 0;
	}

	private void helper(TreeNode root){
		if(root == null)
			return;
		helper(root.left);
		al.offer(root.val);
		helper(root.right);
	}

}

/**
 * The rational here was to perform inorder traversal, fill an array and then use that array to achieve average time
 * 
 * it does not respect this questions requesite for space complexity though
 * 
 * @score
		Runtime: 35 ms, faster than 6.87% of Java online submissions for Binary Search Tree Iterator.
		Memory Usage: 56.7 MB, less than 5.02% of Java online submissions for Binary Search Tree Iterator.
 * 
 * @author Nelson Costa
 *
 */
class BinarySearchTreeIteratorSolution1 {

	ArrayList<Integer> nodesSorted;
	int index;

	public BinarySearchTreeIteratorSolution1(TreeNode root) {

		// Array containing all the nodes in the sorted order
		this.nodesSorted = new ArrayList<Integer>();

		// Pointer to the next smallest element in the BST
		this.index = -1;

		// Call to flatten the input binary search tree
		this._inorder(root);
	}

	private void _inorder(TreeNode root) {

		if (root == null) {
			return;
		}

		this._inorder(root.left);
		this.nodesSorted.add(root.val);
		this._inorder(root.right);
	}

	/**
	 * @return the next smallest number
	 */
	public int next() {
		return this.nodesSorted.get(++this.index);
	}

	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		return this.index + 1 < this.nodesSorted.size();
	}
}
