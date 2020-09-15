package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DesignAddandSearchWordsDataStructure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordDictionary wordDictionary = new WordDictionary();
		//"ran"],["rune"],["runner"],["runs"],["add"],["adds"],["adder"],["addee"
		wordDictionary.addWord("ran");
		wordDictionary.addWord("rune");
		wordDictionary.addWord("runner");
		wordDictionary.addWord("runs");
		wordDictionary.addWord("adds");
		wordDictionary.addWord("adder");
		wordDictionary.addWord("addee");
		System.out.println(wordDictionary.search("....e.")); // return False
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		wordDictionary.search("pad"); // return False
		wordDictionary.search("bad"); // return True
		wordDictionary.search(".ad"); // return True
		wordDictionary.search("b.."); // return True
	}

	/**
	 * @intuition
	 * 		Just a back tracking solution
	 * 
	 * @score
	 * 		Runtime: 93 ms, faster than 25.72% of Java online submissions for Design Add and Search Words Data Structure.
	 *		Memory Usage: 50.7 MB, less than 63.86% of Java online submissions for Design Add and Search Words Data Structure.
	 * 
	 * @fail
	 * 		1) Many errors related with lack of attention and missing details, but the main logic was there.
	 * 		like non initializations and so forth
	 * 		2) I was not backtracking completely
	 * @author Nelson Costa
	 *
	 */
	static class WordDictionary {
		N root;
		N rootReversed;
		/** Initialize your data structure here. */
		public WordDictionary() {
			root = new N(Character.MIN_VALUE);
		}

		/** Adds a word into the data structure. */
		public void addWord(String word) {

			addWord(word, root);
		}


		private void addWord(String word, N root){

			N node = root;

			for(char c : word.toCharArray())
			{
				if (node.children[c - 'a']!= null)
				{
					node = node.children[c - 'a'];
				}
				else
				{
					N children = new N(c);
					node.children[c - 'a'] = children;
					node = node.children[c - 'a'];
				}
			}

			node.isFinal = true;
		}


		/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
		public boolean search(String word) {
			StringBuilder w = new StringBuilder(word);
			return search(0, w, root);
		}

		public boolean search(int start, StringBuilder word,  N node)
		{        
			char c;
			for (int j = start; j < word.length(); j++)
			{
				c = word.charAt(j);

				if (c == '.')
				{
					for (char i = 'a'; i <= 'z'; i++)
					{
						word.replace(j, j+1,  i + "");
						if (search(j, word, node))
							return true;
						word.replace(j, j+1,  ".");
					}
					return false;
				}
				else if (node.children[c - 'a']!= null)
				{
					node = node.children[c - 'a']; 
				}
				else
				{
					return false;
				}
			}

			return node.isFinal;
		}


		class N{
			char val;
			N[] children;
			boolean isFinal;

			N (char v)
			{
				val = v;
				children = new N[26];
			}
		}
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Solution with hashmap instead of array.
 * My decision in using arrays was because I didn't implemented a tree for a long time with an array.
 * but I knew it would not help with the search of wildcards. the hashmap was indeed the better choice.
 * 
 * @author Nelson Costa
 *
 */
class TrieNode {
    Map<Character, TrieNode> children = new HashMap();
    boolean word = false;
    public TrieNode() {}
}

class WordDictionary {
    TrieNode trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = trie;
        
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.word = true;
    }
    
    /** Returns if the word is in the node. */
    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                // if the current character is '.'
                // check all possible nodes at this level
                if (ch == '.') {
                    for (char x : node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true;    
                        }    
                    }   
                }
                // if no nodes lead to answer
                // or the current character != '.'
                return false;    
            } else {
                // if the character is found
                // go down to the next level in trie
                node = node.children.get(ch); 
            }   
        }      
        return node.word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchInNode(word, trie);
    }
}

/**
 * This is one of the top solutions and it is very cleaver.
 * 
 * The gist of it is to sabe the words grouped by length
 * 
 * and when you receive a word you get all the words with that length
 * 
 * then for each word you verify if the word matches.
 * 
 * @author Nelson Costa
 *
 */
class WordDictionaryUnofficialSolution1 {
    public Map<Integer, HashSet<String>> map;
    
    /** Initialize your data structure here. */
    WordDictionaryUnofficialSolution1() {
        map = new HashMap<Integer, HashSet<String>>();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        map.putIfAbsent(word.length(), new HashSet<String>() );
        map.get(word.length()).add(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        // important! otherwise not complete
        if (!map.containsKey(word.length() ) ) {
            return false;
        }
        for (String w : map.get(word.length() ) ) {
            // check match
            if (isMatch(word, w) ) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isMatch(String pattern, String word) {
        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            if (pattern.charAt(i) == '.') {
                continue;
            }
            if (pattern.charAt(i) != word.charAt(i) ) {
                return false;
            }
        }
        
        return true;
    }
    
    
}