package com.data.structures.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 * EASY
 * @author Nelson Costa
 *
 */
public class RomanToInteger {


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Solution with HashMap
	 * 
	 * @score 
	 * 		Runtime: 11 ms, faster than 19.46% of Java online submissions for Roman to Integer.
	 * 		Memory Usage: 43.5 MB, less than 5.12% of Java online submissions for Roman to Integer.
	 * 			
	 * @param s
	 * @return
	 */
	HashMap<Character, Integer> dic = new HashMap<>();
	public RomanToInteger()
	{
		dic.put('I', 1);
		dic.put('V', 5);
		dic.put('X', 10);
		dic.put('L', 50);
		dic.put('C', 100);
		dic.put('D', 500);
		dic.put('M', 1000);
	}
	public int romanToInt0(String s) {

		int num = 0;
		for (int i = 0; i < s.length() - 1; i++)
		{
			if (dic.get(s.charAt(i)) >= dic.get(s.charAt(i + 1)))
			{
				num += dic.get(s.charAt(i));
			}
			else
			{
				num -= dic.get(s.charAt(i));
			}
		}

		//last element
		num += dic.get(s.charAt(s.length() - 1));

		return num;
	}
	
	


	/*********************************
	 * SOLUTION 2
	 ********************************/	
	/**
	 * 
	 * @intuition
	 * 		The gist is to have a mapping between chars and ints, 
	 * 		We know we have to subtract the num to the current sum, if the next symbol is bigger than the next character.
	 * 		If not we add to the current symbol value to the current sum.
	 * 		This is the pattern
	 * 
	 * 		Solution without hashmap
	 * 			
	 *		Runtime: 4 ms, faster than 84.12% of Java online submissions for Roman to Integer.
	 *		Memory Usage: 39.7 MB, less than 64.26% of Java online submissions for Roman to Integer.
	 * @param s
	 * @return
	 */
    public int romanToInt(String s) {
        
        int num = 0;
        for (int i = 0; i < s.length() - 1; i++)
        {
            if (getInt(s.charAt(i)) >= getInt(s.charAt(i + 1)))
            {
                num += getInt(s.charAt(i));
            }
            else
            {
                num -= getInt(s.charAt(i));
            }
        }
        
        //last element
        num += getInt(s.charAt(s.length() - 1));
        
        return num;
    }
    
    
    public int getInt(Character c)
    {
        switch(c){
            case  'I': return 1;
            case  'V': return 5;
            case  'X': return 10;
            case  'L': return 50;
            case  'C': return 100;
            case  'D': return 500;
            case  'M': return 1000;
            default: return 0;
        }
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Solution the starts from the end but does pretty much the same as mine
 * 
 * My solution performs better
 * 
 * @author Nelson Costa
 *
 */
class RomanToIntegerSolution3 {
    
    static Map<String, Integer> values = new HashMap<>();
    
    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }

    public int romanToInt(String s) {
        
        String lastSymbol = s.substring(s.length() - 1);
        int lastValue = values.get(lastSymbol);
        int total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            if (currentValue < lastValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            lastValue = currentValue;
        }
        return total;
    }
}