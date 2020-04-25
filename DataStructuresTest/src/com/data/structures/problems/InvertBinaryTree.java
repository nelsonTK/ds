package com.data.structures.problems;

import com.data.structures.problems.ds.TreeNode;

/**
 * 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 * EASY
 * aparentemente fácil demais
 * 2020/03/17
 * 
 * @author Nelson Costa
 *
 */
public class InvertBinaryTree {

	public static void main(String[] args) {
		InvertBinaryTree b = new InvertBinaryTree();
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);

		root.left = n2;
		root.right = n3;
		n2.left = n4;

		InvertBinaryTree solution = new InvertBinaryTree();
		solution.print(root);
		solution.invertTree(root);
		System.out.println("++Inverted++");
		solution.print(root);
	}

	/**
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
		Memory Usage: 36.9 MB, less than 5.10% of Java online submissions for Invert Binary Tree.
	 * 
	 * 
	 * @timecomplexity O(N) , not possible to reduce once we have to print the nodes at least once 
	 * @spacecompleity O(H) where H is the height of the tree
	 * @param node
	 * @return
	 */
	public TreeNode invertTree(TreeNode node) {

		if (node == null)
		{
			return node;
		}

		TreeNode tmp = node.left;
		node.left = node.right;
		node.right = tmp;

		invertTree(node.left);
		invertTree(node.right);

		return node;	
	}

	public void print(TreeNode node) {

		if (node == null)
			return;

		print(node.left);
		System.out.println(node.val);
		print(node.right);

	}
}