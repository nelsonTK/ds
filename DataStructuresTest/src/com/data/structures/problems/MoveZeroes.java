package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.performance.BenchMark;
import com.data.structures.performance.BenchMarkInput;
import com.data.structures.performance.BenchMarkInputFactory;
import com.data.structures.performance.FunctionInputIntArray;
import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/move-zeroes/
 * EASY
 * @author Nelson Costa
 *
 */
public class MoveZeroes extends LeetCodeExercise{

	static MoveZeroes m = new MoveZeroes();
	public static void main(String[] args) {
		int [] nums = stringToArray("[0,1,0,3,12]");

		m.moveZeroes0(nums);
		m.benchMark();
	}
	
	/*********************************
	 * BENCHMARK
	 ********************************/	
	@Override
	public void benchMark() {
		super.benchMark();

		MoveZeroes p = new MoveZeroes();
		BenchMark b = new BenchMark();
		BenchMarkInputFactory<FunctionInputIntArray> factory = new BenchMarkInputFactory<>();
		List<BenchMarkInput<FunctionInputIntArray>> benchmarkList = new ArrayList<>();

		benchmarkList.add(factory.create(p::moveZeroesWrapper, "moveZeroesWrapper"));
		benchmarkList.add(factory.create(p::moveZeroes0Wrapper, "moveZeroes0Wrapper"));
		b.benchMarkFunctionInputIntArray(benchmarkList, 0, 5, 1000000, 10);

	}

	private int moveZeroes0Wrapper(int[] nums) {
		moveZeroes0(nums);
		return 0;
	}
	private int moveZeroesWrapper(int[] nums) {
		moveZeroes(nums);
		return 0;
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * [LOW PERFORMANCE SOLUTION]
	 * @intuition
	 * 		The intuition is simple, every time i find a zero from right to left, I switch with the right elemet if it is not zero.
	 * 		and I switch till I find the end of the array
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 7.43% of Java online submissions for Move Zeroes.
	 *		Memory Usage: 40 MB, less than 51.77% of Java online submissions for Move Zeroes.
	 *
	 * 
	 * @comments
	 * 		The toughest part to find out was the worstcase scenario.
	 * 		which happen in my case if there is half big zeros in the beginning and half non zeros in the end.
	 * 
	 * 		in this case I would have to traverse some elements n times.
	 * 
	 * @time 
	 * 		O(N^2) 
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param nums
	 */
	public void moveZeroes0(int[] nums) {    
		if (nums == null || nums.length == 0)
		{
			return;
		}

		int cur;
		int tmp;
		for (int i = nums.length - 1; i >= 0; i--)
		{
			if(nums[i] == 0 && i + 1 < nums.length)
			{
				cur = i;

				while (cur + 1 < nums.length && nums[cur + 1] != 0)
				{
					tmp = nums[cur + 1];
					nums[cur + 1] = nums[cur];
					nums[cur] = tmp;
					cur++;
				}
			}
		}
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		This is a more interesting solution where we use two pointers, 
	 * 		if the left pointer has zeros 
	 * 		and the right pointer should deal with non zeros, we swap the values of them when this conditions are met.
	 * 
	 * 		if left is not zero we have nothing to swap so we move the pointer forward
	 * 		
	 * @score
	 *		Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
	 *		Memory Usage: 39.6 MB, less than 89.59% of Java online submissions for Move Zeroes.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {

		if (nums == null || nums.length == 0)
			return;

		for (int left = 0, right = 0; right < nums.length; right++)
		{
			if(nums[left] == 0 && nums[right] != 0)
			{
				int tmp = nums[left];
				nums[left] = nums[right];
				nums[right] = tmp;
				left++;                
			}
			else if (nums[left] != 0)
			{
				left++;
			}
		}
	}
}
