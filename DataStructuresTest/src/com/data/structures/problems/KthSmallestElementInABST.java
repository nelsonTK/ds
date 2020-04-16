package com.data.structures.problems;

import java.util.Stack;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class KthSmallestElementInABST {

	public static void main(String[] args) {

		KthSmallestElementInABST k = new KthSmallestElementInABST();
		int kth = 3;

		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(1);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(2);

		n1.left = n2;
		n1.right = n3;
		n2.right = n4;

		System.out.println(k.kthSmallest(n1, kth));

	}

	/**
	 * 
	 * 
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
Memory Usage: 39.3 MB, less than 13.76% of Java online submissions for Kth Smallest Element in a BST.
	 * 
	 * 
	 * @failed nullpointer exception, because I was not returning the value at the root, 
	 * this was a result of a change that i've made and didn'tchecked all conditions again
	 * 
	 * @time O(H + k) height + k elements (go down to the tree than do k elements
	 * @space O(N)
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest0(TreeNode root, int k) {

		Stack<Integer> s = new Stack<Integer>();

		if (root == null || k < 1)
			throw new IllegalArgumentException();

		return checkKth(root, s, k);
	}

	private Integer checkKth(TreeNode node, Stack<Integer> s, int k) {

		if (node == null)
		{
			return null;
		}

		checkKth(node.left, s, k);

		if (s.size() == k - 1)
		{
			s.add(node.val);
			return s.peek();
		}
		else if( s.size() == k) // I need this because of the nodes after the hit
		{
			return s.peek();
		}

		s.add(node.val);

		return checkKth(node.right, s, k);
	}

	int kthSmallest = 0;
	int k = 0;

	/**
	 *  my optimization will be not use a stack (second part of the question)
	 *  
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
			Memory Usage: 39.3 MB, less than 16.52% of Java online submissions for Kth Smallest Element in a BST. 
	 *  
	 *  
	 * @failed didn't think about where to put the k subtraction
	 * forgot k is primitive
	 *  
	 * @time  O(H + K) height and k elements. go down h and then search k elements
	 * @space O(N) 
	 *  
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {

		if (root == null || k < 1)
			throw new IllegalArgumentException();

		this.k = k;
		
		calcSmallest(root);
		
		return kthSmallest;
	}


	private void calcSmallest(TreeNode node) {
		
		if (node == null)
			return;

		calcSmallest(node.left);
		
		k--;

		if (k == 0)
		{
			kthSmallest = node.val;
			return;
		}
		else if (k < 0) 
		{
			return;
		}


		calcSmallest(node.right);

	}

}
