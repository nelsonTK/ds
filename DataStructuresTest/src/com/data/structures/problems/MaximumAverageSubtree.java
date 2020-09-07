package com.data.structures.problems;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/maximum-average-subtree
 * MED
 * @author Nelson Costa
 *
 */
public class MaximumAverageSubtree {

	public static void main(String[] args) {

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
    double max;
    Pair empty = new Pair();
    /**
     * @intuition
     *  	The intuition is to calculate the average for each subtree in the tree, and the answer will bubble in the root node.
     *  
     * @score 
     *  
     * @fail
     * 		1) Forgot about decimal part casting 
     * 
     * 		2) missinterpreter the question very badly, when they said subtree, it is always the subtree. you cannot skip it and test with only the curent node.
     * 			so you have to always count the children nodes, and get the count of them and the sum
     * 
     * @time O(N) 
     * 
     * @space O(N)
    **/
     public double maximumAverageSubtree(TreeNode root) {
         max = 0;
         avg(root);
         return max;
     }
    
    private Pair avg(TreeNode node)
    {
        if (node == null)
            return empty;
        
        
        Pair left = avg(node.left);
        Pair right = avg(node.right);        
        Pair cur = new Pair(node.val + left.sum + right.sum, left.num + right.num + 1);
        
        max = Math.max(max, cur.sum/(double)cur.num);
        
        return cur;
    }
    
    
    class Pair{
        int sum;
        int num;
        
        Pair (int s, int n)
        {
            sum = s;
            num = n;
        }
        Pair ()
        {
            sum = 0;
            num = 0;
        }
        
    }
}
