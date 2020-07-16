package com.data.structures.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PopulatingNextRightPointersinEachNode {

	static PopulatingNextRightPointersinEachNode p = new PopulatingNextRightPointersinEachNode();
	public static void main(String[] args) {

		Node n1 = p.new Node(1);
		Node n2 = p.new Node(2);
		Node n3 = p.new Node(3);
		Node n4 = p.new Node(4);
		Node n5 = p.new Node(5);
		Node n6 = p.new Node(6);
		Node n7 = p.new Node(7);

		n1.left = n2;
		n1.right = n3;

		n2.left = n4;
		n2.right = n5;

		n3.left = n6;
		n3.right = n7;
		
		p.connect(n1);
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @score
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node.
	 *	Memory Usage: 39.6 MB, less than 76.53% of Java online submissions for Populating Next Right Pointers in Each Node.	 
	 * 
	 *  @fail
     *    1) null pointer exception
     *    2) forgot to reset the cur node
     *    
     *  
     * @time	O() ???
     * @space	O(1) / O(N)
	 */
	public Node connect(Node root) {

		//Exceptions/guards
		//size 1
		//null
		if (root == null)
			return null;

		if(root.left == null)
			return root;        

		dfs(root);        

		return root;

	}

	private void dfs(Node node)
	{
		if (node == null)
		{
			return;
		}

		Node cur = node;
		int i=0;

		
		while (cur != null)
		{
			cur = node; 
			cur = getLeft(cur.right, i);
			if (cur != null)
				setLinkToRight(node.left, cur, i);
			i++;
		}

		dfs(node.right);
		dfs(node.left);

	}

	//get left most
	private Node getLeft(Node n, int level){
		if (level <= 0)
			return n;

		if (n == null)
			return n;

		return getLeft(n.left, level - 1);
	}

	//set link to target
	private void setLinkToRight(Node n, Node nLink, int level)
	{
		if (n == null)
			return;

		if (level == 0)
		{
			n.next = nLink;
			return;
		}

		setLinkToRight(n.right, nLink, level - 1);
	}

	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Optimal solution
 * 
 * Very clearly engineered
 * 
 * The intuition is that 
 * 		1) we start at the leftmost node
 * 		2) we set the next node in the level before
 * 		3) we process level by level, which means we always move to the next node

			-		  1
			-	  2		  3
			-	4	5	6	7
			
		4) there are two ways of binding, 
			1) one for the direct children, 
				E.g. 4 with 5
			2) and other that connects the other parents children with yours
				E.g. 5 with 6
			

 * @author Nelson Costa
 *
 */
class PopulatingNextRightPointersinEachNodeSolution2 {
    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;
        
        // Once we reach the final level, we are done
        while (leftmost.left != null) {
            
            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the 
            // corresponding links for the next level
            Node head = leftmost;
            
            while (head != null) {
                
                // CONNECTION 1
                head.left.next = head.right;
                
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            
            // Move onto the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
    
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}


/**
 * @intuition
 * 		intuition is to add the nodes to the queue in a efficient way in order to connect nodes in the same level.
 * 		the first loop processes the level, the second loop processes the node for that level
 * 		This level control is made by using the size.
 * 		is a neat and efficient way of traversing the tree
 * 
 * @author Nelson Costa
 *
 */
class PopulatingNextRightPointersinEachNodeSolution1 {
    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> Q = new LinkedList<Node>(); 
        Q.add(root);
        
        // Outer while loop which iterates over 
        // each level
        while (Q.size() > 0) {
            
            // Note the size of the queue
            int size = Q.size();
            
            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {
                
                // Pop a node from the front of the queue
                Node node = Q.poll();
                
                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only 
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }
                
                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        
        // Since the tree has now been modified, return the root node
        return root;
    }
    
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}