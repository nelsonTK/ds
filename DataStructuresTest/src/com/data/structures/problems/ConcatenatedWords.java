package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {

	static ConcatenatedWords c = new ConcatenatedWords();
	static ConcatenatedWordsUnofficialSolution1 c2 = new ConcatenatedWordsUnofficialSolution1();
	public static void main(String[] args) {

		String[] str = new String[]{"cat", "cats", "catcatx"};
		c2.findAllConcatenatedWordsInADict(str);
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	@intuition
		I always match the smallest word possible

		from there I check for the the rest of the string for another word match.

		before testing a complete word I test if there is any word starting with that first character.

		if there is, I continue for full verification. If there is not, I cut the investigation there. 

		And go back to the previous word and increase the length of the word until we finish it.

		I return the length of words used to completion


	@optimization
		An optimization could be to have a record of the shortest word, and then we always start from that size


    @score
        Runtime: 74 ms, faster than 63.12% of Java online submissions for Concatenated Words.
        Memory Usage: 46.2 MB, less than 65.61% of Java online submissions for Concatenated Words.

    @time
        O(N) + O(N*M^2)
    @space
    	O(N)

    @fail
        1) failed for empty strings, i did not think of it in the algorithm
        2) there where more places where the empty string was screwing the algorithm
	 */
	public List<String> findAllConcatenatedWordsInADict0(String[] words) {

		if(words == null || words.length <= 1)
			return new ArrayList<String>();

		List<HashSet<String>> array = new ArrayList<HashSet<String>> (26);
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 26; i ++)
		{
			array.add(null);
		}


		for (String word: words) //O(N), N number of words
		{
			if (word.equals(""))
				continue;

			if (array.get((int) (word.charAt(0) - 'a')) == null)
			{
				array.set((int) (word.charAt(0) - 'a'), new HashSet<String>());
			}
			array.get((int) (word.charAt(0) - 'a')).add(word);
		}


		for (String s : words) //O(N)
		{
			if (findWords0(0, s, array) > 1)
				result.add(s);
		}


		return result; 
	}

	//returns >0 if the words exists, and the value represents the number of words. only more than one word words
	//returns -1 if we cannot compose the word of other elements
	// 
	private int findWords0(int start, String s, List<HashSet<String>> array){

		if (s.length()==0)
			return -1;

		if (start >= s.length())
			return 0; //we made it to the end

		if (array.get((int) (s.charAt(start) - 'a')) == null)
			return -1; //we can stop searching, there are no words starting with that char

		int result = -1;

		for (int i = start; i < s.length(); i++) //O(M)
		{
			if (array.get((int) (s.charAt(start) - 'a')).contains(s.substring(start, i + 1))) //O(M)
			{
				result = findWords0(i + 1, s, array);

				if (result >= 0)
					return result + 1;
			}
		}

		return result;
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @score
		    Runtime: 63 ms, faster than 71.36% of Java online submissions for Concatenated Words.
		    Memory Usage: 46.5 MB, less than 61.53% of Java online submissions for Concatenated Words.     
				    
		    Small optimization to the algorithm above where I introduce the minimum size of a word
		    to have the best chances wright from the start.
		    
	 * @param words
	 * @return
	 */
	public List<String> findAllConcatenatedWordsInADict(String[] words) {

		//guards
		//empty list
		//one element
		if(words == null || words.length <= 1)
			return new ArrayList<String>();

		List<HashSet<String>> array = new ArrayList<HashSet<String>> (26);
		List<String> result = new ArrayList<>();
		int minWordSize = Integer.MAX_VALUE;

		for (int i = 0; i < 26; i ++)
		{
			array.add(null);
		}


		for (String word: words) //O(N), N number of words
		{
			if (word.equals(""))
				continue;

			if (array.get((int) (word.charAt(0) - 'a')) == null)
			{
				array.set((int) (word.charAt(0) - 'a'), new HashSet<String>());
			}
			array.get((int) (word.charAt(0) - 'a')).add(word);
			minWordSize = Math.min(minWordSize, word.length());
		}

		for (String s : words) //O(N)
		{
			if (findWords(0, s, array, minWordSize) > 1)
				result.add(s);
		}


		return result; 
	}

	//returns >0 if the words exists, and the value represents the number of words. only more than one word words
	//returns -1 if we cannot compose the word of other elements
	// 
	private int findWords(int start, String s, List<HashSet<String>> array, int minWordSize){

		if (s.length()==0)
			return -1;

		if (start >= s.length())
			return 0; //we made it to the end

		if (start + minWordSize > s.length())
			return -1;

		if (array.get((int) (s.charAt(start) - 'a')) == null)
			return -1; //we can stop searching, there are no words starting with that char

		int result = -1;

		for (int i = start + minWordSize; i <= s.length(); i++) //O(M^2)
		{
			if (array.get((int) (s.charAt(start) - 'a')).contains(s.substring(start, i ))) //O(M^2)
			{
				result = findWords(i , s, array, minWordSize);

				if (result >= 0)
					return result + 1;
			}
		}

		return result;
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
 * Very cleaver
 * 
 * 1) uses min to start already with possibility to match.
 * 2) when something matches it checks if the rest of the string exist in the set and mades a second word, if not goes deeper in the dfs
 * 
 * @author Nelson Costa
 *
 */
class ConcatenatedWordsUnofficialSolution1 {
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Set<String> set = new HashSet<>(10000);
		List<String> res = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for (String word : words) {
			if (word.length() == 0) {
				continue;
			}
			set.add(word);
			min = Math.min(min, word.length());
		}
		for (String word : words) {
			if (check(set, word, 0, min)) {
				res.add(word);
			}
		}
		return res;
	}

	private boolean check(Set<String> set, String word, int start, int min) {
		for (int i = start + min; i <= word.length() - min; i++) {
			if (set.contains(word.substring(start, i)) &&
					(set.contains(word.substring(i)) || check(set, word, i, min))) {
				return true;
			}
		}
		return false;
	}
}