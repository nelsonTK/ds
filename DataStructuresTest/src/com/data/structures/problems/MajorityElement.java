package com.data.structures.problems;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/majority-element/
 * EASY
 * @author Nelson Costa
 *
 */
public class MajorityElement extends LeetCodeExercise{

	public static void main(String[] args) {
		int [] nums = stringToArray("[2,2,1,1,1,2,2]");
//		nums = stringToArray("[3,2,3]");
		printArray(nums);
		MajorityElement m = new MajorityElement();
		System.out.println("ans: " + m.majorityElement(nums));
	}

	
	/**
	 * @score
			Runtime: 7 ms, faster than 47.23% of Java online submissions for Majority Element.
			Memory Usage: 44.7 MB, less than 5.15% of Java online submissions for Majority Element.
	 * @time	N
	 * @space	N
	 * @bcr		N
	 * 
	 * @param nums
	 * @return
	 */
	public int majorityElement(int[] nums) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		int maxFrequency = 0;
		int majorityElement = -1;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()){
			if (entry.getValue() > maxFrequency)
			{
				majorityElement = entry.getKey();
				maxFrequency = entry.getValue();
			}
		}

		return majorityElement;
	}

}

/**
 * The best Solution
 * It essentially counts the majority element.
 * 
 * the first candidate is the first element.
 * if the next element is equals to the candidate we incrise the value of a total sum.
 * if the elemment is diferente we decrease.
 * if the totalsum gets to zero we change candidate.
 * and the process continues.
 * and in the end we return the candidate.
 * 
 * @author Nelson Costa
 *
 */
class MajorityElementSolution2 {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

/**
 * Divide and conquer solution, 
 * is one of the strangest I ever saw because the final answer might not always in the left or right halve
 * It might be necessary to count elements in case of draw between too candidates, in the full scope of right + left.
 * 
 * @intuition
 * 		the most frequent of both halves is the majority element of that left right duel
 * 		If they are equal than we need to count from the begining to see which is the majority element
 * 
 * @time 	N log N
 * @space 	LogN
 * 
 * @author Nelson Costa
 *
 */
class MajorityElementSolution1 {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
}