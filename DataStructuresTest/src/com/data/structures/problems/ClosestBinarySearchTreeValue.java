package com.data.structures.problems;

public class ClosestBinarySearchTreeValue {

	public static void main(String[] args) {

		ClosestBinarySearchTreeValue c = new ClosestBinarySearchTreeValue();
		TreeNode t1 = c.new TreeNode(4);
		TreeNode t2 = c.new TreeNode(2);
		TreeNode t3 = c.new TreeNode(5);
		TreeNode t4 = c.new TreeNode(0);
		TreeNode t5 = c.new TreeNode(3);

		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		TreeNode t6 = c.new TreeNode(1500000000);
		TreeNode t7 = c.new TreeNode(1400000000);
		t6.left = t7;

		double target = -1500000000;
		System.out.println(c.closestValue(t6, target));
	}

	private int closest;
	private double smallestDifference = Double.MAX_VALUE;

	/**
	 * 
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Closest Binary Search Tree Value.
Memory Usage: 39.1 MB, less than 5.13% of Java online submissions for Closest Binary Search Tree Value.


	 * @failed the signs of greater and lower than were on contrary
	 * failed again because the differences calc was still preparated for a absolute value instead of both negative and positive
	 * failed again, realized I need to go until the end of the branch
	 * failed again I change many parts of the solution and things werent okay, 
	 * failed again, after missing I failed to add module
	 * I think the problems started in my inability of seeing all edge cases
	 * failed again, int boundaries so, no attention to how to deal with big numbers
	 * 
	 * A lot of difficulties going on because of all the edge cases
	 * @time  O(log n)
	 * @space O(log n)
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue1(TreeNode root, double target) {		

		findClosest1(root, target);

		return closest;
	}

	private void findClosest1(TreeNode node, double target) {

		if(node == null) { return; }

		double currDiff = node.val - target;

		if (Math.abs(currDiff) <= Math.abs(smallestDifference))
		{
			closest = node.val;
			smallestDifference = currDiff;
		}	

		if (currDiff < 0)
		{
			findClosest1(node.right, target);
		}
		else if (currDiff > 0)
		{
			findClosest1(node.left, target);
		}
		//else currDiff == 0 will be returned
	}


	/**
	 * Increased performance
	 * @time  O(H)
	 * @space O(1)
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue(TreeNode root, double target) {

		int closest = root.val;
		int val;
		while (root != null)
		{
			val = root.val;

			closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;

			root = val > target ? root.left : root.right; 

		}

		return closest;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}


class ClosestBinarySearchTreeValueSolution {
	/**
	 * Very cleaver solution binary search applied to the bst tree iteratively...
	 * this was a must, and solves all the constraints regarding the upper limits and so forth
	 * 
	 * 
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue(TreeNode root, double target) {
		int val, closest = root.val;
		while (root != null) {
			val = root.val;
			closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
			root =  target < root.val ? root.left : root.right;
		}
		return closest;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}