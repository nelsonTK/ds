package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LetterCombinationsOfAPhoneNumber extends LeetCodeExercise{

	static LetterCombinationsOfAPhoneNumber l = new LetterCombinationsOfAPhoneNumber();
	
	public static void main(String[] args) {
		String digits = "23";

		for (String s : l.letterCombinations(digits))
		{
			System.out.println(s);
		}
		
	}

    HashMap<Character, ArrayList<Character>> map = new HashMap<>();
    
    public LetterCombinationsOfAPhoneNumber(){
        loadMap();
    }
    
	/*********************************
	 * SOLUTION 1
	 ********************************/    
    /**
     *    
     * @intuition
     *  	It's a combination/Permutation exercise
     *  	pretty strait forward
     *  	I loaded the map with the combinations of letters per number 
     *  	so that I can reuse it if the object is called multiple times
     *  	
     *  	used stringbuilder to avoid rewriting all the time the string
     *  	
     *  	and that's it
     *  
     *  
     * @score
     *   	Runtime: 1 ms, faster than 81.99% of Java online submissions for Letter Combinations of a Phone Number.
     *  	Memory Usage: 38.3 MB, less than 76.12% of Java online submissions for Letter Combinations of a Phone Number.
     *  
     * @time   O(3^L)
     * @space  O(1)
     *  
     **/
    public List<String> letterCombinations(String digits) {
        
        //guards 
        if (digits == null || digits.length() == 0)
            return new ArrayList<String>();
        
        List<String> ans = new ArrayList<>();
        
        generate(digits, ans, 0, new StringBuilder(""));
        
        return ans;        
    }
    
    private void generate(String digits, List<String> ans, int index, StringBuilder currWord)
    {        
        if (digits.length() == currWord.length())
        {
            ans.add(currWord.toString());
            return;
        }
        
        for (Character c : map.getOrDefault(digits.charAt(index), new ArrayList<Character>()))
        {
            currWord.append(c);
            generate(digits, ans, index + 1, currWord);
            currWord.setLength(currWord.length() - 1);
        }
    }
    
    private void loadMap(){
        
        this.map = new HashMap<>();
        
        int letterSize;
        char letter = 'a' - 1;
        for (int num = 2; num < 10; num++)
        {
            letterSize = (num == 7 || num == 9 ) ? 4 : 3;
            
            for (int letterIdx = 0; letterIdx < letterSize; letterIdx++)
            {
                letter++;
                map.computeIfAbsent((char)(num + '0'), k -> new ArrayList<Character>()).add(letter);
            }
        }
    }
}
