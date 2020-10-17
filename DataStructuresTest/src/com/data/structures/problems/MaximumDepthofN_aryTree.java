package com.data.structures.problems;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * EASY
 * @author Nelson Costa
 *
 */
public class MaximumDepthofN_aryTree {

	

	 /*********************************
	 * SOLUTION 1
	 ********************************/	

	/**
	 * @intuition
	 * 		Just a simple recursion exercise
	 * 
	 * @score 
	 *		Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of N-ary Tree.
	 *		Memory Usage: 38.9 MB, less than 12.66% of Java online submissions for Maximum Depth of N-ary Tree.
	 *
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 *
	 *
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    int max;
	    public int maxDepth(Node root) {
	        
	        if (root == null)
	            return 0;
	        
	        max = 0;
	        
	        findMax(root, 1);
	        
	        return max;
	    }
	    
	    private void findMax(Node root, int level)
	    {
	        
	        if (max < level)
	            max = level;
	    
	        for(Node child : root.children)
	        {
	            findMax(child, level+1);
	        }
	    }
	}
	

	// Definition for a Node.
	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	};
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Iterative Solution
 * @author Nelson Costa
 *
 */
class MaximumDepthofN_aryTreeSolution2 {
	  public int maxDepth(Node root) {
	    Queue<Pair<Node, Integer>> stack = new LinkedList<>();
	    if (root != null) {
	      stack.add(new Pair(root, 1));
	    }

	    int depth = 0;
	    while (!stack.isEmpty()) {
	      Pair<Node, Integer> current = stack.poll();
	      root = current.getKey();
	      int current_depth = current.getValue();
	      if (root != null) {
	        depth = Math.max(depth, current_depth);
	        for (Node c : root.children) {
	          stack.add(new Pair(c, current_depth + 1));    
	        }
	      }
	    }
	    return depth;
	  }

		// Definition for a Node.
		class Node {
		    public int val;
		    public List<Node> children;

		    public Node() {}

		    public Node(int _val) {
		        val = _val;
		    }

		    public Node(int _val, List<Node> _children) {
		        val = _val;
		        children = _children;
		    }
		};
	}
