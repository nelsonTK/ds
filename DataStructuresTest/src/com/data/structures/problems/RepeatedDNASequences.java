package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * MED
 * @author Nelson Costa
 *
 */
public class RepeatedDNASequences {

	/**
	 * @intuition
	 * 		The intuition is to create a sliding window with a mask and always keep the mask with 20 length.
	 * 		because each character is uses 2 bits.
	 * 
	 * @score
	 * 		Runtime: 17 ms, faster than 57.88% of Java online submissions for Repeated DNA Sequences.
	 * 		Memory Usage: 43.8 MB, less than 94.54% of Java online submissions for Repeated DNA Sequences.
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @space

very cool problem to solve with bitmap

https://leetcode.com/problems/repeated-dna-sequences/discuss/280513/repeated-dna-sequences-java-array-and-bit-manipulation

	 **/
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> ans = new ArrayList<>();
		HashSet<Integer> masks = new HashSet<>();
		HashSet<Integer> repeatedMasks = new HashSet<>();


		HashMap<Character, Integer> letterHash = new HashMap<>();
		letterHash.put('A', 0);
		letterHash.put('C', 1);
		letterHash.put('G', 2);
		letterHash.put('T', 3);

		int mask = 0;

		//fill up the window untill nine element
		for(int i = 0; i < Math.min(9, s.length()); i++)
		{
			mask = (mask <<  2) | letterHash.get(s.charAt(i));
		}

		for (int i = 9; i < s.length(); i++)
		{
			//add char to mask
			mask = (mask <<  2) | letterHash.get(s.charAt(i));
			mask = mask & ((1 << 20) - 1);

			if (!masks.contains(mask))
			{
				masks.add(mask);
			}
			else if (masks.contains(mask) && !repeatedMasks.contains(mask))
			{
				//System.out.println(mask);
				repeatedMasks.add(mask);
				ans.add(s.substring(i - 9, i + 1));
			}
		}

		return ans;
	}
}


/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/

class RepeatedDNASequencesUnofficialSolutions {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> resultList = new ArrayList<>();
		Set<Integer> words = new HashSet<>();
		Set<Integer> repeatedWords = new HashSet<>();

		int[] map = new int[26];
		map['A' - 'A'] = 0;
		map['C' - 'A'] = 1;
		map['G' - 'A'] = 2;
		map['T' - 'A'] = 3;

		int mask = 0;

		for(int i = 0; i < s.length(); i++) {
			mask = (mask << 2) | map[s.charAt(i) - 'A'];
			if(i >= 9) {
				mask = mask & ((1 << 20) - 1); // to consider only last 20 bits // ((1<<20)-1) creates a mask of 20 1s
				if(!words.add(mask) && repeatedWords.add(mask)) {
					resultList.add(s.substring(i - 9, i + 1));  // if repeated addd to resultList
				}
			}
		}

		return resultList;
	}
}
