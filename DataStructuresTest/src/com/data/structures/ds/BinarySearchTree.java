package com.data.structures.ds;

public class BinarySearchTree {
	private int value;
	private BinarySearchTree right;
	private BinarySearchTree left;

	public BinarySearchTree(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BinarySearchTree getRight() {
		return right;
	}

	public void setRight(BinarySearchTree right) {
		this.right = right;
	}

	public BinarySearchTree getLeft() {
		return left;
	}

	public void setLeft(BinarySearchTree left) {
		this.left = left;
	}

	/*******************************
	 * 
	 * 			INSERTION
	 * 
	 ******************************/

	/**
	 * Tentativa de criar um add que de facto cria uma binary tree mais completa e não em triangulo, acho que está correto
	 * Ou seja aqui nós não estamos a criar a tree por ordem ou algo assim, estamos a criar a tree sempre de cima para baixo. não trocamos as ligações dos nós.
	 * O Processo foi interessante...
	 * @param nodeVal
	 */
	public void insertNode(int nodeVal) {
		if (value < nodeVal)
		{
			//right
			if (right == null)
			{
				right = new BinarySearchTree(nodeVal);
				return;
			}

			right.insertNode(nodeVal);
		}
		else {
			//left
			if (left == null)
			{
				left = new BinarySearchTree(nodeVal);
				return;
			}

			left.insertNode(nodeVal);
		}
	}

	/**
	 * Adicionar nó algoritmo fatela, forma uma espécie de triangulo
	 * @param nodeVal
	 */
	public void addNodeA(int nodeVal)
	{
		if(value < nodeVal)
		{
			if (right == null)
			{
				BinarySearchTree newNode = new BinarySearchTree(nodeVal);
				right = newNode;
				return;
			}

			if (right != null && right.getValue() > nodeVal) {
				BinarySearchTree newNode = new BinarySearchTree(nodeVal);
				BinarySearchTree temp = right;
				right = newNode;
				newNode.setRight(temp);
				return;
			}

			right.insertNode(nodeVal);

		}else
		{
			if (left == null)
			{
				BinarySearchTree newNode = new BinarySearchTree(nodeVal);
				left = newNode;
				return;
			}

			if (left != null && left.getValue() < nodeVal)
			{
				BinarySearchTree newNode = new BinarySearchTree(nodeVal);
				BinarySearchTree temp = left;
				left = newNode;
				newNode.setLeft(temp);
				return;

			}

			left.insertNode(nodeVal);
		}
	}

	/*******************************
	 * 
	 * 			SEARCH, MAX, MIN
	 * 
	 ******************************/

	public void searchNode(int nodeVal) {

		System.out.println( " > " + value);//start node

		if(value == nodeVal)
		{
			System.out.println("NODE FOUND: " + value);
			return;
		}

		if(value < nodeVal)
		{
			//right
			if (right != null) {
				right.searchNode(nodeVal);
			}
		}
		else
		{
			//left
			if (left != null) {
				left.searchNode(nodeVal);
			}
		}
	}

	public void max(BinarySearchTree node, int max)
	{
		System.out.println("> " + value);
		if (this.right != null)
		{
			this.right.max(this.right, max);
			return;
		}
		else
		{
			max = this.getValue();
			System.out.println("=" + max);
		}
	}

	public void min(BinarySearchTree node, int min)
	{				
		System.out.println("> " + node.value);

		if (node.left != null)
		{
			min(node.left, min);
		}
		else
		{
			min = node.value;
			System.out.println("= " + min);
		}
	}

	/*******************************
	 * 
	 * 			TRAVERSAL
	 * 
	 ******************************/
	public void preorder() {

		System.out.println(value);

		if(left != null)
		{
			left.preorder();
		}


		if(right != null)
		{
			right.preorder();
		}
	}

	public void inorder() {


		if(left != null)
		{
			left.inorder();
		}

		System.out.println(value);


		if(right != null)
		{
			right.inorder();
		}
	}

	public void postorder() {


		if(left != null)
		{
			left.postorder();
		}

		if(right != null)
		{
			right.postorder();
		}		

		System.out.println(value);
	}
}
