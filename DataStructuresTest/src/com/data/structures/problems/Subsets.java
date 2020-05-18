package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/subsets/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class Subsets extends LeetCodeExercise {

	static Subsets s = new Subsets();
	public static void main(String[] args) {

		int [] nums = stringToArray("[1,2,3]");
		for(List<Integer> l : s.subsets(nums)){
			printArray(l.toArray());
		}
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	
	List<List<Integer>> ans = new ArrayList<List<Integer>>(); // answer
	int [] a;//inputArray
	
	/**
	 * 
	 * @intuition
	 * 		we will start with a set size of 1 and will increment after all combinations for that set size are done.
	 * 		we use backtracting for this.
	 * 		In this solution I wrongly create new object over and over untill the set is completed. 
	 * 		I fix that in the second implementiton
	 * 
	 * @score
			Runtime: 1 ms, faster than 54.88% of Java online submissions for Subsets.
			Memory Usage: 39.8 MB, less than 5.74% of Java online submissions for Subsets.
	 * @fail
	 * 		1) forgot the subset of full size it was a bad clause in the while invariant
	 * 
	 * @time
	 * @space
	 * 
	 * @param nums
	 * @return
	 */
    public List<List<Integer>> subsets0(int[] nums) {
        a = nums;
    	
    	if (a == null || a.length == 0)
    	{
    		return new ArrayList<List<Integer>>();
    	}
    	
    	
    	int setLength = 1;
    	
    	while (setLength <= a.length)
    	{
    		
    		getPowerSet0(0, new ArrayList<Integer>(), setLength);
    		
    		setLength++;
    	}
    	
    	ans.add(new ArrayList<Integer>());
    	
    	return ans;
    }

	private void getPowerSet0(int start, ArrayList<Integer> row, int setLength) {
		
		if (setLength == 0)
		{
			ans.add(row);
			return;
		}
		
		ArrayList<Integer> newRow;
		for (int i = start; i <= a.length - setLength; i++)
		{
			newRow = new ArrayList<>(row);
			newRow.add(a[i]);
			
			getPowerSet(i + 1, newRow, setLength - 1);
		}
	}
	
	/*********************************
	 * SOLUTION 2
	 ********************************/	

	/**
	 * 
	 * @intuition
	 * 		The difference of this implementation is that I don't always create a new row object during the subset set creation.
	 * 		I create the object once and that add and remove objects to return to the previous position.
	 * 
	 * @score
			Runtime: 0 ms, faster than 100.00% of Java online submissions for Subsets.
			Memory Usage: 39.9 MB, less than 5.74% of Java online submissions for Subsets.
			
	 * @fail
	 * 		1) forgot the subset of full size it was a bad clause in the while invariant
	 * 
	 * @time
	 * @space
	 * 
	 * @param nums
	 * @return
	 */
    public List<List<Integer>> subsets(int[] nums) {
        a = nums;
    	
    	if (a == null || a.length == 0)
    	{
    		return new ArrayList<List<Integer>>();
    	}
    	
    	
    	int setLength = 1;
    	
    	while (setLength <= a.length)
    	{
    		
    		getPowerSet(0, new ArrayList<Integer>(), setLength);
    		
    		setLength++;
    	}
    	
    	ans.add(new ArrayList<Integer>());
    	
    	return ans;
    }

	private void getPowerSet(int start, ArrayList<Integer> row, int setLength) {
		
		if (setLength == 0)
		{
			ans.add(new ArrayList<Integer>(row));
			return;
		}
		
		for (int i = start; i <= a.length - setLength; i++)
		{
            row.add(a[i]);
			
			getPowerSet(i + 1, row, setLength - 1);
            row.remove(row.size() - 1);
		}
	}
}
