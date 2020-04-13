package com.data.structures.problems;

/**
 * EASY
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * 
 * @author Nelson Costa
 *
 */
public class SearchInABinarySearchTree {

	public static void main(String[] args) {
		SearchInABinarySearchTree s = new SearchInABinarySearchTree();
		TreeNode t0 = s.new TreeNode(4);
		TreeNode t1 = s.new TreeNode(2);
		TreeNode t2 = s.new TreeNode(7);
		TreeNode t3 = s.new TreeNode(1);
		TreeNode t4 = s.new TreeNode(3);
		t0.left = t1;
		t0.right = t2;
		t1.left = t3;
		t1.right = t4;
		s.printBST(t0);
		int searchVal = 0;
		System.out.println("--------Search " + searchVal + "--------");
		s.printBST(s.searchBST(t0, searchVal));
		

	}

	private void printBST(TreeNode t) {

		if (t == null)
			return;

		printBST(t.left);

		System.out.println(t.val);

		printBST(t.right);
	}

	/**
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in a Binary Search Tree.
			Memory Usage: 40.3 MB, less than 8.89% of Java online submissions for Search in a Binary Search Tree.
	 * 
	 * 
	 * Nothing fancy applied
	 * @space O(1)
	 * @time O(H) H equals to the tree height, or I believe log2 N
	 * @param root
	 * @param val
	 * @return
	 */
	public TreeNode searchBST(TreeNode root, int val) {

		if (root == null)
			return null;

		while (root != null)
		{
			if(val < root.val)
			{
				root = root.left;
			}
			else if(val > root.val)
			{
				root = root.right;
			}
			else if(val == root.val)
			{
				return root;
			}

		}

		return null;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
