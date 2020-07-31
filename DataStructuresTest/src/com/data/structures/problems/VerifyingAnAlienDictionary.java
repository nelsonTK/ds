package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * EASY
 * @author Nelson Costa
 *
 */
public class VerifyingAnAlienDictionary {
	static VerifyingAnAlienDictionary v = new VerifyingAnAlienDictionary();
	public static void main(String[] args) {
		String [] words = new String[]{"word","world","row"};
		String order = "worldabcefghijkmnpqstuvxyz";
		words = new String[]{"apple","app"};
		order = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(v.isAlienSorted(words, order));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		dfs solution to find out of order words.
	 * 		
	 * 		we check the first char of words and group them by the first char.
	 * 		
	 * 		those who are grouped we do dfs on them and advance the index for the next character
	 * 
	 * 		if its all okay we continue the search in the top most level, else the words are out of order.
	 * 
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 54.69% of Java online submissions for Verifying an Alien Dictionary.
			Memory Usage: 39.2 MB, less than 32.18% of Java online submissions for Verifying an Alien Dictionary.
	 * 
	 * 	@fail 	
	 * 		1) the wIndex was too big, was a initialization problem
	 * 		2) forgot to update previous word
	 * 		3) when the last element was part of a group the recursion was not happening
	 * 		4) condition for the edge case of shorter words after bigger word with more chars was wrong
	 * 
	 * @time
	 * 		O(W * C)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 */
	HashMap<Character, Integer> orderMap;
	public boolean isAlienSorted(String[] words, String order) {

		List<Integer> wIndex = new ArrayList<>();
		orderMap = new HashMap<>();

		int index = 0;

		//Create an map with the right order for each character
		for (char c : order.toCharArray()) //O(1)
		{
			orderMap.put(c, index++);
		}

		index = 0;

		//create list of indexes that map to words
		for (String w : words) //O(W)
		{
			wIndex.add(index++);
		}

		return alien(wIndex, 0, words);

	}

	/**
	 * What I do is to group words that start with the same letter, and when they do I dfs them to find if the next letters are in the right order
	 * 
	 * if its all okay I continue the search
	 * 
	 * 
	 * @param wordsIndex
	 * @param charIndex
	 * @param words
	 * @return
	 */
	public boolean alien(List<Integer> wordsIndex, int charIndex, String[] words){

		List<Integer> newGroups = new ArrayList<>();

		int prevLetter = -1;
		int curLetter;
		String word;
		for (int i = 0; i < wordsIndex.size(); i++) //O(W)
		{
			word = words[wordsIndex.get(i)];

			//empty size have to come first else its not ordered
			if (charIndex >= word.length() && (i - 1 >= 0 && words[wordsIndex.get(i - 1)].length() > 0))
				return false;

			//this procedure only applies to non empty strings
			if (charIndex < word.length())
			{
				curLetter = orderMap.get(word.charAt(charIndex));

				if (curLetter < prevLetter)
				{
					return false;
				} 
				else if (curLetter == prevLetter)
				{
					newGroups.add(i);

					if (i == wordsIndex.size() - 1)
						if (!alien(newGroups, charIndex + 1, words))
							return false;
				} 
				else if (curLetter > prevLetter)
				{
					if (newGroups.size() > 1)
					{
						//recurse
						if (!alien(newGroups, charIndex + 1, words))
							return false;
					}

					newGroups = new ArrayList<>();
					newGroups.add(i);
				}

				prevLetter = curLetter;
			}
		}

		return true;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Official solution,
 * Very messy and ugly code
 * 
 * @author Nelson Costa
 *
 */
class VerifyingAnAlienDictionarySolution1 {
	public boolean isAlienSorted(String[] words, String order) {
		int[] index = new int[26];
		for (int i = 0; i < order.length(); ++i)
			index[order.charAt(i) - 'a'] = i;

		search: for (int i = 0; i < words.length - 1; ++i) {
			String word1 = words[i];
			String word2 = words[i+1];

			// Find the first difference word1[k] != word2[k].
			for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
				if (word1.charAt(k) != word2.charAt(k)) {
					// If they compare badly, it's not sorted.
					if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
						return false;
					continue search;
				}
			}

			// If we didn't find a first difference, the
			// words are like ("app", "apple").
			if (word1.length() > word2.length())
				return false;
		}

		return true;
	}
}

/**
 * Very elegant solution that compares each char of one word with the chars of the next word
 * 
 * this like a cleaner version of the official solution
 * 
 * @author Nelson Costa
 *
 */
class VerifyingAnAlienDictionaryUnofficialSolution {

	int[] map = new int[26];

	public boolean isAlienSorted(String[] words, String order) {

		int OGindex = 0;
		//Map each char and its index
		for (char c : order.toCharArray()) {
			map[c - 'a'] = OGindex;           //maps according to new order; sysout
			OGindex++;
		}

		//for each word compare with the following
		//compare char to char for every two words
		for (int i = 0; i < words.length - 1; i++) 
			if (notLex(words[i], words[i + 1]))         //note: it is ('not'Lex)
				return false;   

		return true;
	}

	public boolean notLex(String word1, String word2) {

		int w1 = word1.length();
		int w2 = word2.length();

		for (int i = 0; i < Math.min(w1, w2); i++) 
			if (word1.charAt(i) != word2.charAt(i)) 
				return map[word1.charAt(i) - 'a'] > map[word2.charAt(i) - 'a'];  //#

				return w1 > w2;
	}
}