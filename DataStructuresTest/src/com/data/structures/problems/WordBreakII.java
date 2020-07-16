package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/word-break-ii/
 * HARD
 * @author Nelson Costa
 *
 */
public class WordBreakII extends LeetCodeExercise{

	static WordBreakII w = new WordBreakII();
	public static void main(String[] args) {
		String s = "aaaaaaaaaaaaaaaaaaaaab";
		List<String> wordDict = Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});
		
		
		System.out.println(Arrays.toString(w.wordBreak(s, wordDict).toArray()));
	}
	
	List<String> ans;

	/**
     * [Time Limit Exceeded] 
     *    	It looks like it works, I also created a new data structure which I called Statefull Tree. but the algorithm by itself is still costly
     *		That's why it timesout
     *
     * @intuition
     *        created the concept of stateful trie but if fails at the worst cases because I use no cache.
     *        We some changes this concept might work
     *    
     * @comments
     *    	I use no cache, and I calculate the same values again and again
     *        
     * @fail
     *        1) null pointer exception, forgot to initialize children in TrieNode
     *        2) forgot to remove the final space
     *
	 **/
	public List<String> wordBreak(String s, List<String> wordDict) {
		ans = new ArrayList<String>();

		//Trie
		//Fill the stateful trie
		Trie trie = new Trie();
		for (String st : wordDict) //O(N)
		{
			trie.addString(st); //O(L)
		}

		//build each possible combination
		StringBuilder sb = new StringBuilder("");
		breakWord(trie, sb, 0, s);


		return ans;
	}


	private void breakWord(Trie trie, StringBuilder sb, int start, String s)
	{

		//if we got to the end of the string we should add to the answers
		if (start >= s.length())
		{
			sb.setLength(sb.length() - 1);
			ans.add(sb.toString());
			return;
		}

		//start a new stateful search
		trie.addStatefulSearch();

		int wordSize = 0;


		for (int i = start; i < s.length(); i++){
			//if the cur word starts with i, and is final we break,
			// else we continue adding chars to the cur word
			if (trie.statefulStartsWith(s.charAt(i)))
			{
				sb.append(s.charAt(i));
				wordSize++;
				if (trie.statefultIsFinal())
				{
					sb.append(" ");
					breakWord(trie, sb, i + 1, s);
				}
			}
			else
			{
				//if we dont have a word we end this search state and remove the word from string builder
				//reduce stringBuilder Size
				sb.setLength(sb.length() - wordSize);
				if (sb.length() != 0)
					sb.setLength(sb.length() - 1); //remove space

				trie.removeSearchStateNode();    
				return;
			}
		}

		//if we get to this, is means we got to the end of the string and we want to explore another possibilities, 
		//so we remove the string from the stringbuilder and also terminate the current state
		sb.setLength(sb.length() - wordSize);
		if (sb.length() != 0)
			sb.setLength(sb.length() - 1); //remove space

		trie.removeSearchStateNode();    
	}

	class Trie {
		TrieNode root;
		LL searchState;

		public Trie (){
			searchState = new LL();
			root = new TrieNode(Character.MIN_VALUE);
		}

		public void addString(String s){

			TrieNode node = root;

			for (int i = 0; i < s.length(); i++)
			{
				if (node.children.containsKey(s.charAt(i)))
				{
					node = node.children.get(s.charAt(i));
				}
				else
				{
					TrieNode newChildren = new TrieNode(s.charAt(i));
					node.children.put(s.charAt(i), newChildren);
					node = newChildren;
				}
			}

			node.isFinal = true;

		}

		public void addStatefulSearch()
		{
			searchState.add(new LLNode(root));
		}

		//indicates if current search plus c is still in the trie 
		//and updates the current state to node c
		public boolean statefulStartsWith(char c)
		{
			TrieNode node = searchState.getLatest();
			if (node != null)
			{
				//we will search for a character c
				//if present we add c node to current search state
				//if not we return false
				if (node.children.containsKey(c))
				{
					node = node.children.get(c);
					searchState.removeMostRecent();
					searchState.add(new LLNode(node));
					return true;
				}
			}
			return false;
		}


		//indicates if the current search node is a final word
		public boolean statefultIsFinal()
		{
			TrieNode node = searchState.getLatest();
			return node != null ? node.isFinal :  false;
		}


		//Removes latest the search State node 
		public void removeSearchStateNode(){
			searchState.removeMostRecent();
		}
	}
	class TrieNode {
		char val;
		HashMap<Character, TrieNode> children;
		boolean isFinal;

		public TrieNode (char val)
		{
			this.val = val;
			isFinal = false;
			children = new HashMap<>();
		}
	}
	class LL {

		LLNode head;
		LLNode tail;

		public LL(){
			head = new LLNode(null);
			tail = new LLNode(null);
			head.next = tail;
		}

		//new elements always next to head
		public void add(LLNode node){
			LLNode tmp = head.next;
			head.next = node;
			node.next = tmp;
		}

		public void removeMostRecent(){
			if (head.next != tail)
			{
				head.next = head.next.next;
			}
		}

		public TrieNode getLatest(){
			return head.next != tail ? head.next.val : null;
		}

	}
	class LLNode {
		TrieNode val;
		LLNode next;

		public LLNode(TrieNode t){
			val = t;
			next = null;
		}
	}
}
