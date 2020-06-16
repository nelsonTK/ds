package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.TreeNode;

public class BinaryTreeRightSideView {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @intuition
	 * 		firstly, the problem is to always get the rightmost node regardless of the tree branch
	 * 		nothing fancy was applied, just a stack  and bfs...
	 * 		removing adding the leftmost element first and adding the rightmost second
	 * 
	 * 		for instance:
	 * 
			  			3
			  
			  		5		6
			 	
			  	7
			  
			  	Resultado: [3, 6, 7]
			  	
			  	
	 * 
		@score
		    Runtime: 3 ms, faster than 5.39% of Java online submissions for Binary Tree Right Side View.
		    Memory Usage: 39.6 MB, less than 11.77% of Java online submissions for Binary Tree Right Side View.
	 * 
		@time   O(N)
		@space  O(2^Log2N) or O(D) Where D is the diameter but the worst case is O(N)
		
	 *  @param root
	 *  @return
	 */
	    public List<Integer> rightSideView(TreeNode root) {
	        
	        //guards root no right
	        if(root == null)
	            return new ArrayList<Integer>();
	        
	        
	        List<Integer> ans = new ArrayList<>();
	        Stack<Pair> q = new Stack<>();        
	        q.add(new Pair(root, 0));
	        
	        Pair pair;
	        TreeNode cur;
	        int prevLevel = -1;
	        
	        while (!q.isEmpty())
	        {
	            pair = q.pop();
	            cur = pair.node;
	            
	            if (prevLevel < pair.level)
	            {
	                ans.add(cur.val);
	                prevLevel++;
	            }
	            
	            if (cur.left != null)
	                q.add(new Pair(cur.left, pair.level + 1));
	            
	            if (cur.right != null)
	                q.add(new Pair(cur.right, pair.level + 1));
	        }
	        
	        return ans;
	    }
	

	class Pair{
	    TreeNode node;
	    int level;
	    
	    public Pair(TreeNode node, int level){
	        this.node = node;
	        this.level = level;
	    }
	}
}


/**
 * DFS Solution
 * 
 * performs pre order traversal and compares if the level is the highest
 * 
 * this is the official top solution
 * 
 * @score
	Runtime: 1 ms, faster than 78.72% of Java online submissions for Binary Tree Right Side View.
	Memory Usage: 39.9 MB, less than 6.48% of Java online submissions for Binary Tree Right Side View.
 * 
 * @author Nelson Costa
 *
 */
class BinaryTreeRightSideViewSolution1 {
    List<Integer> rightside = new ArrayList();
    
    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) 
            rightside.add(node.val);
        
        if (node.right != null) 
            helper(node.right, level + 1);  
        if (node.left != null) 
            helper(node.left, level + 1);
    }    
    
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return rightside;
        
        helper(root, 0);
        return rightside;
    }
}