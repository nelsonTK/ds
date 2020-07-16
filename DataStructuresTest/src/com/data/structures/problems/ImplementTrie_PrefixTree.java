package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ImplementTrie_PrefixTree {

	static ImplementTrie_PrefixTree i = new ImplementTrie_PrefixTree();
	public static void main(String[] args) {
		Trie trie = i.new Trie();

		trie.insert("apple");
		trie.search("apple");   // returns true
		trie.search("app");     // returns false
		trie.startsWith("app"); // returns true
		trie.insert("app");   
		trie.search("app");     // returns true
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @fail
	 * 		1) Forgot to initialize the the TrieNode Hashmap
	 * 
	 * @score
	 * 		Runtime: 36 ms, faster than 40.92% of Java online submissions for Implement Trie (Prefix Tree).
			Memory Usage: 50.7 MB, less than 100.00% of Java online submissions for Implement Trie (Prefix Tree).
	 *
	 *  @optimizations
	 * 		I believe I could have used a 26 character array, but I decided to use an Hashmap.
	 *
	 * @author Nelson Costa
	 *
	 */
	class Trie {

		TrieNode root = new TrieNode();

		/** Initialize your data structure here. */
		public Trie() {

		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			TrieNode node = root;
			TrieNode value;

			for (char c : word.toCharArray())
			{
				value = node.words.get(c);
				if (value == null)
				{
					value = new TrieNode(c);
					node.words.put(c, value);
				}
				node = value;
			}

			node.isFinal = true;
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			TrieNode node = root;

			for(char c : word.toCharArray())
			{
				node = node.words.get(c);

				if (node == null)
					return false;
			}

			return node.isFinal? true : false;
		}

		/** Returns if there is any word in the trie that starts with the given prefix. */
		public boolean startsWith(String prefix) {
			TrieNode node = root;

			for(char c : prefix.toCharArray())
			{
				node = node.words.get(c);

				if (node == null)
					return false;
			}

			return true;
		}

		class TrieNode{
			char c;
			HashMap<Character, TrieNode> words = new HashMap<>();
			boolean isFinal;

			public TrieNode(){
				this.c = Character.MIN_VALUE;
			}
			public TrieNode(char c){
				this.c = c;
			}

			public TrieNode(char c, boolean isFinal){
				this.c = c;
				this.isFinal = isFinal;
			}
		}
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
class ImplementTrie_PrefixTreeSolution1 {

	class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}
		
		// search a prefix or whole key in trie and
	    // returns the node where search ends
	    private TrieNode searchPrefix(String word) {
	        TrieNode node = root;
	        for (int i = 0; i < word.length(); i++) {
	           char curLetter = word.charAt(i);
	           if (node.containsKey(curLetter)) {
	               node = node.get(curLetter);
	           } else {
	               return null;
	           }
	        }
	        return node;
	    }

	    // Returns if the word is in the trie.
	    public boolean search(String word) {
	       TrieNode node = searchPrefix(word);
	       return node != null && node.isEnd();
	    }
	    
	    // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) {
	        TrieNode node = searchPrefix(prefix);
	        return node != null;
	    }
	}

	class TrieNode {

		// R links to node children
		private TrieNode[] links;

		private final int R = 26;

		private boolean isEnd;

		public TrieNode() {
			links = new TrieNode[R];
		}

		public boolean containsKey(char ch) {
			return links[ch -'a'] != null;
		}
		public TrieNode get(char ch) {
			return links[ch -'a'];
		}
		public void put(char ch, TrieNode node) {
			links[ch -'a'] = node;
		}
		public void setEnd() {
			isEnd = true;
		}
		public boolean isEnd() {
			return isEnd;
		}
	}
}