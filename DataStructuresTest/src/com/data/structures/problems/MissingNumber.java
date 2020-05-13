package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/missing-number/
 * EASY
 * @author Nelson Costa
 *
 */
public class MissingNumber extends LeetCodeExercise{

	static MissingNumber m = new MissingNumber();
	
	public static void main(String[] args) {
		int [] a = stringToArray("[9,6,4,2,3,5,7,0,1]");
		
		printArray(a);
		
		System.out.println(m.missingNumber(a));
	}

	/**
	 * @intuition 
	 * 		Nothing fancy was applied
	 * 
	 * @this code could be much better.
	 * 
	 * @score
	  		Runtime: 0 ms, faster than 100.00% of Java online submissions for Missing Number.
			Memory Usage: 40.2 MB, less than 98.95% of Java online submissions for Missing Number.
	 * 
	 * 
	 * @fail
	 * 		1) array out of bounds on foreach, if I'm using for each I should not use i but something more descriptive
	 * 		2) failed because I didn't think on zero for cases like [1]
	 * 
	 * @time 	O(N)
	 * @space 	O(1)
	 * @bcr 	O(N)
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber(int[] nums) {

		if (nums == null || nums.length == 0)
			return 0;
		
		
		
		int n = nums.length;
		int actual = 0;
		int expected = n * (n + 1) / 2;
		
		int min = n;
		
		for (int num : nums) {
			actual += num;
			min = Math.min(min, num);
		}
		
		if (min > 0)
			return 0;
				
		return expected == actual ? n + 1 : expected - actual; 
	}
}

/**
 * Same reason as my algorithm but much more efficient
 * @author Nelson Costa
 *
 */
class MissingNumberSolution4 {
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}

/**
 * Interesting solution using bit wise. it bitwises the indexes and the values.
 * only one will be missing.
 * 
 * it bitwizes the indexes, the values and the n (size) all together will result in the number missing, 
 * because it will not have a pair number plus value like the other elements.
 * @author Nelson Costa
 *
 */
class MissingNumberSolution3 {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}



