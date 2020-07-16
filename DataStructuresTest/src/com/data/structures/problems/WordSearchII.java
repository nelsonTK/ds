package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-search-ii/
 * HARD
 * @author Nelson Costa
 *
 */
public class WordSearchII {

	public static void main(String[] args) {

	}


	/**
	@score
		Runtime: 110 ms, faster than 30.87% of Java online submissions for Word Search II.
		Memory Usage: 61.2 MB, less than 5.04% of Java online submissions for Word Search II.
	
		Runtime: 77 ms, faster than 33.36% of Java online submissions for Word Search II.
		Memory Usage: 47.9 MB, less than 58.98% of Java online submissions for Word Search II.


	@fail
	    1) forgot to backtrack stringbuilder
	    2) I think that I forgot to backtrack extra repetitions
	    3) I was adding duplicate first element
	    4) Calculated mistake I forgot to add a set
	    5) looks like I misinterpreted one line or so...the same cell cannot be used twice and I interpreted the oposite
	    
	 @time
	  	O(N^2M^2*W)
	  	
	 @space
	 	O(N*M + N)
	    
	 */
	Set<String> ans;
	public List<String> findWords(char[][] board, String[] words) {


		ans = new HashSet<>();

		Trie t = new Trie();

		// Fill a Trie
		//O(N) * O(L)
		for (String w : words)
		{
			t.addString(w);
		}

		boolean[][] visited = new boolean[board.length][board[0].length];

		for (int r = 0; r < board.length; r++) //O(M)
		{
			for (int c = 0; c < board[0].length; c++) //O(N)
			{
				search (board, r, c, t, new StringBuilder(""), visited, ans);
			}
		}


		return new ArrayList<String>(ans);
		//Backtrack and cut fast
		//O(N^2) * O(W)       


	}

	private void search (char[][] board, int r, int c, Trie t, StringBuilder cur,boolean[][] visited, Set<String> ans)
	{

		if(r < 0 || c < 0 || r >= board.length|| c >= board[0].length)
		{
			return;
		}

		if(visited[r][c])
			return;

		TrieNode node;

		visited[r][c] = true;

		cur.append(board[r][c]);

		if ((node = t.startsWith(cur)) == null) //O(W)
		{
			visited[r][c] = false;
			cur.setLength(cur.length() - 1);
			return;
		}

		if (node.isFinal)
			ans.add(cur.toString());


		search (board, r+1, c, t, cur, visited, ans);
		search (board, r, c+1, t, cur, visited, ans);
		search (board, r-1, c, t, cur, visited, ans);
		search (board, r, c-1, t, cur, visited, ans);

		visited[r][c] = false;
		cur.setLength(cur.length() - 1);
	}



	class Trie{
		TrieNode root;

		public Trie(){
			root = new TrieNode(Character.MIN_VALUE);
		}

		public void addString(String s){

			TrieNode node = root;
			TrieNode child;
			for (char c : s.toCharArray())
			{

				if (node.contains(c))
				{
					node = node.children.get(c);
				}
				else
				{
					child = new TrieNode(c);
					node.children.put(c, child);
					node = child;

				}
			}
			node.isFinal = true;
		}

		public TrieNode startsWith(StringBuilder s)
		{            
			TrieNode node = root;

			for (int i = 0; i < s.length(); i++)
			{
				char c = s.charAt(i);

				if (node.contains(c))
				{
					node = node.children.get(c);
				}
				else
				{
					return null;
				}
			}

			return node;
		}        
	}

	class TrieNode{
		char val;
		HashMap<Character, TrieNode> children;
		boolean isFinal;

		public TrieNode(char v){
			char val = v;
			children = new HashMap<>();
		}


		public void add(TrieNode n)
		{
			children.put(n.val, n);
		}

		public boolean contains(char c)
		{
			return children.get(c) != null ? true : false;
		}
	}
}
