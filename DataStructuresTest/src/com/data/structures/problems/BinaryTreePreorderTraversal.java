package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Apparantly easy question
 * 144
 * MEDIUM
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * @author Nelson Costa
 *
 */
public class BinaryTreePreorderTraversal {

	public static void main(String[] args) {
		BinaryTreePreorderTraversal b = new BinaryTreePreorderTraversal();
		TreeNode root = b.new TreeNode(1);
		TreeNode n2 = b.new TreeNode(2);
		TreeNode n3 = b.new TreeNode(3);
		TreeNode n4 = b.new TreeNode(4);
		root.right = n2;
		n2.left = n3;
		root.left = n4;
		
		System.out.println(b.preorderTraversal(root));

	}

	/**
	 * @timeComplexity  O(N)
	 * @spaceComplexity O(2^H) + O(N) = O(2^H), I think so. Where H is the Tree Height
	 * @param node
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode node) {
		List<Integer> list = new ArrayList<Integer>();

		if (node == null)
		{
			return list;
		}
		
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.add(node);
		
		while(!s.isEmpty()) {
			node = s.peek();
			
			if (node != null) 
			{
				list.add(node.val);
				s.pop();
				s.push(node.right);
				s.push(node.left);
			}
			else
			{
				s.pop();
			}
		}
		return list;
	}

	//Nested class
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}

