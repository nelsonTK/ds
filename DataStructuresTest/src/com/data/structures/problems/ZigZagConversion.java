package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ZigZagConversion {

	public static void main(String[] args) {
		String s = "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.";
		ZigZagConversion z = new ZigZagConversion();
		z.convert(s, 5);
		
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
    /** 
     * @intuition 
     * 		was to create a matrix with the zigzag and here the challenge is to guess the length we need to have.
     * 		The number I used was achieved by math, but later it has revealed somehow too short.
     * 		In order to make this exercise work I hard to do trial and error on the size of the matrix. 
     * 		There should be a better way.
     * 
     * 
     * 		After some tweaking I get to the conclusion that I can divide in the formula just by the rows.
     * 		because its not expected that the diagonal can be larger than the vertical
     * 
     * @score
     * 		Runtime: 108 ms, faster than 5.01% of Java online submissions for ZigZag Conversion.
	 *		Memory Usage: 95.9 MB, less than 5.02% of Java online submissions for ZigZag Conversion.
     * 
     * @optimization
     * 		1) An optimization would be to put each row together in one hashmap. instead of using a matrix. and then I would just go over the hashmap and would had better efficience of space used and time
     * 
     * 		2) would be a combination of hashmap + linear scan.
     * 		we start by descending and mark each char in a hashmap, when we start ascending we continue putting the chars in the  hashmap.
     * 
     * 			a) linear scan the string
     * 			b) use variable to control if your reach length or zero
     * 			c) have something to mark if you are going up or down
     * 			d) use hashmap to keep track of the rows
     * 			e) traverse the map from row 0 to N to gather the final answer
     * 
     * @fail
     * 		1) infinite loop
     * 
    * @time
    *       O(getColumnLength(s.size, rows) * rows)
    * 
    * @space
    *       O(getColumnLength(s.size, rows) * rows)
    *
    **/
    public String convert(String s, int rows) {
            
        if (s == null || s.length() == 0)
            return "";
        
        if (rows <= 0)
            return "";
        
        if (rows == 1)
            return s;
            
        
        Character [][] mtx = new Character[rows][getColumnLength(s.length(), rows)];
        char cur;
        int r = -1, c = 0, i = 0;
        boolean used = true;
        //create ZigZag Matrix 
        while (i < s.length() )
        {
        	used = false;
            //System.out.println("a");
            while (i < s.length() && r + 1 < mtx.length)//canGoDown(mtx, r, c))
            {
                r++;
                cur = s.charAt(i);
                mtx[r][c] = cur;                
                i++;
                used = true;
            }
            
            while (i < s.length() && r - 1 >= 0 && c + 1 < mtx[r].length)// canGoDiagonal(mtx, r, c))
            {
                r--;
                c++;
                cur = s.charAt(i);
                mtx[r][c] = cur;
                i++;
                used = true;
            }
        }
        
        //create zigzag string
        StringBuilder aux = new StringBuilder("");
        for (int row = 0; row < mtx.length; row++)
        {
            for (int col = 0; col < mtx[r].length; col++)
            {
                if (mtx[row][col] != null)
                {
                    aux.append(mtx[row][col]);
                }
            }
        }
        
        return aux.toString();
        
    }
    
    
    private int getColumnLength(int size, int rows){
        //return (int) Math.ceil(size / (double)(rows + rows/2)) * (1 + rows/2);
    	//consider diagonals the size of verticals. so I can divide total size by the number of rows.
    	// with the result I multiply each block by 1 (vertical takes one column) plus (expected maxc diagonal size which might be wrong)
    	//this is an heuristic.
        return (int) Math.ceil(size / (double)(rows)) * (1 + rows/2);
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * this solution is similar to a hashmap solution
 * 
 * you create a mapping between rows and entries
 * 
 * later you just join the rows in the final answer
 * 
 * elegant
 * 
 * @author Nelson Costa
 *
 */
class ZigZagConversionSolution1 {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        //creates entries for rows
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            
            //when limits are reach the direction changes
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}