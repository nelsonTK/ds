package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.TreeNode;

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
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		root.right = n2;
		n2.left = n3;
		root.left = n4;
		
		System.out.println(b.preorderTraversal(root));

	}

	/**
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Preorder Traversal.
			Memory Usage: 37.6 MB, less than 5.17% of Java online submissions for Binary Tree Preorder Traversal.


	 * @time  O(N)
	 * @space O(2^H) + O(N) = O(2^H), I think so. Where H is the Tree Height
	 * 
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
}

