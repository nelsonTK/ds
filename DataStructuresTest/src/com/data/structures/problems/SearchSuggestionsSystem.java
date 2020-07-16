package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/search-suggestions-system/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SearchSuggestionsSystem {
	static SearchSuggestionsSystemSolutionUnofficial3 s = new SearchSuggestionsSystemSolutionUnofficial3();
	public static void main(String[] args) {
//		String [] products = {"mobile","mouse","moneypot","monitor","mousepad"};
//		String searchWord = "mouse";
		String [] products = {"aaa","abb","acc"};
		String searchWord = "abc";
		s.suggestedProducts(products, searchWord);
	}

	public static final int MAX_SUGGESTIONS = 3;
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * 	 @intuition
	 * 	 		The intuition for this exercise was pretty simple.
	 * 
	 * 	 		It was to just use a trie and count the number of ocurrences of words found.
	 * 
	 * 	 		to fill the lexicographically order requirement 
	 * 
	 * 	 		is just to search in the array in ascending order everytime I'm searching for a word.
	 * 
	 * 	 		The most weird thing I did was to get always the element I want to start the search from.
	 * 
	 * 	 		if I'm searching for "MO" I want to start mysearch in "O" not in the root.
	 * 
	 * 	 		the consequence of this is that I need to put a prefix in the  searchWord Method in order to not loose the start bit of the word
	 * 
	 * 	 		this episode could be prevented If I searched for all the word inside my method search.
	 * 
	 * 	 		It was a design fault/Mistake.
	 * 
	 * 	 @alternatives
	 * 	 		1) use TreeMap instead of HashMap or Array
	 * 
	 * 	 		2) sort and resolve
	 * 
	 * 	 		3) binary search
	 * 
	 * 
	 * 
	 * 	@score
	 * 			Runtime: 66 ms, faster than 22.45% of Java online submissions for Search Suggestions System.
	 * 			Memory Usage: 45 MB, less than 61.38% of Java online submissions for Search Suggestions System.
	 * 
	 * 
	 *     @fail
	 *         1) Forgot to add prefix to the answer
	 * 
	 * 	@time
	 * 		O(N + L* (L + L*M))
	 * 
	 * 	@space
	 * 		O(N + L*M + L) third L would be recursion
	 * 
	 * @param products
	 * @param s
	 * @return
	 */
	public List<List<String>> suggestedProducts(String[] products, String s) {

		//Guards...

		//load trie
		List<List<String>> ans = new ArrayList<List<String>>();
		Trie trie = new Trie();
		TrieNode cur = null;
		for (String p : products) //O (N) , N equals to words numbers
		{
			trie.addWord(p);
		}

		List<String> row = new ArrayList<String>();
		cur = trie.root;
		for (int i = 0; i < s.length(); i++) //O(L) Length of the searchword
		{
			row =  new ArrayList<String>();
			if(cur != null)
				cur = cur.children[s.charAt(i) - 'a'];
			searchWord(cur, new StringBuilder(""), row, 0, s.substring(0,i)); //O(L), for the substring O(L*M)
			ans.add(row);
		}

		return ans;

	}

	private int searchWord(TrieNode node, StringBuilder curWord, List<String> curAns, int curSuggestions, String prefix){

		if (node == null || curSuggestions == MAX_SUGGESTIONS)
			return curSuggestions;

		curWord.append(node.val);

		if (node.isFinal)
		{      
			curAns.add(prefix + curWord.toString());
			curSuggestions++;
		}

		for (int i = 0; i < 26; i++) // O(1)
		{
			if (node.children[i] != null)
			{
				curSuggestions = searchWord(node.children[i], curWord, curAns, curSuggestions, prefix);

				if (curSuggestions == MAX_SUGGESTIONS)
				{
					return curSuggestions;
				}
			}
		}

		curWord.setLength(curWord.length() - 1);

		return curSuggestions;
	}

	class Trie{
		TrieNode root;

		public Trie(){
			root = new TrieNode();
		}

		public void addWord(String s)
		{
			//guards
			//empty
			if(s == null || s.trim().length() == 0)
				return;      

			TrieNode node = root;
			for (char c : s.toCharArray())
			{
				if (node.getChildren(c) != null)
				{
					node = node.getChildren(c);
				}
				else
				{
					node = node.addChildren(c);
				}                
			}
			node.isFinal = true;            
		}

		public TrieNode getNode(String s){

			TrieNode node = root;

			for (char c : s.toCharArray())
			{
				node = node.getChildren(c);
				if (node == null)
					return null;
			}

			return node;

		}
	}
	class TrieNode{
		char val;
		TrieNode [] children;
		boolean isFinal;

		public TrieNode(){
			val = Character.MIN_VALUE;
			children = new TrieNode[26];
		}
		public TrieNode(char v){
			val = v;
			children = new TrieNode[26];
		}

		public TrieNode getChildren(char c){
			return children[c - 'a'];
		}

		public TrieNode addChildren(char c){
			TrieNode child = new TrieNode(c);
			children[c - 'a'] = child;
			return child;
		}
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/

/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/

/**
 * Top solution
 * 
 * @timetook 3ms
 * 
 * This is a non trivial solution for the problem.
 * 
 * It uses some sort of LinkedList based hashmap with 26 buckets.
 * 
 * it uses popMinNode to get the minimum from buckets with more than one element
 * 
 * FindMinStrings to get the answer
 * 
 * the level represents a search with 1 character
 * 
 * Each level only has valid candidates
 * 
 * and the 26 elements scan is avoided with an heuristic
 * 
 * 
 * @Intuition
 * 		it starts off by doing the creation of a linked list with all the candidate values.
 * 
 * 		for each level we assign the words to the so called linkedlist based hashmap 
 * 
 * 		each bucket contains the next letter after search word as prefix for the candidate words
 * 			if searching for "m" and there is a word "mou" then the filled bucket is "o"
 * 			if the word is "m" than it is added to the results
 * 	
 * 		at level 1 we calculate the search string with one characters
 * 		at level 2 we calculate the search string with two characters
 * 
 * 
 * 		we pass to level X only the elements that are valid candidates
 * 
 * 		Level one means one letter, we pass all elements that starts with that letter.
 * 
 * 		Level two means two letters, we pass only elements that starts with those letters
 * 
 * 
 * 		the search in the linkedlist is an heuristic.
 * 
 * 			1) fill the results only until some char (in this case is the next character in the search word)
 * 
 * 			2) calculate higher levels and get the results from those levels, if we are lucky we will receive the enough answer to avoid going through the all alphabet

 * 			3) if we still don't have our max result, we need to traverse the rest of the alphabeth starting from the char at 1) plus 1.
 * 
 * 
 * 		This is very very smart, not that much didatic but a great line of thought.
 * 
 * 
 * @author Nelson Costa
 *
 */
class SearchSuggestionsSystemSolutionUnofficial3 {
	class LinkNode {
		String str;
		LinkNode next;
		LinkNode(String str, LinkNode next) {
			this.str = str;
			this.next = next;
		}
	}

	private LinkNode popMinNode(LinkNode list) {
		if (list == null) {
			return list;
		}

		LinkNode minNode = list;
		LinkNode node = list.next;

		while(node != null) {
			if (node.str.compareTo(minNode.str) < 0) {
				minNode = node;
			}
			node = node.next;
		}

		if (minNode == list) {
			return list;
		}

		String str = minNode.str;
		minNode.str = list.str;
		list.str = str;

		return list;
	}

	private List<String> findMinStrings(int lvl, String searchWord, LinkNode list, List<List<String>> rslts) {
		List<String> rslt = new ArrayList<>();
		rslts.add(rslt);

		if (lvl >= searchWord.length()) {
			while(list != null && rslt.size() < 3) {
				list = popMinNode(list);
				rslt.add(list.str);
				list = list.next;
			}
			return rslt;
		}
		
		/************************
		 * 
		 * Creating the linkedlist based hashmap 
		 * 
		 **********************/
		
		//This works like an hashmap with linked list implementation
		LinkNode[] tbl = new LinkNode[26];//create array list of all the words for this level
		for (LinkNode node = list; node != null; ) {
			if (lvl >= node.str.length()) { //this is actually "==" the other case is impossible
				rslt.add(node.str);
				node = node.next;
				continue;
			}
			int idx = node.str.charAt(lvl)-'a'; //get letter where this element places for the specific level
			LinkNode tmp = node.next; //save current node's next
			node.next = tbl[idx]; //set current nodes next to the next in the array
			tbl[idx] = node; //set the element in the array to the current node
			node = tmp; //set current node equals to next
		}     

		/******************************
		 * 
		 * Getting the minimum Elements, 
		 * from "a" to char at lvl in the search word
		 * 
		 *****************************/
		
		int sIdx = searchWord.charAt(lvl)-'a'; //Heuristic

		//in here we are adding the minimal elements to the resultset
		//and we use popMin to know which of the elements in the same bucket is the minimum
		for (int i = 0; i < sIdx && rslt.size() < 3; i++) {
			while(tbl[i] != null && rslt.size() < 3) {            
				tbl[i] = popMinNode(tbl[i]);
				rslt.add(tbl[i].str);
				tbl[i] = tbl[i].next;
			}
		}

		/******************************
		 *  
		 * set as input of the next level the elements that match the character at "lvl" position of the searchword
		 * 
		 *****************************/
		
		List<String> strs = findMinStrings(lvl+1, searchWord, tbl[sIdx], rslts);
		

		/******************************
		 *  
		 * Adds to the results list the elements the search result of higher levels, 
		 * which means more words in the search
		 * so "m" search can get results from "mo" result
		 * 
		 *****************************/
		
		for (int i = 0; i < strs.size() && rslt.size() < 3; i++) {
			rslt.add(strs.get(i));
		}

		

		/******************************
		 *  
		 * If the we still don't have our solution we do a full search to the complets alphabet
		 * So this is a heuristic that worked pretty well for them. 
		 * only do 26 char search if previous searches didn't soleved the problem already
		 * It was a cool way to circunvent scanning the 26 words everytime
		 * 
		 * Solution for the level is only completed here...
		 * 
		 *****************************/		
		for (int i = sIdx+1; i < 26 && rslt.size() < 3; i++) {
			while(tbl[i] != null && rslt.size() < 3) {            
				tbl[i] = popMinNode(tbl[i]);
				rslt.add(tbl[i].str);
				tbl[i] = tbl[i].next;
			}
		}
		return rslt;
	}

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> rslts = new ArrayList<>();

		LinkNode list = null;
		char firstCh = searchWord.charAt(0);

		//link all nodes that have the same starting character
		for (int i = 0; i < products.length; i++) {
			char ch = products[i].charAt(0);
			if (ch == firstCh) {
				list = new LinkNode(products[i], list);
			}
		}  

		findMinStrings(1, searchWord, list, rslts);       

		return rslts;
	}
}


