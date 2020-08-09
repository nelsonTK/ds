package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class AllNodesDistanceKinBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	HashMap<TreeNode, TreeNode> childToParent = new HashMap<>();
	List<Integer> ans = new ArrayList<>();
	TreeNode root;

	/**
	 * @intuition
	 * 		I break this problem in 3 parts.
	 * 
	 * 		1) find the target element
	 * 
	 * 		2) find all the elements down
	 * 
	 * 		3) find all elements up
	 * 			which might include exploring up and down
	 * 		
	 * 		we have to keep track of the elements parents so that we can go up.
	 * 
	 * 		I do that tracking when going down only.
	 * 
	 * 		because when we need to go up we already have the parent map.
	 * 		
	 * 
	 * @comments
	 * 		I see a lot of code for this problem...
	 * 		
	 * @alternatives
	 * 		I could have just use a classical breathfirst search where I do a first traversal and map all elements to an hashmap.
	 * 
	 * 		And then I would do breath first search on the elements, I would enqueue always the children and the parent if they are not already processed.
	 * 
	 * 
	 * @score
	 *		Runtime: 10 ms, faster than 93.50% of Java online submissions for All Nodes Distance K in Binary Tree.
	 *		Memory Usage: 40 MB, less than 5.00% of Java online submissions for All Nodes Distance K in Binary Tree.
	 * 
	 * 
	 ** @fail    
	 *		1) there was a case that I was not taking into acount, I should have tried a nunber of tree formats
	 *
	 *	@time
	 *		O(N)
	 *
	 *	@space
	 *		O(N)		
	 **/
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

		//going up for root element?


		//search for target
		TreeNode searchTarget = search(root, target, null);
		if (searchTarget == null)
			return new ArrayList<Integer>();

		//seach down O(N)
		goDown(searchTarget, K);
		//search up


		//odd and even k
		//searchUP O(N)
		if(root != searchTarget)
			goUp(searchTarget, childToParent.get(searchTarget), K - 1);

		return ans;
	}


	private TreeNode search(TreeNode node, TreeNode target, TreeNode parent){

		if (node == null)
		{
			return null;
		}

		childToParent.put(node, parent);

		if(node == target)
		{
			return node;
		}

		TreeNode searchResult = search(node.left, target, node);

		if (searchResult !=null)
			return searchResult;

		searchResult = search(node.right, target, node);

		if (searchResult != null)
			return searchResult;

		return null;
	}

	
	private void goDown(TreeNode node, int K)
	{

		if (node == null)
		{
			return;
		}

		if(K == 0)
		{
			ans.add(node.val);
			return;
		}

		goDown(node.left, K-1);
		goDown(node.right, K-1);

	}


	private boolean goUp(TreeNode node, TreeNode parent,  int K)
	{

		if (parent == null)
		{
			return false;
		}

		if(K == 0)
		{
			ans.add(parent.val);
			return true;
		}

		goUp(parent, childToParent.get(parent), --K);

		//explore the node that is not equals to the previous
		if (parent.left == node)
			goDown(parent.right, K);
		else
			goDown(parent.left, K);

		return true;
	}


}
