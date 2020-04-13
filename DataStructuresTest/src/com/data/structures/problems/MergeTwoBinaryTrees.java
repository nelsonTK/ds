package com.data.structures.problems;

import com.data.structures.problems.MergeTwoBinaryTrees.TreeNode;

public class MergeTwoBinaryTrees {

	public static void main(String[] args) {

		MergeTwoBinaryTrees m = new MergeTwoBinaryTrees();
		TreeNode t1 = m.new TreeNode(1);
		TreeNode t2 = m.new TreeNode(2);
		TreeNode t3 = m.new TreeNode(3);
		TreeNode a1 = m.new TreeNode(2);
		TreeNode a2 = m.new TreeNode(1);
		TreeNode a3 = m.new TreeNode(3);
		TreeNode a4 = m.new TreeNode(4);
		t1.left = t2;
		t1.right = t3;
		a1.left = a2;
		a1.right = a3;
		a3.right = a4;

		System.out.println("tree1");
		m.printTree(a1);
		System.out.println("tree2");
		m.printTree(t1);		
		System.out.println("merge tree");
		m.printTree(m.mergeTrees(t1, a1));
	}

	/**
	 * 		Runtime: 1 ms, faster than 25.13% of Java online submissions for Merge Two Binary Trees.
			Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Merge Two Binary Trees.
	 * 
	 * I though a new tree had to be retrieved the the solutions tell otherwise
	 * 
	 * @time O(m) minimal number of nodes that need to be traversed which is the number of the smallest tree	
	 * @space worst case O(m) (skewed tree)average case (log m)
	 * @param t1
	 * @param t2
	 * @return
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		if (t1 == null && t2 == null)
		{
			return null;
		}

		TreeNode tNew = null;

		if (t1 != null && t2 != null)
		{
			tNew = new TreeNode(t1.val + t2.val);
		}
		else if (t1 == null)
		{
			tNew = new TreeNode(t2.val);
		}
		else if(t2 == null)
		{
			tNew = new TreeNode(t1.val);
		}

		tNew.left  =  mergeTrees(t1 == null? t1 : t1.left, t2 == null ? t2 : t2.left);
		tNew.right =  mergeTrees(t1 == null? t1 : t1.right, t2 == null ? t2 : t2.right); 

		return tNew;
	}

	public void printTree(TreeNode node) {

		if (node == null)
			return;

		System.out.println(node.val);

		printTree(node.left);

		printTree(node.right);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}

/**
 * smart solution that reduces the if checks 
 * very optimized
 * @author Nelson Costa
 *
 */
class MergeTwoBinaryTreesSolution {
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}

}
