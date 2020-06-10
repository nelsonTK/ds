package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CopyListwithRandomPointer {

	public static void main(String[] args) {

	}
	/**
	 * @intuition
	 * 		The intuition is to maintain a copy of nodes in a hashmap.
	 * 		for each original we pair with a copy.
	 * 		And that's how we roll
	 * 
	 * @optimizations
	 * 		
	 * @fail
        	1) forgot to put the original nodes in the hashmap
	 * @score
	 * 		Runtime: 1 ms, faster than 15.26% of Java online submissions for Copy List with Random Pointer.
			Memory Usage: 39.7 MB, less than 18.45% of Java online submissions for Copy List with Random Pointer.
	 * 
	 * @time	O(N)
	 * @space	O(N)
	 * 
   
	 **/
	public Node copyRandomList(Node head) {

		if (head == null)
			return null;

		HashMap<Node, Node> map = new HashMap<>();

		Node ans = new Node(-1);
		Node cur = ans;            

		while (head != null)
		{
			cur.next = getCopy(head, map);
			cur.next.next = getCopy(head.next, map);
			cur.next.random = getCopy(head.random, map);

			cur = cur.next;
			head = head.next;
		}

		return ans.next;
	}

	private Node getCopy(Node original, HashMap<Node, Node> map){
		if (original == null)
			return null;		

		return map.computeIfAbsent(original, k -> new Node(original.val));
	}

	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/


/**
 * @intuition
 * 		For each element in the list we create a copy of the original and put it next to the original
 * 		in a secon iteration we replicicate the random connection of the original node
 * 		int the final step we separate the two lists
 * 
 * @author Nelson Costa
 *
 */
class CopyListwithRandomPointerSolution3 {
	public Node copyRandomList(Node head) {

		if (head == null) {
			return null;
		}

		// Creating a new weaved list of original and copied nodes.
		Node ptr = head;
		while (ptr != null) {

			// Cloned node
			Node newNode = new Node(ptr.val);

			// Inserting the cloned node just next to the original node.
			// If A->B->C is the original linked list,
			// Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
			newNode.next = ptr.next;
			ptr.next = newNode;
			ptr = newNode.next;
		}

		ptr = head;

		// Now link the random pointers of the new nodes created.
		// Iterate the newly created list and use the original nodes' random pointers,
		// to assign references to random pointers for cloned nodes.
		while (ptr != null) {
			ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
			ptr = ptr.next.next;
		}

		// Unweave the linked list to get back the original linked list and the cloned list.
		// i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
		Node ptr_old_list = head; // A->B->C
		Node ptr_new_list = head.next; // A'->B'->C'
		Node head_old = head.next;
		while (ptr_old_list != null) {
			ptr_old_list.next = ptr_old_list.next.next;
			ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
			ptr_old_list = ptr_old_list.next;
			ptr_new_list = ptr_new_list.next;
		}
		return head_old;
	}

	class Node {
		public int val;
		public Node next;
		public Node random;

		public Node() {}
		
		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
		
		public Node(int _val,Node _next,Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}
}


/**
 * Equivalent to my solution
 * 
 * @author Nelson Costa
 *
 */
class CopyListwithRandomPointerSolution2 {
	// Visited dictionary to hold old node reference as "key" and new node reference as the "value"
	HashMap<Node, Node> visited = new HashMap<Node, Node>();

	public Node getClonedNode(Node node) {
		// If the node exists then
		if (node != null) {
			// Check if the node is in the visited dictionary
			if (this.visited.containsKey(node)) {
				// If its in the visited dictionary then return the new node reference from the dictionary
				return this.visited.get(node);
			} else {
				// Otherwise create a new node, add to the dictionary and return it
				this.visited.put(node, new Node(node.val, null, null));
				return this.visited.get(node);
			}
		}
		return null;
	}

	public Node copyRandomList(Node head) {

		if (head == null) {
			return null;
		}

		Node oldNode = head;

		// Creating the new head node.
		Node newNode = new Node(oldNode.val);
		this.visited.put(oldNode, newNode);

		// Iterate on the linked list until all nodes are cloned.
		while (oldNode != null) {
			// Get the clones of the nodes referenced by random and next pointers.
			newNode.random = this.getClonedNode(oldNode.random);
			newNode.next = this.getClonedNode(oldNode.next);

			// Move one step ahead in the linked list.
			oldNode = oldNode.next;
			newNode = newNode.next;
		}
		return this.visited.get(head);
	}


	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}

		public Node(int val, Node next, Node random) {
			this.val = val;
			this.next = next;
			this.random = random;
		}
	}
}

/**
 * Dealing with a linkedList as it was a graph
 * @author Nelson Costa
 *
 */
class CopyListwithRandomPointerSolution1 {
	// HashMap which holds old nodes as keys and new nodes as its values.
	HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

	public Node copyRandomList(Node head) {

		if (head == null) {
			return null;
		}

		// If we have already processed the current node, then we simply return the cloned version of
		// it.
		if (this.visitedHash.containsKey(head)) {
			return this.visitedHash.get(head);
		}

		// Create a new node with the value same as old node. (i.e. copy the node)
		Node node = new Node(head.val, null, null);

		// Save this value in the hash map. This is needed since there might be
		// loops during traversal due to randomness of random pointers and this would help us avoid
		// them.
		this.visitedHash.put(head, node);

		// Recursively copy the remaining linked list starting once from the next pointer and then from
		// the random pointer.
		// Thus we have two independent recursive calls.
		// Finally we update the next and random pointers for the new node created.
		node.next = this.copyRandomList(head.next);
		node.random = this.copyRandomList(head.random);

		return node;
	}

	class Node {
		public int val;
		public Node next;
		public Node random;

		public Node() {}

		public Node(int _val,Node _next,Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}
}
