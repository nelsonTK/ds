package com.data.structures.problems;

import com.data.structures.problems.ds.TreeNode;

public class SubtreeOfAnotherTree {

	public static void main(String[] args) {

		SubtreeOfAnotherTree s = new SubtreeOfAnotherTree();
		//Main Tree
		/*
					 3
				    / \
				   4   5
				  / \
				 1   2
				 
		*/
		TreeNode s1 = new TreeNode(3);
		TreeNode s2 = new TreeNode(4);
		TreeNode s3 = new TreeNode(5);
		TreeNode s4 = new TreeNode(1);
		TreeNode s5 = new TreeNode(2);
		
		s1.left = s2;
		s1.right = s3;
		s2.left = s4;
		s2.right = s5;
		
		//SubTree
		TreeNode t1 = new TreeNode(4);
		TreeNode t2 = new TreeNode(1);
		TreeNode t3 = new TreeNode(2);
		t1.left = t2;
		t1.right = t3;

		System.out.println(s.isSubtree(s1, t1));

		
		System.out.println("New Test Case");
		TreeNode x1 = new TreeNode(1);
		TreeNode x2 = new TreeNode(1);
		x1.left = x2;
		
		TreeNode y1 = new TreeNode(1);
		System.out.println(s.isSubtree(x1, y1));

	}

	/**
	 * 
	 * 		Runtime: 5 ms, faster than 96.83% of Java online submissions for Subtree of Another Tree.
			Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Subtree of Another Tree.
	 * 
	 * @fail - null pointer exception, forgot to put a clause to protect the cases where we are testing equality but one of the elements is null (not the root of the problem though)
	 * the other erros was that I should have mantain the t variable while searching
	 * voltei a falhar porque assumi que não havia valores repetidos na arvore
	 * mistake in defining space complexity and time complexity
	 * 
	 * @time O(N * M) worst cases are full size subtree and not found which results in traversal of all nodes
	 * @space O(N) no extra space used but the recursion
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {

		if (s == null || t == null)
		{
			throw new IllegalArgumentException("Invalid parameters");
		}
		
		return hasSubtree(s, t);
	}
	
	

	private boolean hasSubtree(TreeNode s, TreeNode t) {
		//end of the brach
		if (s == null)
			return false;
		
		if (s.val == t.val)
		{
			if( equals(s, t)) {
				return true;
			}
		}
		
		return hasSubtree(s.left, t) || hasSubtree(s.right, t);
	}



	private boolean equals(TreeNode s, TreeNode t) {
		//and of the branch than this branch is equals
		if (s == null && t == null)
			return true;
		
		//this branch is not equals
		//the verification is in case of one is null and the other doesn't
		if (s == null && t != null || t == null && s != null || s.val != t.val)
		{
			return false;
		}
		
		return equals(s.left, t.left) && equals(s.right, t.right);
	}
}
