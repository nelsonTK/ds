package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class BinaryTreeInorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    /** 
     * @intuition 
     * 		Put root element in the stack
     * 		and perform a loop while the stack is not empty.
     * 
     * 		LEFT
     * 			whe on the loop we peek the stack and then watch if it has left element, 
     * 			if it has add it to the stack and nullify the left element in the current node
     * 			and we continue
     * 
     * 			if it has not left element then we continue and arr its element to the answer list
     * 			
     * 
     * 		MIDDLE
     * 			after checking left side, we add the current element to the answer list and remove the curr from the stack
     * 			Then we check the right side
     * 
     * 
     * 		RIGHT
     * 			if it has right element, we add the element to the stack, and that's it.
     * 			We don't need to nullify the right side
     * 			
     * @score
     * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
	 *		Memory Usage: 37.8 MB, less than 66.50% of Java online submissions for Binary Tree Inorder Traversal.
	 *
     * @time O(N) 
     * 
     * @space O(N) avgO(H)
    **/
    public List<Integer> inorderTraversal(TreeNode root) {
       
        if (root == null)
            return new ArrayList<>();
        
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode cur;
        
        while(!s.isEmpty())
        {
            cur = s.peek();
            
            if(cur.left != null)
            {
                s.push(cur.left);
                cur.left = null;
                continue;
                
            }
            
            ans.add(cur.val);
            s.pop();
            
            if(cur.right != null)
            {
                s.push(cur.right);
            }            
        }
        
        return ans;        
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Morris traversal
 * 
 * @time 
 * 		O(N) 
 * @space
 * 		O(1) excluding answer
 * 		O(N) including answer
 * @author Nelson Costa
 *
 */
class BinaryTreeInorderTraversalSolution3 {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}