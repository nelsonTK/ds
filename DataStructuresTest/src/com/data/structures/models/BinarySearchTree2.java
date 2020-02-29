package com.data.structures.models;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Segunda implementação da BST dest vez acredito que corretamente, com o root node
 * @author Nelson Costa
 *
 */
public class BinarySearchTree2 {

	private Node root;

	public BinarySearchTree2(int value) {
		this.root = new Node(value);
	}

	public void addNode(int value)
	{
		addNode(root, value);
	}

	private void addNode(Node node, int value) {

		if (node == null)
		{
			return;
		}

		if(node.value > value) {
			if (node.left == null)
			{
				node.left = new Node(value);
			}else {
				addNode(node.left, value);
			}

		}
		else
		{
			if(node.right == null)
			{
				node.right = new Node(value);
			}else
			{
				addNode(node.right, value);
			}
		}
	}

	/**
	 * my firs implementation of inorder iterative
	 * Comparando com a versão do GFG parece que não implementei o stack bem como deve der ser, ou seja á risca como quando utilizamos uma função recursiva
	 * Acredito que o miss cirurgico foi quando fazer o pop.. vamos ver para a próxima
	 */
	public void inorderIterative(){
		ArrayList<Node> alreadyPeeked = new ArrayList<Node>();
		Stack<Node> s = new Stack<Node>();
		s.push(root);

		while (!s.isEmpty()) {
			Node peek = s.peek();

			if (peek.left != null && !alreadyPeeked.contains(peek.left)) {
				s.push(peek.left);
				alreadyPeeked.add(peek.left);

			}else {
				System.out.println(peek.value);

				if (peek.right != null && !alreadyPeeked.contains(peek.right)) {
					s.push(peek.right);
					alreadyPeeked.add(peek.right);

				}else {
					s.pop();

					//Se o lado direito do que obrar estiver processado, o pai está processado
					while (!s.empty() && alreadyPeeked.contains(s.peek().right)) {
						s.pop();
					}
				}

			}
		}
	}

	/**
	 * Solução GeekForGeeks Muitissimo interessante.
	 * baseada sobretudo em controlo de pointers
	 */
	public void inorderGFG() 
	{ 
		if (root == null) 
			return; 


		Stack<Node> s = new Stack<Node>(); 
		Node curr = root; 

		// traverse the tree 
		while (curr != null || s.size() > 0) 
		{ 

			/* Reach the left most Node of the 
	            curr Node */
			while (curr !=  null) 
			{ 
				/* place pointer to a tree node on 
	                   the stack before traversing 
	                  the node's left subtree */
				s.push(curr); 
				curr = curr.left; 
			} 

			/* Current must be NULL at this point */
			curr = s.pop(); 

			System.out.print(curr.value + " "); 

			/* we have visited the node and its 
	               left subtree.  Now, it's right 
	               subtree's turn */
			curr = curr.right; 
		} 
	} 

	public void inorder() {
		inorder(root);
	}

	private void inorder(Node node) {

		if (node == null) {
			return;
		}

		inorder(node.left);

		System.out.println(node.value);

		inorder(node.right);

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	private class Node{
		Node left;
		Node right;
		int value;

		public Node(int value) {
			super();
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}


	}
}
