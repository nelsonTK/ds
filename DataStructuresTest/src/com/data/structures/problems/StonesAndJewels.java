package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 771
 * https://leetcode.com/problems/jewels-and-stones/
 * EASY
 * 
 * Identifiquei que para cada exercicio devo identificar a time complexity e a space complexity
 * @author Nelson Costa
 *
 */
public class StonesAndJewels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(stonesAndJewels("-1", "aaA-AbbaBB"));
		System.out.println(stonesAndJewels2("-1", "aaA-AbbaBB"));
	}

	/**
	 * ntkw -> pass:ml1
	 * https://leetcode.com/problems/jewels-and-stones/
	 * 
	 * FEEDBACK
	 * 		Runtime: 1 ms, faster than 80.68% of Java online submissions for Jewels and Stones.
			Memory Usage: 37.6 MB, less than 5.05% of Java online submissions for Jewels and Stones.
	 * Usar o hashset poderia ser mais apropriado uma vez que não temosvalue apenas a key
	 * @param J
	 * @param S
	 * @return
	 */
	public static int stonesAndJewels(String J, String S) {
		Map<Character, Integer> jewells = new HashMap<>();
		int myJewells = 0;
		char mychar;
		for (int i = 0; i < J.length(); i++) {
			jewells.put(J.charAt(i), 1);			
		}

		for (int i = 0; i < S.length(); i++) {
			mychar = S.charAt(i);
			if(jewells.get(mychar) != null)
				myJewells++;
		}

		return myJewells;
	}


	public static int stonesAndJewels2(String J, String S) {
		Set<Character> jewells = new HashSet<>();
		int myJewells = 0;

		for (Character c : J.toCharArray()) {
			jewells.add(c);			
		}

		for(Character c : S.toCharArray())
		{
			if (jewells.contains(c) )
				myJewells++;
		}

		return myJewells;
	}
}
