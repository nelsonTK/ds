package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break-ii/
 * HARD
 * @author Nelson Costa
 *
 */
public class WordBreakII_Take2 {
	static WordBreakII_Take2 w = new WordBreakII_Take2();
	public static void main(String[] args) {
		String s = "aaaaaaaaaaaaaaaaaaaaab";
		List<String> wordDict = Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});	
		System.out.println(Arrays.toString(w.wordBreak(s, wordDict).toArray()));
	}

	List<List<String>> memo;
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 	@intuition
	 * 		this is a memoization solution where I go to the bottom and them from the bottom I build my solution.
	 * 		
	 * 
	 * 		1) I always find the smallest matchable word, than I test the rest, and I do this till I get to the end of the string.
	 * 		
	 * 		2) at each search word after we end we put the number of words possible for that searchword. and we put that possibility in the index that matches the start of the word.
	 * 
	 *  	3) the last word can only have one word in the memo list.
	 *  
	 *  		3.1) the second word can have at maximum 2 words, and so on. it's 2^N
	 *  
	 *  		3.2) the worst case is "aaaaaaaaaa"
	 *  
	 *  	4) by only moving forward after finding a word we avoid processing all the possibilities
	 *  		aaaaaaaaaab
	 *  		if the words were "a aa aaa aaaa aaaaa aaaaaa aaaaaaa"
	 *  
	 *  	5) cache the nodes we already processed are cached, that's where we get most of the performance
			   			 	combinações
			   	MEMO	 	aaa
			  	MEMO	  	aa a
			  	MEMO	  	a a a		aa
			  	MEMO		a aa
			  	String ->	a 			a     		a (aaa)
			   	INDEX		0			1			2
		   		
		   		Strings Validas - "a" "aa" "aaa"
		   	
		   	6)	A resposta está no primeiro elemento do Memo. 
		   	
	 * 
	 *  @score
	 *  	Runtime: 13 ms, faster than 31.59% of Java online submissions for Word Break II.
	 *			Memory Usage: 43.9 MB, less than 7.87% of Java online submissions for Word Break II.
	 *		
	 *	@fail
	 *	        1) I failed to know that lists do not have a size in its constructor
	 *
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreak(String s, List<String> wordDict) {


		//DP Memo
		memo = new ArrayList<List<String>>();
		for (int i = 0; i < s.length(); i++)
		{
			memo.add(null);
		}

		//Fill the set
		Set<String> wordMap = new HashSet<>();
		for (String st : wordDict)
		{
			wordMap.add(st);
		}

		//call a word break
		breakWord(s, wordMap, 0);

		return memo.get(0);
	}


	private void breakWord(String s, Set<String> wordMap, int start)
	{

		if (start >= s.length())
		{
			return;
		}

		if (memo.get(start) != null)
			return;

		List<String> ans = new ArrayList<>();
		String word = "";
		for (int i = start; i < s.length(); i++)
		{
			word = s.substring(start, i + 1);

			if (wordMap.contains(word))
			{
				breakWord(s, wordMap, i + 1);

				if (i + 1 < s.length() && memo.get(i + 1) != null)
				{
					for(String piece : memo.get(i + 1))
					{
						ans.add(word + " " + piece);
					}
				}
				else if (i + 1 == s.length())
				{
					ans.add(word);
				}
			}
		}
		memo.set(start, ans); 
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
class WordBreakII_Take2Solution1 {
    protected Set<String> wordSet;
    protected HashMap<String, List<List<String>>> memo;

    public List<String> wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        memo = new HashMap<String, List<List<String>>>();

        _wordBreak_topdown(s);

        // chain up words together
        List<String> ret = new ArrayList<String>();
        for (List<String> words : memo.get(s)) {
            StringBuffer sentence = new StringBuffer();
            for (String word : words) {
                sentence.insert(0, word);
                sentence.insert(0, " ");
            }
            ret.add(sentence.toString().strip());
        }

        return ret;
    }

    protected List<List<String>> _wordBreak_topdown(String s) {
        if (s.equals("")) {
            List<List<String>> solutions = new ArrayList<List<String>>();
            solutions.add(new ArrayList<String>());
            return solutions;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        } else {
            List<List<String>> solutions = new ArrayList<List<String>>();
            memo.put(s, solutions);
        }

        for (int endIndex = 1; endIndex <= s.length(); ++endIndex) {
            String word = s.substring(0, endIndex);
            if (wordSet.contains(word)) {
                List<List<String>> subsentences = _wordBreak_topdown(s.substring(endIndex));
                for (List<String> subsentence : subsentences) {
                    List<String> newSentence = new ArrayList<String>(subsentence);
                    newSentence.add(word);
                    memo.get(s).add(newSentence);
                }
            }
        }
        return memo.get(s);
    }
}
