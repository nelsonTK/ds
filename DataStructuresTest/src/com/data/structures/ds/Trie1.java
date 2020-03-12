package com.data.structures.ds;

import java.util.HashMap;
import java.util.Stack;

public class Trie1 {

	private TrieNode root;

	public Trie1() {
		root = new TrieNode();
	}


	public boolean hasWord(String word) {

		TrieNode current = root;
		int i = 0;
		while (i < word.length()) {

			current = current.getChildren().get(word.charAt(i));

			if (current == null)
			{
				return false;
			}

			i++;
		}

		return current.isFinal();
	}

	public void addWord(String word) {
		//		root.addWord(word);
		root.addWordIterative(word);
	}


	/**
	 * Removes word from Trie
	 * @timecomplexity  O(N) = O(1) + O(N) + O(1) + O(N)
	 * @spacecomplexity O(N)
	 * @param word
	 */
	public void removeWord(String word) {
		/***************
		 * Trie is empty
		 ***************/
		if (root.getChildren().size() == 0)
		{
			System.out.println("Trie is Empty");
			return;
		}

		/****************
		 * Fill the stack
		 ****************/
		Stack<TrieNode> s = new Stack<TrieNode>();
		TrieNode current = root;
		s.push(current);

		for (Character c : word.toCharArray()) {

			current = current.getChildren().get(c);
			if (current == null) 
			{
				System.out.println("Word not found");
				return;
			}

			s.push(current);
		}
		
		/*******************
		 * Empty the stack
		 ******************/
		boolean foundIntermediateWord = false;
		TrieNode child = s.pop();
		current = s.peek();
		RemoveLastCharOrNode(child, current);

		//root element is not removed from stack
		
		while(!foundIntermediateWord && !current.getValue().equals(Character.MIN_VALUE)) {
			child = s.pop();
			current = s.peek();

			if (!child.isFinal() && child.getChildren().size() == 0)
			{
				current.getChildren().remove(child.getValue());
			}
			else
			{
				foundIntermediateWord = true;
			}
		}		
	}
	
	/**
	 * removes the child node from parent if it hash no children
	 * @param child
	 * @param parent
	 */
	private void RemoveLastCharOrNode(TrieNode child, TrieNode parent) {

		if (child.getChildren().size() == 0)
		{
			parent.getChildren().remove(child.getValue());
		}
		else
		{
			child.setFinal(false);
		}
	}
}

class TrieNode{
	private Character value;
	private boolean isFinal;
	private HashMap<Character, TrieNode> children;

	public TrieNode() {
		value = Character.MIN_VALUE;
		isFinal = false;
		children = new HashMap<Character, TrieNode>();
	}

	public TrieNode(char val) {
		value = val;
		isFinal = false;
		children = new HashMap<Character, TrieNode>();
	}


	/**
	 * Add Word Iterative approach
	 * @time 	O(N)
	 * @space 	O(1)
	 * @param word
	 */
	public void addWordIterative(String word) {

		TrieNode current = this;
		TrieNode next = this;

		for (Character c : word.toCharArray()) {

			next = current.getChildren().get(c);
			if (next == null) {
				current.getChildren().put(c, new TrieNode(c));
				current = current.getChildren().get(c);
			}else {
				current = next;
			}
		}

		current.setFinal(true);
	}

	/**
	 * Recursive approach to solve insert/add node
	 * @param word
	 */
	public  void addWord(String word) {

		if(word == null || word.trim().equals(""))
			return;

		int startIndex = 0;
		Character key = word.charAt(startIndex);
		TrieNode child = this.children.get(key);

		if (child != null) 
		{
			if (startIndex + 1 < word.length())
			{
				child.addWord(word.substring(1, word.length()));
			}
			else
			{
				child.setFinal(true);
			}
		}
		else
		{
			TrieNode newChild = new TrieNode(key);

			if (startIndex + 1 < word.length())
			{				
				this.children.put(key, newChild);
				newChild.addWord(word.substring(1, word.length()));
			}
			else
			{
				this.children.put(key, newChild);
				newChild.setFinal(true);
			}
		}
	}


	public Character getValue() {
		return value;
	}

	public void setValue(Character value) {
		this.value = value;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(HashMap<Character, TrieNode> children) {
		this.children = children;
	}
}
