package com.data.structures.main;

import com.data.structures.models.BinaryTree;

public class BinaryTreeTest {

	public BinaryTree root;
	
	public static void main(String[] args) {
		
		BinaryTreeTest test = new BinaryTreeTest();
		test.BinaryTreeTraversalTest();

	}

	
	public void BinaryTreeTraversalTest() {
		BinaryTreeTest test = new BinaryTreeTest();
		test.buildTree();
		test.preOrderTest();
		test.inOrderTest();
		test.postOrderTest();
	}
	
	private void buildTree() {
		root = new BinaryTree(1);
		BinaryTree right = new BinaryTree(39);
		BinaryTree left = new BinaryTree(10);
		BinaryTree leftLeft = new BinaryTree(5);
		BinaryTree leftLeftleft = new BinaryTree(6);
		BinaryTree leftLeftright = new BinaryTree(7);

		root.setRight(right);
		root.setLeft(left);
		left.setLeft(leftLeft);
		leftLeft.setLeft(leftLeftleft);
		leftLeft.setRight(leftLeftright);
	}

	private void preOrderTest() {
		System.out.println("+++++++++++ PREORDER +++++++++++");
		root.preorder(); //Expected 1 10 5 6 7 39
	}
	
	private void inOrderTest() {
		System.out.println("+++++++++++ INORDER +++++++++++");
		root.inorder(); //Expected 1 10 5 6 7 39
	}
	
	private void postOrderTest() {
		System.out.println("+++++++++++ POSTORDER +++++++++++");
		root.postorder(); //Expected 1 10 5 6 7 39
	}
}
