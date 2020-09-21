package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/rearrange-spaces-between-words/
 * EASY
 * @author Nelson Costa
 *
 */
public class w207RearrangeSpacesBetweenWords {
	/**
	 * @intuition
	 * 		Just implementing.
	 * 		But I could have done much better if I was aware of the strings Api for join, repeat and also spliting the string with regex.
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 66.67% of Java online submissions for Rearrange Spaces Between Words.
	 * 		Memory Usage: 37.5 MB, less than 100.00% of Java online submissions for Rearrange Spaces Between Words.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(W)
	 **/
	public String reorderSpaces(String text) {

		//this solution could be improved by using String.Join, string.repeat()

		int countSpaces = 0;
		int wordCount = 0;
		//learned how to split by spaces
		//String [] words = text.split("\\s+");
		StringBuilder ans = new StringBuilder("");        
		List<String> words = new ArrayList<String>();
		StringBuilder cur = new StringBuilder("");

		int i = 0;
		char c = ' ';
		while(i < text.length())
		{
			//count spaces
			while (i < text.length() && text.charAt(i) == ' ')
			{
				c = text.charAt(i);
				i++;
				countSpaces++;
			}


			//get word
			cur = new StringBuilder("");
			while(i < text.length() && Character.isLetter(text.charAt(i)))
			{
				c = text.charAt(i);
				cur.append(c);
				i++;
			}

			//if word increase word count
			if (!cur.toString().equals(""))
			{
				wordCount++;
				words.add(cur.toString());
			}
		}

		//if one word append to the answer and add the spaces in the end
		if (words.size() == 1)
		{
			ans.append(words.get(0));

			while (countSpaces > 0)
			{
				ans.append(" ");
				countSpaces--;
			}
			return ans.toString();
		}


		//get number of spaces between words
		int spaces = countSpaces/(wordCount - 1);

		//preprocess number of spaces
		StringBuilder sbSpace = new StringBuilder("");
		for (i = 0; i < spaces; i++)
		{
			sbSpace.append(" ");
		}

		//process word and space while not end word
		String w = "";
		for (i = 0; i < words.size(); i++)
		{
			w = words.get(i);

			ans.append(w);

			if(i != words.size()-1){
				countSpaces-=spaces;
				ans.append(sbSpace);
			}
		}

		//remaining
		while(countSpaces > 0)
		{
			ans.append(" ");
			countSpaces--;
		}

		return ans.toString();
	}
}

class RearrangeSpacesBetweenWordsSolution1 {

	public String reorderSpaces(String text) {
		String[] words = text.trim().split("\\s+");
		int cnt = words.length;
		int spaces = text.chars().map(c -> c == ' ' ? 1 : 0).sum();
		int gap = cnt <= 1 ? 0 : spaces / (cnt - 1); 
		int trailingSpaces = gap == 0 ? spaces : spaces % (cnt - 1);
		return String.join(" ".repeat(gap), words) + " ".repeat(trailingSpaces);
	}
}