/**
 * Solution with two pointers
 * 
 * sort the products array, than find the element that starts the answer
 * 
 * than find the element that ends the window
 * 
 * @timetook 6ms
 * 
 * @author happy girl
 *
 */
class SearchSuggestionsSystemSolutionUnofficial2 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> res = new ArrayList<>();
		int n = products.length;
		int lo = 0, hi = n - 1;
		int len = searchWord.length();

		Arrays.sort(products);
		for (int i = 0; i < len; i++) {
			while ((lo <= hi) && (products[lo].length() <= i ||
					products[lo].charAt(i) != searchWord.charAt(i))) {
				lo++;
			}

			while ((lo <= hi) && (products[hi].length() <= i || 
					products[hi].charAt(i) != searchWord.charAt(i))) {
				hi--;
			}

			int min = Math.min(lo + 3, hi + 1);
			List<String> list = new ArrayList<>();
			for (int j = lo; j < min; j++) {
				list.add(products[j]);
			}

			res.add(list);
		}

		return res;
	}
}


/**
 * 
 * simple solution with sorting
 * 
 * sort the array
 * 
 * find the first n elements starting with the suffix
 * 
 * 
 * @timetook 21ms
 * @author Nelson Costa
 *
 */
class SearchSuggestionsSystemSolutionUnofficial1 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Arrays.sort(products);
		int index = 0;
		List<List<String>> result = new ArrayList<>();

		while (index < searchWord.length()) {
			String substr = searchWord.substring(0, index + 1);
			ArrayList<String> subtres = new ArrayList<String>();
			int count = 0;
			for (int i = 0; i < products.length && count < 3; i++) {
				if (products[i].startsWith(substr, 0)) {
					subtres.add(products[i]);
					count++;
				}

			}
			result.add(subtres);
			index++;
		}

		return result;
	}
}