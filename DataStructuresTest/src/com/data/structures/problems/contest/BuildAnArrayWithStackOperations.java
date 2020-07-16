package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

	/**
	 * https://leetcode.com/problems/build-an-array-with-stack-operations/
	 * EASY
	 * @param args
	 */
public class BuildAnArrayWithStackOperations extends LeetCodeExercise{

	/**
	 * https://leetcode.com/problems/build-an-array-with-stack-operations/
	 * EASY
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * My First Contest Question
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Build an Array With Stack Operations.
			Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Build an Array With Stack
	 * 
	 * 
	 * @param a
	 * @param n
	 * @return
	 */
    public List<String> buildArray(int[] a, int n) {
        
        if (a == null || a.length == 0)
        {
            return new ArrayList<String>();
        }
        
        int filler = 1;
        List<String> ans = new ArrayList<String>();
        
        for (int i = 0 ; i  < a.length; i++)
        {
            while (a[i] > i + filler)
            {
                ans.add("Push");
                ans.add("Pop");
                filler++;
            }
            
            if  (a[i] == i + filler)
            {
                ans.add("Push");
            }
        }
        
        return ans;
    }
}
