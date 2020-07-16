package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 * EASY (Very)
 * @author Nelson Costa
 *
 */
public class RangeSumQueryImmutable {

	public static void main(String[] args) {

	}

	class NumArray{	


		/*********************************
		 * SOLUTION 1
		 ********************************/

		/**
		 * @intuition
		 * 		My intuition to find the sum between i and j, 
		 * 		was you just have to subtract the comulative sum of i by the comulative sum of j
		 * 		which gives you the difference between i a j, and then you add j to have it inclusive.
		 * 
		 * 		WHY ADD J??
			  		because the way I arranged this was like create a comulative sum but from right to left.
			  		so the value comulates from right to left. i is bigger than j.
			  		i has all the sum untill i. j has all the sum until j.
			  		if you subtract than you need to add j again.
		 * 	
		 * 
		 * 		so this is grounded on the principle that the difference between two commulative points is the sum between those two points
		 * 
		 * 
		 * 		EASIER WAY
		 * 			an easier way would be to subtract commulative j by commulative i 
		 * 			inclusively much more intuitive
		 * 
		 * @score
		 * 		Runtime: 8 ms, faster than 35.54% of Java online submissions for Range Sum Query - Immutable.
				Memory Usage: 44.3 MB, less than 19.51% of Java online submissions for Range Sum Query - Immutable.

		 * @time  O(1)
		 * @space O(N)
		 * 
		 *
		 */
		int [] nums;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		public NumArray(int[] n, int dummy) { 
			this.nums = n;

			int totalsum = 0;
			for (int i : nums)
			{
				totalsum =+ i;            
			}

			int comulative = 0;

			for (int i = 0; i < nums.length; i++)
			{
				map.put(i, totalsum - comulative);
				comulative += nums[i];
			}
		}

		public int sumRange0(int i, int j) { //O(1)
			//i = i >= nums.length? nums.length-1 : i < 0? 0: i;
			// j = j >= nums.length? nums.length-1 : j < 0? 0: j;
			return map.get(i) - map.get(j) + nums[j];
		}
		
	}


}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * DP Solution
 * but the logic is very similar to mine but with different math
 * 
 * and more elegant filling of the sum array.
 * The commulative sum is made by using the array that will have the sum, and the input array
 * 
 * 
 * @author Nelson Costa
 *
 */
class RangeSumQueryImmutableSolution1
{

	class NumArray{
		private int[] sum;

		public NumArray(int[] nums) {
			sum = new int[nums.length + 1];
			for (int i = 0; i < nums.length; i++) {
				sum[i + 1] = sum[i] + nums[i];
			}
		}

		public int sumRange(int i, int j) {
			return sum[j + 1] - sum[i];
		}
	}
}
