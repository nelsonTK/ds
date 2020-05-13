package com.data.structures.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal extends LeetCodeExercise{

	static BinaryTreeZigzagLevelOrderTraversal b = new BinaryTreeZigzagLevelOrderTraversal();
	
	public static void main(String[] args) {
/*
	    3
	    / \
	   9  20
	     /  \
	    15   7
	    */

		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		
		for (List<Integer> list : b.zigzagLevelOrder(n1)) {
			printArray(list.toArray());
		}
		
	}

	/**
	 * @intuition
	 * 		using stack and switching which node i see first. for odd levels of the tree is left and right.
			for even levels of the tree is right and left.
	 * 
	   @score
	   		Runtime: 1 ms, faster than 71.26% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
			Memory Usage: 39.3 MB, less than 5.77% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
	 * 
	 * @alternatives
	 * 		at the moment I see no alternatives or optimizations
	 * 
	 * @fail
	 * 		1) I had pop left instead of pop curStack
	 * 		2) forgot to add ansRow to the answer
	 * 
	 * @time  O(N)
	 * @space O(N)
	 * @bcr	  O(N)
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

		if (root == null)
			return new ArrayList<List<Integer>>();

		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		List<Integer> ansRow = new ArrayList<>();
		ans.add(ansRow);
		
		TreeNode node;
		Stack<TreeNode> left = new Stack<>(), right = new Stack<>(), curStack = left;
		left.add(root);

		boolean isLeft = true;

		while (!curStack.isEmpty())
		{
			node = curStack.pop();
			ansRow.add(node.val);

			if (isLeft)
			{
				if(node.left != null)
					right.add(node.left);
				
				if(node.right != null)
					right.add(node.right);
				
				if (left.isEmpty())
				{
					curStack = right;
					isLeft = false;
					
					if (!right.isEmpty())
					{
						ansRow =  new ArrayList<Integer>();
						ans.add(ansRow);
					}
				}
			}
			else
			{

				if(node.right!= null)
					left.add(node.right);
				
				if(node.left != null)
					left.add(node.left);
				
				if(right.isEmpty())
				{
					curStack = left;
					isLeft = true;
					
					if (!left.isEmpty())
					{
						ansRow = new ArrayList<Integer>();
						ans.add(ansRow);
					}
				}
			}
		}

		return ans;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Solution with bfs and queues, the author uses a queue as a stack by adding elements at the front, 
 * and uses null to indicate the end of the level
 * I like this solution
 * 
 * O(N)
 * O(N)
 * 
 * @author Nelson Costa
 *
 */
class BinaryTreeZigzagLevelOrderTraversalSolution1 {
	  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	    if (root == null) {
	      return new ArrayList<List<Integer>>();
	    }

	    List<List<Integer>> results = new ArrayList<List<Integer>>();

	    // add the root element with a delimiter to kick off the BFS loop
	    LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
	    node_queue.addLast(root);
	    node_queue.addLast(null);

	    LinkedList<Integer> level_list = new LinkedList<Integer>();
	    boolean is_order_left = true;

	    while (node_queue.size() > 0) {
	      TreeNode curr_node = node_queue.pollFirst();
	      if (curr_node != null) {
	        if (is_order_left)
	          level_list.addLast(curr_node.val);
	        else
	          level_list.addFirst(curr_node.val);

	        if (curr_node.left != null)
	          node_queue.addLast(curr_node.left);
	        if (curr_node.right != null)
	          node_queue.addLast(curr_node.right);

	      } else {
	        // we finish the scan of one level
	        results.add(level_list);
	        level_list = new LinkedList<Integer>();
	        // prepare for the next level
	        if (node_queue.size() > 0)
	          node_queue.addLast(null);
	        is_order_left = !is_order_left;
	      }
	    }
	    return results;
	  }
	}

/**
 * the author uses dfs to explore the tree and uses the levels to determine if it should be added and the beginning or end.
 * Its a cool thought.
 * 
 * @t O(N)
 * @s O(H)
 * 
 * 
 * @author Nelson Costa
 *
 */
class BinaryTreeZigzagLevelOrderTraversalSolution2 {
	  protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
	    if (level >= results.size()) {
	      LinkedList<Integer> newLevel = new LinkedList<Integer>();
	      newLevel.add(node.val);
	      results.add(newLevel);
	    } else {
	      if (level % 2 == 0)
	        results.get(level).add(node.val);
	      else
	        results.get(level).add(0, node.val);
	    }

	    if (node.left != null) DFS(node.left, level + 1, results);
	    if (node.right != null) DFS(node.right, level + 1, results);
	  }

	  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	    if (root == null) {
	      return new ArrayList<List<Integer>>();
	    }
	    List<List<Integer>> results = new ArrayList<List<Integer>>();
	    DFS(root, 0, results);
	    return results;
	  }
	}