package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LowestCommonAncestorofaBinaryTree {

	static LowestCommonAncestorofaBinaryTree l = new LowestCommonAncestorofaBinaryTree();
	static LowestCommonAncestorofaBinaryTreeUnofficialSolution1 l2 = new LowestCommonAncestorofaBinaryTreeUnofficialSolution1();
	public static void main(String[] args) {

		//[3,5,1,6,2,0,8,null,null,7,4]
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(5);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(0);
		TreeNode n7 = new TreeNode(8);
		TreeNode n8 = new TreeNode(7);
		TreeNode n9 = new TreeNode(4);

		n1.left = n2;
		n1.right = n3;

		n2.left = n4;
		n2.right = n5;

		n5.left = n8;
		n5.right = n9;

		n3.left = n6;
		n3.right = n7;

		//System.out.println(l.lowestCommonAncestor(n1, n8, n4).val);
		System.out.println(l.lowestCommonAncestor(n1, new TreeNode(4), new TreeNode(5)).val);
		System.out.println(l2.lowestCommonAncestor(n1, new TreeNode(5), new TreeNode(6)).val);

	}


	TreeNode first = null;
	TreeNode second = null;
	TreeNode ans = null;
	Set<TreeNode> set;

	
	/**
	 * 
     * @score
     *    		Runtime: 7 ms, faster than 34.32% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     *  		Memory Usage: 41 MB, less than 91.40% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     *  
     * @fail
     *         1) nullPointer exception
     *         2) logic issue, caused by messing up with references, I was not changing p and q properly
     *  	   3) I was not returning properly when finding the second element
     *  	   4) I was not updating the answer after running the right side traversal
     *  	   5) Did't knew the common ancestor could be the first... this led to some approaches in the code which was not valid
     *      
     * @debug
     *       yes
     *  
     * @time   O(N)
     * @space  O(N)
     *      
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		// X test case one not found
		// X if both not found null
		// X test case if they are equal
		// X test case single node
        if (root == null)
            return null;
        
		//HashSet with nodes found along the way for the first node
		set = new HashSet<>();
		//Continue search until finding the second node

		//when second node found [BASE CASE]
		//return second and compare parent against set the first to match will be the cvommon ancestor
		searchBoth(root, p, q);
		return  ans == null ? first : ans;
	}

	private void searchBoth(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null)
			return;

		if (first == null)
		{        
			if (root.val == p.val)
			{
				first = p;
                set.add(root);
				//System.out.println("found-first: " + root.val);
			}
			else if (root.val == q.val)
			{
				first = q;
                set.add(root);
				//System.out.println("found-first: " + root.val);
			}
			else
			{
				set.add(root);
				//System.out.println("path-to-first: " + root.val);
			}
		}
		else if (second == null)
		{
			if (root.val == p.val)
			{
				second = p;
				//System.out.println("found-second: " + root.val);
				return;
			}

			else if (root.val == q.val)
			{
				second = q;
				//System.out.println("found-second: " + root.val);
				return;
			}
		}


		//calling left and calling right
		//Validating against parent

		searchBoth(root.left, p, q);

		if(canLevelUp(root))
			return;            

		searchBoth(root.right, p, q);  

		if(canLevelUp(root))
			return;       
	}

	private boolean canLevelUp(TreeNode root){

		if (ans != null)
			return true;

		//if current node is not first, and second is already found, we can check if it is common ancestor
		if (first != null && second != null && ans == null)
		{
			if (set.contains(root)){
				ans = root;
				return true;
			}
		}
		//if current node is equals to first it cannot be a common ancentor, so we need to check above
		else if (first != null && root.val == first.val && second != null)
		{
            System.out.println("tch");
			return true;
		}

		return false;
	}
}




/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Non Official Solution
 * 
	  This is the top solution
	  
	  
	  this solution is agressive in the sense it does not try to find both elements in all the tree.
	  
	  They try to find the first element and from there they dont go more down in that node, they explore the other part of the tree 
	  because if the second node is below the ancestor will be the first node, the remaining possibility is if the element is in another branch.
	  
	  This solution requires some kind of mental jump to understand that you don't need to explore the node any further
 * 
 * 
 * @timetook 4ms
 * 
 * @author Nelson Costa
 *
 */
class LowestCommonAncestorofaBinaryTreeUnofficialSolution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;
        
        //one of the nodes has been found. return it
        if(root.val==p.val || root.val==q.val)
            return root;
        
        //look for both nodes in both sub-trees
        TreeNode ancestorInLeftSubTree = lowestCommonAncestor(root.left, p, q);
        TreeNode ancestorInRightSubTree = lowestCommonAncestor(root.right, p, q);
        
        //if p & q were found in different sub-trees. I am the LCA
        if(ancestorInLeftSubTree!=null && ancestorInRightSubTree!=null)
            return root;
        
        //if an ancestor was found, return it. Else, return null. 
        return (ancestorInLeftSubTree!=null) ? ancestorInLeftSubTree : ancestorInRightSubTree;
    }
}



