package com.data.structures.problems;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class StringToInteger_atoi_ {
	

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		the rational is simple, remove white spaces
	 * 		form number
	 * 		return number
	 * 		
	 * 		the tricky part and it can probably be improved is the bit that can be probably improved with better math.
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for String to Integer (atoi).
	 *		Memory Usage: 39.1 MB, less than 5.86% of Java online submissions for String to Integer (atoi).
	 * 
	 * @fail
	 * 		1) wrong sign
	 * 		2) skipping the first digit when the sign is omitted
	 * 		3) because my verification for out of bounds was wrong
	 * 		4) failing at negative numbers after changing the verification. missing the sign multiplication
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param str
	 * @return
	 */
    public int myAtoi(String str) {
        
        //if starts with letter truncate word
        if (str == null || str.length() == 0)
            return 0;
        
        int i = 0, sign = 1, num=0;        
        
        //clean spaces
        while (i < str.length() && str.charAt(i) == ' ')
        {
            i++;
        }
        
        //getSign
        if(i < str.length())
        {
            if (str.charAt(i) == '-')
            {
                sign=-1;
                i++;
            }
            else if (str.charAt(i) == '+')
            {
                i++;
            }
            
        }
        
        //build number
        while (i < str.length() && Character.isDigit(str.charAt(i)))
        {
            int newChar = str.charAt(i) - '0';
                        
            //verify multiplication
            
            int tmp = num * 10;
            
            int verification = tmp/10;
            
            if (verification != num)
            {
                if(sign > 0)
                    return Integer.MAX_VALUE;
                else if (sign < 0)
                    return Integer.MIN_VALUE;
            }
            
            //verify sum
            if (tmp + newChar * sign > 0 && sign < 0)
                return Integer.MIN_VALUE;
            else  if (tmp + newChar * sign < 0 && sign > 0)
                return Integer.MAX_VALUE;
            
            
            //math
            num = num * 10 + (newChar)*sign;
            
            i++;
        }
        return num;
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Beautifully written solution, contrary to my solution it avoids getting to overflow with well thought math.
 * 
 * in essence they check if the number is bigger or not to INT_MAX/10 if it is bigger then overflow is garanteed.
 * 
 * If it is equals it depends on weather or not the value to add is bigger thant the remaining of the INT_MAX.
 * 
 * if it is bigger we overflow in case of positive sign, and we might not overflow in negative sign but if we dont overflow we get the min number, if we overflow we return the min num also.
 * 
 * that's it.
 * 
 * @author Nelson Costa
 *
 */
class StringToInteger_atoi_Solution1 {
    public int myAtoi(String str) {
        int i = 0;
        int sign = 1;
        int result = 0;
        if (str.length() == 0) return 0;

        //Discard whitespaces in the beginning
        while (i < str.length() && str.charAt(i) == ' ')
            i++;

        // Check if optional sign if it exists
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-'))
            sign = (str.charAt(i++) == '-') ? -1 : 1;

        // Build the result and check for overflow/underflow condition
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (str.charAt(i++) - '0');
        }
        return result * sign;

    }
}