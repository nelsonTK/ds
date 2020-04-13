package com.data.structures.problems;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * HARD
 * @author Nelson Costa
 *
 */
public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeMaximumPathSum b = new BinaryTreeMaximumPathSum();
//		TreeNode t1 = b.new TreeNode(1);
//		TreeNode t2 = b.new TreeNode(2);
//		TreeNode t3 = b.new TreeNode(3);
//		t1.left = t2;
//		t1.right = t3;
		
//
//		TreeNode t1 = b.new TreeNode(2);
//		TreeNode t2 = b.new TreeNode(-1);
//		TreeNode t3 = b.new TreeNode(-2);
//		t1.left = t2;
//		t1.right = t3;
		
		TreeNode a1 = b.new TreeNode(-10);
		TreeNode a2 = b.new TreeNode(9);
		TreeNode a3 = b.new TreeNode(20);
		TreeNode a4 = b.new TreeNode(15);
		TreeNode a5 = b.new TreeNode(7);
		a1.left = a2;
		a1.right = a3;
		a3.left = a5;
		a3.right = a4;
		
		System.out.println(b.maxPathSum(a1));
	}

	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		
		TreeSet<Integer> sums = new TreeSet<>();

		sums.add(calcSums(root, sums));

		return sums.last();
	}

	/**		Runtime: 8 ms, faster than 5.49% of Java online submissions for Binary Tree Maximum Path Sum.
			Memory Usage: 41.1 MB, less than 35.71% of Java online submissions for Binary Tree Maximum Path Sum.


	 * @failed didnt understand why yet, write left instead of right node int the right variable
	 * failed again, maybe the node solely counts has a max path.
	 * it might be possible that is any path in the tree and not only a complete end to end, 
	 * it includes single node, it includes two nodes path, etc
	 * and it actually was that case (I miss interpreted the question)
	 * 
	 * @comments This was my first Hard problem, so I didn't try to make a efficient solution, 
	 * furthermore it was 3AM
	 * 
	 * @time  O(N)
	 * @space O(N)
	 * @param node
	 * @param sums
	 * @return
	 */
	private int calcSums(TreeNode node, TreeSet<Integer> sums)
	{
		if (node == null)
			return 0;
		
		int left = calcSums(node.left, sums);
		
		int right = calcSums(node.right, sums);
		
		sums.add(node.val + right + left);
		
		sums.add(node.val + Math.max(left, right));
		
		sums.add(node.val);
		
		return Math.max(node.val + Math.max(left, right), node.val);
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
