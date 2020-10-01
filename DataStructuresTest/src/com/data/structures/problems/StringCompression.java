package com.data.structures.problems;

/**
 * https://leetcode.com/problems/string-compression/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class StringCompression {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		This is a two pointer solution.
	 * 		where we traverse the string and at the same time we have a index "pos" that indicates where we have to write.
	 * 		we write the numbers from left to right and we use math to achieve that.
	 * 		
	 * 
	 * 		Is kinda simple, but I took a bit to write, and always need a board to do it
	 * 		
	 * @score
	 * 		Runtime: 1 ms, faster than 96.05% of Java online submissions for String Compression.
	 * 		Memory Usage: 39 MB, less than 84.06% of Java online submissions for String Compression.
	 * 
	 * 
	 * @fail
	 * 		1) forgot to thing when the number is below zero, in doMath
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 **/
	public int compress(char[] chars) {

		int pos = 0,    		// position to write
			count = 1;  		// count per character
		char curChar = ' ';   	// current char


		for (int i = 0 ; i < chars.length; i++)
		{
			curChar = chars[i];

			//if next char different than current we have to place the character and its count
			if (i + 1 < chars.length && chars[i] != chars[i + 1])
			{
				pos = processNumber(chars, pos, curChar, count);

				count = 1;
			}
			else if (i + 1 < chars.length && chars[i + 1] == chars[i])
			{
				count++;
			}
		}

		if (count > 0)
		{
			pos = processNumber(chars, pos, curChar, count);
		}

		return pos;
	}

	private int processNumber(char[] chars, int pos, char curChar, int count)
	{
		//place character
		chars[pos] = curChar;
		pos++;

		if (count == 1)
		{
			return pos;
		}

		//place count
		int iterations = (int)(Math.log10(count) + 1);

		while (iterations > 0 && pos < chars.length)
		{
			int [] math = doMath(count);
			chars[pos] = (char)('0' + math[0]);
			count = math[1];
			pos++;
			iterations--;
		}

		return pos;
	}

	//gets the left most and the number without the leftmost
	private int[] doMath(int num){
		if (num<10)
		{
			return new int[]{num, 0};
		}
		int exp = (int)Math.log10(num);
		int base10 = (int) Math.pow(10, exp);
		int leftMost = num/base10;
		return new int[]{leftMost, num - (base10 * leftMost)};
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * My solution just smashes the official solution, ahaha
 * 
 * However, my solution is much more complex and difficult to write and test
 * 
 * WHY?
 * 
 * They count the number of characters differently, they count the characters by using the difference between an anchor and the current position.
 * 
 * is a good case of a solution that is not perfect but is easy to implement
 * 
 * good job guys
 * 
 * @author Nelson Costa
 *
 */
class StringCompressionSolution1 {
    public int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }
}