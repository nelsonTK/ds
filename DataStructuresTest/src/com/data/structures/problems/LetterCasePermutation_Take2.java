package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;


/*********************************
* SOLUTION 1
********************************/
/**
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LetterCasePermutation_Take2 {

	/**
	 * @intuition
	 * 		The gist was to pinpoint where are the letters, 
	 * 		save their indexes and permute the indexes to lowercase and uppercase
	 * 		
	 * 		It was possible to solve this without the letters list but i was in a hurry
	 * 		also I could do 1<<N to get the 2^N
	 * 
	 * @param s
	 * @return
	 */
    public List<String> letterCasePermutation(String s) {

        //1) count letters and note their indexes
        List<Integer> letters = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        List<String> ans = new ArrayList<>();
        
        
        for (int i = 0; i < s.length();i++)
        {
            if (Character.isLetter(s.charAt(i)))
            {
                sb.append(s.charAt(i));
                letters.add(i);
            }
            else
            {
                sb.append(s.charAt(i));
            }
        }
        
        //if no letters no case to permutate
        if (letters.size() == 0)
        {
            ans.add(s);
            return ans;
        }
        
        //2) bitmap from 0 to 2^n        
        int permutations = (int)Math.pow(2, letters.size());
        for (int i = 0; i < permutations; i++)
        {
            for (int j = 0; j < letters.size(); j++)
            {
                int idx = letters.get(j);
                                
                if ((i & (1 << j))!= 0)
                {
                    sb.setCharAt(idx, Character.toUpperCase(sb.charAt(idx)));
                }
                else
                {
                    sb.setCharAt(idx, Character.toLowerCase(sb.charAt(idx)));
                }
            }
                                               
            ans.add(sb.toString());
        }
        
        
        //3) if 0 -> lowcase |--| if 1 -> highcase
        return ans;
    }
}
/*********************
* OTHERS SOLUTIONS
*********************/
class LetterCasePermutation_Take2Solution2 {
    public List<String> letterCasePermutation(String S) {
        int B = 0;
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                B++;

        List<String> ans = new ArrayList();

        for (int bits = 0; bits < 1<<B; bits++) {
            int b = 0;
            StringBuilder word = new StringBuilder();
            for (char letter: S.toCharArray()) {
                if (Character.isLetter(letter)) {
                    if (((bits >> b++) & 1) == 1)
                        word.append(Character.toLowerCase(letter));
                    else
                        word.append(Character.toUpperCase(letter));
                } else {
                    word.append(letter);
                }
            }

            ans.add(word.toString());
        }

        return ans;

    }
}