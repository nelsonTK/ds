package com.data.structures.main;

import com.data.structures.ds.BinarySearchTree;

public class BinarySearchTreeTest {
	BinarySearchTree root;

	public static void main(String[] args) {
		BinarySearchTreeTest test = new BinarySearchTreeTest();
		//		test.binarySearchTreeAddNodeTest001();
		//		test.binarySearchTreeAddNodeTest002();
		//		test.binarySearchTreeAddNodeTest003();
		//test.binarySearchTreeSearchTests001();
		//test.maxTest();
		test.minTest();
	}

	/********************************************
	 * 
	 * 			TESTS
	 * 
	 ********************************************/
	
	public void binarySearchTreeAddNodeTest001() {

		root = new BinarySearchTree(24);
		root.insertNode(12);
		root.insertNode(6);
		root.insertNode(3);
		root.insertNode(1);
		root.insertNode(48);

		inOrderTest();
	}

	public void binarySearchTreeAddNodeTest002() {

		root = new BinarySearchTree(11);
		root.addNodeA(12);
		root.addNodeA(2);
		root.addNodeA(4);
		root.addNodeA(6);
		root.addNodeA(3);
		root.addNodeA(1);
		root.addNodeA(48);

		inOrderTest();
	}

	public void binarySearchTreeAddNodeTest003() {

		root = BinarySearchTreeTest.buildLongTree();
		inOrderTest();
	}

	public void binarySearchTreeSearchTests001() {
		binarySearchTreeSearch001(10);
		binarySearchTreeSearch001(80);
		binarySearchTreeSearch001(255);
		binarySearchTreeSearch001(150);
	}

	public void binarySearchTreeSearch001(int value) {
		System.out.println("+++++++++++ SEARCH "+ value + " +++++++++++");
		root = BinarySearchTreeTest.buildLongTree();
		root.searchNode(value);
	}

	public void maxTest() {
		root = buildLongTree();
		max(root);
	}

	public void minTest() {
		root = buildLongTree();
		min(root);
	}
	

	/********************************************
	 * 
	 * 			PRIVATE METHODS
	 * 
	 ********************************************/	
	
	private void max(BinarySearchTree b) {
		int currentValue = Integer.MIN_VALUE;
		b.max(b, currentValue);
	}

	private void min(BinarySearchTree b) {
		int currentValue = Integer.MAX_VALUE;
		b.min(b, currentValue);
	}

	private static BinarySearchTree buildLongTree() {
		BinarySearchTree root = new BinarySearchTree(240);
		root.insertNode(100);
		root.insertNode(150);
		root.insertNode(90);
		root.insertNode(93);
		root.insertNode(91);
		root.insertNode(80);


		root.insertNode(250);
		root.insertNode(260);
		root.insertNode(255);
		root.insertNode(245);
		return root;
	}
	
	private void inOrderTest() {
		System.out.println("+++++++++++ INORDER +++++++++++");
		root.inorder(); 
	}
}
