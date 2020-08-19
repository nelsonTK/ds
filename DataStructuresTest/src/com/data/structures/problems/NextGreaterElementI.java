package com.data.structures.problems;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 * EASY
 * @author Nelson Costa
 *
 */
public class NextGreaterElementI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/

	/** 
	 * @intuition
	 * 		use hashmap to save index of the elements
	 * 		
	 * 		use that to have constant time access to elements and then traverse the remaining bigger array
	 * 
	 * @source
	 * 		Runtime: 2 ms, faster than 99.18% of Java online submissions for Next Greater Element I.
	 *		Memory Usage: 39.4 MB, less than 92.56% of Java online submissions for Next Greater Element I.
	 * 
	 * @time
	 * 		O(N*M)
	 * @space
	 * 		O(M)
	 **/
	public int[] nextGreaterElement0(int[] nums1, int[] nums2) {

		if (nums1 == null || nums1.length == 0)
			return new  int [0];

		//O(M)
		HashMap<Integer, Integer> n2Indexes = new HashMap<>();
		for (int i = 0; i < nums2.length; i++)
		{
			n2Indexes.put(nums2[i], i);
		}

		int [] ans = new int[nums1.length];
		int ansIndex = 0;
		//pass and save in hashmap 
		//O(N)
		for (int i = 0; i < nums1.length; i++)
		{
			ans[i] = -1;
			//O(M)
			for(int j = n2Indexes.get(nums1[i]) + 1; j < nums2.length; j++)
			{
				if (nums2[j] > nums1[i])
				{
					ans[i] = nums2[j];
					break;
				}
			}
		}


		return ans;
	}




	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		This is a stack solution for this problem.
	 * 		It is a great way to leverage the inherent properties of the stack.
	 * 		we only keep in the stack elements that we have not found a greater element yet.
	 * 		When we find an element greater than the peek of the stack, we remove the smaller element from the stack
	 * 		and then check if the new pop also is smaller than the current element untill there is no smaller element in the stack
	 * 		and there we add the current element to the stack.
	 * 		
	 * 		In the end we just traverse the array searching for correspondence in the hashmap
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 76.79% of Java online submissions for Next Greater Element I.
	 *		Memory Usage: 39.7 MB, less than 71.85% of Java online submissions for Next Greater Element I.
	 * 
	 * @time 
	 * 		O(N + M)
	 * 
	 * @space
	 * 		O(M)
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {

		if (nums1 == null || nums1.length == 0)
			return nums1;

		Stack<Integer> stack = new Stack<>();
		HashMap<Integer, Integer> nextG = new HashMap<>();
		stack.push(nums2[0]);

		//create next greater Element Array
		for (int k = 1; k < nums2.length; k++)
		{
			int i = nums2[k];
			while (!stack.isEmpty() && i > stack.peek())
			{
				nextG.put(stack.pop(), i);
			}

			stack.push(i);
		}

		//creating the answer
		int [] ans = new int [nums1.length]; 
		int j = 0;
		for (int i : nums1)
		{
			ans[j] = nextG.getOrDefault(i, -1);
			j++;
		}

		return ans;

	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * I like the fact they pop the remaining elements from the stack and put it in the hashmap
 * 
 * This solution is analogous to my solution of the stack
 * 
 * @author Nelson Costa
 *
 */
class NextGreaterElementISolution3 {
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack < Integer > stack = new Stack < > ();
		HashMap < Integer, Integer > map = new HashMap < > ();
		int[] res = new int[findNums.length];
		for (int i = 0; i < nums.length; i++) {
			while (!stack.empty() && nums[i] > stack.peek())
				map.put(stack.pop(), nums[i]);
			stack.push(nums[i]);
		}
		while (!stack.empty())
			map.put(stack.pop(), -1);
		for (int i = 0; i < findNums.length; i++) {
			res[i] = map.get(findNums[i]);
		}
		return res;
	}
}