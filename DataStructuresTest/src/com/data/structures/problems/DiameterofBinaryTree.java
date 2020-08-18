package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;
import com.data.structures.utils.LeetcodeUtils;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * EASY
 * @author Nelson Costa
 *
 */
public class DiameterofBinaryTree extends LeetCodeExercise{
	static DiameterofBinaryTree d = new DiameterofBinaryTree();
	public static void main(String[] args) {
		TreeNode t = LeetcodeUtils.Tree.stringToTreeNode("[1,2,3,4,5]");
		System.out.println(d.diameterOfBinaryTree(t));
	}

    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        
        if (root == null)
            return 0;
        solve(root);
        return max;
    }
    
    /**
     * 
     * @intuition
     * 		This is equivalent to sum the depth of the left side  plus the right side.
     * 		
     * 
     * @solve
     * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
	 * 		Memory Usage: 39.2 MB, less than 88.28% of Java online submissions for Diameter of Binary Tree.
	 * 
	 * @fail
	 * 		1) forgot to return the max element instead of the max variable
	 * 		2) massive distraction caused by changing the code from the main function to auxiliar function... god damn
	 * 	
	 * @time
	 * 		O(N)
	 * 
	 * @space 
	 * 		O(N)
	 * 
	 * 	
     * @param root
     * @return
     */
    private int solve(TreeNode root){
        
        //
        //This is equals to the depth of the right side plus the left side, for each node?
        //return the max of them
            
        if(root == null)
            return 0;
        
        
        int left = solve(root.left);
        int right = solve(root.right);       
        
        max = Math.max(max, left+right);
        
        return Math.max(left, right) + 1;
        
    }
}
