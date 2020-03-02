package com.data.structures.main;

import com.data.structures.ds.BinarySearchTree2;

public class BinarySearchTree2Tests {

	public static void main(String[] args) {
		
		BinarySearchTree2 b = new BinarySearchTree2(1);
		b.addNode(2);
		b.addNode(0);
		b.addNode(6);
		b.addNode(3);
		b.addNode(5);
		b.inorderIterative();
		System.out.println();
		b.inorderGFG();
		//b.inorder();
		
		
	}
}
