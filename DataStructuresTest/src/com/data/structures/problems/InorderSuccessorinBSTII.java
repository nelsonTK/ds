package com.data.structures.problems;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class InorderSuccessorinBSTII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is base in a simple premise.
	 * 		If you have nodes at your right then the next element is the left most of your right node.
	 * 		Else it is the first parent bigger than you.
	 * 		Very easy question
	 * 
	 * @score
	 * 		Runtime: 27 ms, faster than 67.29% of Java online submissions for Inorder Successor in BST II.
	 *		Memory Usage: 40.3 MB, less than 94.25% of Java online submissions for Inorder Successor in BST II.
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 */
	int reference;
	public Node inorderSuccessor(Node node) {

		if (node == null)
			return node;

		reference = node.val;

		/**if node has right the next is there**/
		if (node.right != null)
		{
			return getNextRight(node.right);
		}
		/**if not has no right, the next must be a parent**/
		else
		{
			while (node.parent != null && node.parent.val < reference )
			{
				node = node.parent;
			}

			return node.parent;
		}
	}    

	private Node getNextRight(Node node)
	{
		while (node.left != null){
			node = node.left;
		}

		return node;
	}

	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	};
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * Same logic than my solution
 * @author Nelson Costa
 *
 */
class InorderSuccessorinBSTIISolution {
	public Node inorderSuccessor(Node x) {
		// the successor is somewhere lower in the right subtree
		if (x.right != null) {
			x = x.right;
			while (x.left != null) x = x.left;
			return x;
		}

		// the successor is somewhere upper in the tree
		while (x.parent != null && x == x.parent.right) x = x.parent;
		return x.parent;
	}

	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	};
}