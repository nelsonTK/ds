package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//
	/**
	 * @intuition
	 * 		Nothing fancy was applied, I just broke the paragraph in pieces and when a word is finished I added it to the mapcounter.
	 * 		
	 * 		I also decided to treat the last word edge case after the main for loop
	 * 
	 * @score
	 *      Runtime: 4 ms, faster than 99.39% of Java online submissions for Most Common Word.
        	Memory Usage: 39.5 MB, less than 68.47% of Java online submissions for Most Common Word.
	 * 
	 * 
	 * @time
	 * 		O(B + P + W)
	 * 		B number of banned words
	 * 		P number of paragraph characters
	 * 		W number of words inside the paragraph
	 * 
	 * @space
	 * 		O(B + W)
	 * 		B number of banned words
	 * 		W number of words inside the paragraph
	 **/
	public String mostCommonWord(String paragraph, String[] banned) {

		Set<String> ban = new HashSet<>();
		HashMap<String, Integer> map = new HashMap<>();

		//add banned words to a set
		for (String b : banned)
		{
			ban.add(b);
		}

		//break words and add to map
		StringBuilder cur = new StringBuilder("");
		for (int i = 0; i < paragraph.length(); i++)
		{
			if (Character.isLetter(paragraph.charAt(i)))
			{
				cur.append(Character.toLowerCase(paragraph.charAt(i)));    
			}
			else if (cur.length() != 0)
			{
				updateWord(cur, map, ban);
			}
		}

		//treat last word in case of last character is letter (because it was not treated in the for loop)
		if (Character.isLetter(paragraph.charAt(paragraph.length() - 1 )))
		{
			updateWord(cur, map, ban);
		}

		//find max word
		String ans = "";
		int max = 0;
		for (String k : map.keySet())
		{
			int count = map.get(k);
			if (count > max)
			{
				max = count;
				ans = k;
			}
		}

		return ans;
	}

	private void updateWord(StringBuilder cur, HashMap<String, Integer> map, Set<String> ban){
		String tmp = cur.toString();
		if (!ban.contains(tmp))
		{
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);     
		}

		cur.setLength(0); 
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * TOP SOLUTION
 * 
 * @intuition
 * 	Out of the blue solution where the author creates a trie
 * 	puts the ban elements in the trie
 * 	then he will traverse the paragraph char by char, and add the word to the trie when finished if it is not banned
 * 	so he calculates the answer while traversing the paragraph string.
 * 
 * 	The trie is very simplified version, he dont even records the value for each node, its quite unortodox but it works.
 * 	and is the top solution
 * 
 * 
 * @author Nelson Costa
 *
 */
class MostCommonWordUnofficialSolution1 {
    
    public String mostCommonWord(String paragraph, String[] banned) {
        Trie root = new Trie();
        Trie curr = root;
        // insert banned words into Trie
        for (String ban : banned) {
            for (int i = 0; i < ban.length(); i++) {
                int idx = ban.charAt(i) - 'a';
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            curr.ban = true;
            curr = root;
        }
        int maxCount = 0;
        String mostFreqWord = "";
        paragraph = paragraph.toLowerCase();
        char[] pArray = paragraph.toCharArray();
        // insert words in paragraph into Trie
        for (int start = 0, end = 0; start < pArray.length; start = end + 1) {
            // skip non-letter characters
            while (start < pArray.length && (pArray[start] < 'a' || pArray[start] > 'z')) { start++; }
            // insert consecutive letters(words) into Trie
            for (end = start; end < pArray.length && (pArray[end] >= 'a' && pArray[end] <= 'z'); end++) {
                int idx = pArray[end] - 'a';
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            // update statistics
            if (curr != root && !curr.ban) {
                curr.count++;
                if (curr.count > maxCount) {
                    mostFreqWord = paragraph.substring(start, end);
                    maxCount = curr.count;
                }
            }
            curr = root;
        }
        return mostFreqWord;
    }
    // simplest Trie data structure
    private class Trie {
        private Trie[] next = new Trie[26];    // sub nodes
        private int count;                     // word freqence
        private boolean ban;                   // banned?
    }
}


/**
 * Same concept than mine but I believe that is implemented in a more consused way
 * @author Nelson Costa
 *
 */
class MostCommonWordSolution2 {
    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedWords = new HashSet();
        for (String word : banned)
            bannedWords.add(word);

        String ans = "";
        int maxCount = 0;
        Map<String, Integer> wordCount = new HashMap();
        StringBuilder wordBuffer = new StringBuilder();
        char[] chars = paragraph.toCharArray();

        for (int p = 0; p < chars.length; ++p) {
            char currChar = chars[p];

            // 1). consume the characters in a word
            if (Character.isLetter(currChar)) {
                wordBuffer.append(Character.toLowerCase(currChar));
                if (p != chars.length - 1)
                    // skip the rest of the processing
                    continue;
            }

            // 2). at the end of one word or at the end of paragraph
            if (wordBuffer.length() > 0) {
                String word = wordBuffer.toString();
                // identify the maximum count while updating the wordCount table.
                if (!bannedWords.contains(word)) {
                    int newCount = wordCount.getOrDefault(word, 0) + 1;
                    wordCount.put(word, newCount);
                    if (newCount > maxCount) {
                        ans = word;
                        maxCount = newCount;
                    }
                }
                // reset the buffer for the next word
                wordBuffer = new StringBuilder();
            }
        }
        return ans;
    }
}