package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

public class WordBreak extends LeetCodeExercise {

	static WordBreak b = new WordBreak();

	public static void main(String[] args) {
		String s = "leetcode";
		String [] words = new String[]{"leet", "code"};

		s = "applepenapple";
		words = new String[]{"apple", "pen"};


		s = "catsandog";
		words = new String[]{"cats", "dog", "sand", "and", "cat"};

		s = "aaaaaaaaaaaaaaaaaaaaaaaaaaab";
		words = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

		//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab		
		//		["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]


		List<String> wordDict = Arrays.asList(words);

		System.out.println(b.wordBreak(s, wordDict));
	}

	/**
	 * @fail
	 * 		1) the return of the result was not going right. I was not handling well when the string is in fact a valid word break
	 * 		2) I was not finalizing well when the last word was right. as i could not solve it (i + 1 > s.length - 1) it return the default false value;
	 		3) Timeout very slow
	 		4) Timeout very slow, I tried hashmaps and stoping the search earlier but failed too, another idea would be to perform binary search instead of linear search

	   @comments
	   		I clearly need some caching here and optimizations
	   		I'm thinking in a HashMap or an HashSet
	 * @time	O(N^2*W)
	 * @space	N*W
	 * @bcr		
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, List<String> wordDict) {

		if (wordDict == null || wordDict.size() == 0 || s== null || s.length() == 0)
			return false;

		Trie trie = listToTrie(wordDict);

		return solve(s, 0, trie);
	}

	private boolean solve(String s, int start, Trie trie) {

		if (!trie.startsWith(s.charAt(start)))
		{
			return false;
		}

		String word = "";

		for (int i = start; i < s.length(); i++)
		{
			word+=s.charAt(i);

			if (trie.search(word))
			{
				if (i + 1 < s.length() && solve(s, i+1, trie))
				{
					return true;
				}
				else if (i == s.length() - 1)
				{	
					return true;
				}
			}
		}

		return false;
	}

//	Set<String> memo = new HashSet<>();
//	private boolean solve(String s, int start, Trie trie) {
//
//		if (!trie.startsWith(s.charAt(start)))
//		{
//			return false;
//		}
//
//		String word = "";
//
//		for (int i = start; i < s.length(); i++)
//		{
//			word+=s.charAt(i);
//
//			if (!trie.startsWith(word))
//				return false;
//
//
//			if (memo.contains(word) || trie.search(word))
//			{
//				memo.add(word);
//
//				if (i + 1 < s.length() && solve(s, i+1, trie))
//				{
//					return true;
//				}
//				else if (i == s.length() - 1)
//				{	
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

	private Trie listToTrie(List<String> s) {

		Trie t = new Trie();

		s.stream().forEach(x -> t.insert(x));

		return t;
	}


	class Trie{
		TrieNode root = new TrieNode();

		public boolean search(String s) {
			TrieNode node = root;
			for(char c : s.toCharArray())
			{
				node = node.words.get(c);

				if (node ==  null)
					return false;
			}

			return node.isFinal ? true : false;
		}

		public void insert(String s)
		{
			TrieNode node = root;
			TrieNode value;

			for (char c : s.toCharArray())
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

		public boolean startsWith(char c) {
			return root.words.get(c) == null ? false : true;
		}

		/**
		 * not super tested
		 * @param s
		 * @return
		 */
		public boolean startsWith(String s) {
			TrieNode node = root;
			for(char c : s.toCharArray())
			{
				node = node.words.get(c);

				if (node == null)
					return false;
			}
			return true;
		}
	}

	class TrieNode{
		char val;
		HashMap<Character, TrieNode> words = new HashMap<>();
		boolean isFinal;

		public TrieNode ()
		{
			val = Character.MIN_VALUE;
		}

		public TrieNode (char c)
		{
			val = c;
		}
		public TrieNode (char c, boolean isFinal)
		{
			val = c;
			this.isFinal = isFinal;
		}
	}
}
