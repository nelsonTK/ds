package com.data.structures.models;

public class BinaryTree {

	private int value;
	private BinaryTree right;
	private BinaryTree left;

	public BinaryTree(int value, BinaryTree right, BinaryTree left) {
		super();
		this.value = value;
		this.right = right;
		this.left = left;
	}
	
	public BinaryTree(int value) {
		super();
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public BinaryTree getRight() {
		return right;
	}
	public void setRight(BinaryTree right) {
		this.right = right;
	}
	public BinaryTree getLeft() {
		return left;
	}
	public void setLeft(BinaryTree left) {
		this.left = left;
	}


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
			left.preorder();
		}
		
		System.out.println(value);

		
		if(right != null)
		{
			right.preorder();
		}
	}
	
	public void postorder() {
		
		
		if(left != null)
		{
			left.preorder();
		}
				
		if(right != null)
		{
			right.preorder();
		}		

		System.out.println(value);
	}
	
	public boolean haskey(int value) {
		
		return true;
	}
}