/**
 * very interesting solution based in mid, left, and right point, 
 * when two of this tree variables are true we set the ancestor
 * 
 * 
 * @author Nelson Costa
 *
 */
class LowestCommonAncestorofaBinaryTreeSolution1 {

    private TreeNode ans;

    public LowestCommonAncestorofaBinaryTreeSolution1() {
        // Variable to store LCA node.
        this.ans = null;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
}

/**
 *	@score
		Runtime: 9 ms, faster than 27.40% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
		Memory Usage: 40.3 MB, less than 95.30% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
		
		
	@intuition
		1) Traverse all tree and save for each node it's parent.
		2) save all the ancestors of p
		3) check all the ancestors of q and check the first that is common
		
		
 * @author Nelson Costa
 *
 */
class LowestCommonAncestorofaBinaryTreeSolution2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

}

/**
 * 
 * @score
 		Runtime: 17 ms, faster than 9.08% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
		Memory Usage: 45.8 MB, less than 6.05% of Java online submissions for Lowest Common Ancestor of a Binary Tree.

 * @author Nelson Costa
 *
 */
class LowestCommonAncestorofaBinaryTreeSolution3 {

    // Three static flags to keep track of post-order traversal.

    // Both left and right traversal pending for a node.
    // Indicates the nodes children are yet to be traversed.
    private static int BOTH_PENDING = 2;

    // Left traversal done.
    private static int LEFT_DONE = 1;

    // Both left and right traversal done for a node.
    // Indicates the node can be popped off the stack.
    private static int BOTH_DONE = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

        // Initialize the stack with the root node.
        stack.push(new Pair<TreeNode, Integer>(root, LowestCommonAncestorofaBinaryTreeSolution3.BOTH_PENDING));

        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;

        // This is used to keep track of the LCA.
        TreeNode LCA = null;

        // Child node
        TreeNode child_node = null;

        // We do a post order traversal of the binary tree using stack
        while (!stack.isEmpty()) {

            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parent_node = top.getKey();
            int parent_state = top.getValue();

            // If the parent_state is not equal to BOTH_DONE,
            // this means the parent_node can't be popped off yet.
            if (parent_state != LowestCommonAncestorofaBinaryTreeSolution3.BOTH_DONE) {

                // If both child traversals are pending
                if (parent_state == LowestCommonAncestorofaBinaryTreeSolution3.BOTH_PENDING) {

                    // Check if the current parent_node is either p or q.
                    if (parent_node == p || parent_node == q) {

                        // If one_node_found was set already, this means we have found
                        // both the nodes.
                        if (one_node_found) {
                            return LCA;
                        } else {
                            // Otherwise, set one_node_found to True,
                            // to mark one of p and q is found.
                            one_node_found = true;

                            // Save the current top element of stack as the LCA.
                            LCA = stack.peek().getKey();
                        }
                    }

                    // If both pending, traverse the left child first
                    child_node = parent_node.left;
                } else {
                    // traverse right child
                    child_node = parent_node.right;
                }

                // Update the node state at the top of the stack
                // Since we have visited one more child.
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

                // Add the child node to the stack for traversal.
                if (child_node != null) {
                    stack.push(new Pair<TreeNode, Integer>(child_node, LowestCommonAncestorofaBinaryTreeSolution3.BOTH_PENDING));
                }
            } else {

                // If the parent_state of the node is both done,
                // the top node could be popped off the stack.
                // Update the LCA node to be the next top node.
                if (LCA == stack.pop().getKey() && one_node_found) {
                    LCA = stack.peek().getKey();
                }

            }
        }

        return null;
    }
}