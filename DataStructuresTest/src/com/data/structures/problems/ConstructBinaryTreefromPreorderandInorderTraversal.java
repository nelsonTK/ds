package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal extends LeetCodeExercise{

	static ConstructBinaryTreefromPreorderandInorderTraversal c = new ConstructBinaryTreefromPreorderandInorderTraversal();
	public static void main(String[] args) {
		int [] pre = stringToArray("[3,9,20,15,7]");
		int [] ino = stringToArray("[9,3,15,20,7]");
		
		c.buildTree(pre, ino);
	}
	
	/**
	 * @intuition
	 * 		
	 * 
	 * 
	 * @score
	 * 		Runtime: 331 ms, faster than 5.33% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
	 *		Memory Usage: 40.9 MB, less than 6.90% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
	 * 
	 * @fail
	 * 		1) I did not have the right Root well calculated
	 * 		
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param pre
	 * @param ino
	 * @return
	 */
    public TreeNode buildTree(int[] pre, int[] ino) {
        
        //create mapping for preorder entries and inorder
        
        //left, right,  rootindex(pre order), preorder, inorder array
        
        //new root with preorder[rootindex]
        
        //find end of left side which is equals to rootindex in inorder array -1 (), e tenho como saber o range, é o max e o min correspondentes no ino
            //so left is from 0 to root - 1
            
            //root.left = dfstree rootindex+1, pre, ino  
            //or null
        
        //find the right side which is left +1 to the end
        //or null
        
        //e continuar recursivamente
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ino.length; i++)
        {
            map.put(ino[i], i);
        }
        /*************
        
        
        ESTAVA ALTERAR A ASSINATURA DO METODO PARA ELIMINAR O preleft pois só uso o preRootIndex
        DEPOIS ERA PARA FAZER DRY RUN
        
        
        ************/
        return solve(0, pre.length - 1, 0, pre.length - 1, pre, ino, map);
        
        
    }
    
    private TreeNode solve(int inoleft, int inoright, int preRootIndex, int preright, int [] pre, int [] ino, HashMap<Integer, Integer> map)
    {
        //out of bounds control
        if (preRootIndex >= pre.length || preRootIndex > preright)
        {
            System.out.println("test " + preRootIndex);
            return null;
        }
        
        TreeNode root = new TreeNode(pre[preRootIndex]);
        System.out.println(pre[preRootIndex]);
        
        //find left children
        Integer max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int i = preRootIndex + 1;
        while (i < pre.length && map.get(pre[i]) < map.get(pre[preRootIndex])
              && map.get(pre[i]) <= inoright && map.get(pre[i]) >= inoleft)
        {
            max = Math.max(max, map.get(pre[i]));
            min = Math.min(min, map.get(pre[i]));
            i++;
        }
        
        if(max != Integer.MIN_VALUE)
        {
            root.left = solve(min, max, preRootIndex + 1, i - 1, pre, ino, map);
        }
        
        //i has the next element to left, so arguably, the first right
        max = Integer.MIN_VALUE; min = Integer.MAX_VALUE;
        int rightRoot = i;
        while (i < pre.length && map.get(pre[i]) > map.get(pre[preRootIndex])
              && map.get(pre[i]) <= inoright && map.get(pre[i]) >= inoleft)
        {
            max = Math.max(max, map.get(pre[i]));
            min = Math.min(min, map.get(pre[i]));
            i++;
        }
        
        if (max != Integer.MIN_VALUE)
        {
            root.right = solve(min, max, rightRoot, preright, pre, ino, map);
        }
        
        return root;
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
Top solution 
class UnofficialSolution {
	   int pos[] = new int[]{0,0};
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	        return buildTree(preorder, inorder, null);
	    } 

	    public TreeNode buildTree(int[] preorder,int[] inorder, TreeNode parent) {
	        if (pos[0] == preorder.length){
	            return null;
	        }
	        TreeNode cur = new TreeNode(preorder[pos[0]++]);
	        
	        // Go to left side
	        // if it's not left most node, continue to build its left child
	        if (cur.val != inorder[pos[1]]){ 
	            cur.left = buildTree(preorder, inorder, cur);
	        }
	        // After reach the left most node, move to next left most node
	        pos[1]++;
			// if new left most node is the parent, it means cur don't have right child.
			// ortherwise, find its right child
	        if (parent == null || parent.val != inorder[pos[1]]){
	            cur.right = buildTree(preorder, inorder, parent);
	        }
	        return cur;
	    }
	}
