package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class WordSubsets {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is to compress the B array to have only the max count of letter for all its words.
	 * 		Bcause it is the same testing all letter or testing a compress array that has the max count we can find in each word
	 * 		This way we can avoid traversing all words from both arrays n times.
	 * 
	 * 		
	 * 
	 * @score
	 * 		Runtime: 17 ms, faster than 82.48% of Java online submissions for Word Subsets .  
	 * 		Memory Usage: 47.3 MB, less than 38.37% of Java online submissions for Word Subsets.
	 * 
	 * @time
	 * 		B*W + A*w
	 * 
	 * @space
	 * 		A.length which is the max size.
	 * 
	 * 
	 * **/
	public List<String> wordSubsets(String[] A, String[] B) {

		int [] compressedB = new int[26];
		int [] tmpLetterCount = new int[26]; //tmp array used to calculate compressedB
		String s;

		//get compressed Count
		for (int w = 0; w < B.length; w++)
		{
			s = B[w];
			//count elements
			for (int j = 0; j < s.length(); j++)
			{
				char c = s.charAt(j);
				tmpLetterCount[c-'a']++;
			}

			//clean count letter count and update max
			for (int letter = 0; letter < compressedB.length; letter++ )
			{
				if (compressedB[letter] < tmpLetterCount[letter])
				{
					compressedB[letter] = tmpLetterCount[letter];
				}
				tmpLetterCount[letter] = 0;
			}
		}

		List<String> ans = new ArrayList<>();
		for (int wa = 0; wa < A.length; wa++)
		{
			if (isUniversal(A[wa], compressedB))
			{
				ans.add(A[wa]);
			}
		}

		return ans;
	}


	private boolean isUniversal(String s, int [] compressedB){

		int [] tmp = new int[26];
		for (int i = 0; i < s.length();i++)
		{
			char c = s.charAt(i);
			tmp[c - 'a']++;
		}

		for (int i = 0; i < compressedB.length; i++)
		{
			if (tmp[i] < compressedB[i])
				return false;
		}

		return true;
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * More readable, except the label
 * @author Nelson Costa
 *
 */
class WordSubsetsOficialSolution1 {
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bmax = count("");
        for (String b: B) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; ++i)
                bmax[i] = Math.max(bmax[i], bCount[i]);
        }

        List<String> ans = new ArrayList();
        search: for (String a: A) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; ++i)
                if (aCount[i] < bmax[i])
                    continue search;
            ans.add(a);
        }

        return ans;
    }

    public int[] count(String S) {
        int[] ans = new int[26];
        for (char c: S.toCharArray())
            ans[c - 'a']++;
        return ans;
    }
}
