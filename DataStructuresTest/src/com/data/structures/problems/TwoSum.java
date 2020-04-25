package com.data.structures.problems;

import java.util.HashMap;
import java.util.Map;

import com.data.structures.utils.PrintHelper;

/**
 * 1
 * https://leetcode.com/problems/two-sum/
 * EASY
 * 
 * @author Nelson Costa
 *
 */
public class TwoSum {

	public static void main(String[] args) {
		TwoSum t = new TwoSum();
		PrintHelper.printArray(t.twoSum(new int[] {2, 2,7,15}, 4));
		PrintHelper.printArray(t.twoSum1(new int[] {2, 2,7, 7,15}, 14));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/	
	/**
	 * Não estou a contar com repetidos
	 * baseado em : https://www.youtube.com/watch?v=BoHO04xVeU0
	 * @param nums
	 * @param target
	 * @return
	 */
	public int [] twoSum1(int [] nums, int target) {
		Map<Integer, Integer> prevIndices = new HashMap<>();
		for(int i = 0; i< nums.length; i++) {
			int solutionNum = target - nums[i];
			prevIndices.put(nums[i], i);
			if (prevIndices.get(solutionNum) != null)
			{
				return new int []{prevIndices.get(solutionNum), i};
			}
			
		}
		return null;
	}
	
	/*********************************
	 * SOLUTION 2
	 ********************************/	
	public int[] twoSum(int[]nums, int target) {
		
		Map<Integer, Integer> prev = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int solutionNum = target - nums[i];
			
			if(prev.get(solutionNum) != null) {
				return new int[] {prev.get(solutionNum), i};
			}
			
			prev.put(nums[i], i);
		}
		return null;
	}
}
