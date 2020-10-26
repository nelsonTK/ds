package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _4Sum {

	Set<List<Integer>> repeated;
	/**
	 * @intuition
	 * 		The intuition was to do the combinations of all possible cases.
	 * 		Probably there are much more efficient ways of do it.
	 * 
	 * 
	 * @score
	 * 		Runtime: 2714 ms, faster than 5.00% of Java online submissions for 4Sum.
	 *		Memory Usage: 39.5 MB, less than 6.94% of Java online submissions for 4Sum.
	 * 
	 * @fail
	 * 		failed because of repeated combinations
	 * 		failed multiple times because I didn't have the list sorted
	 * 		time limit exceeded I was sorting every time I had a new result, I change it to sort the initial array
	 * 		
	 * @time
	 * 		O(NLogN) or O(Combinations), is the biggest of the two
	 * 		Actually it might be 2^N
	 * @space
	 * 		O(Combinations * 4)
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		repeated = new HashSet<>();
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		if (nums == null || nums.length == 0)
		{
			return ans;
		}

		find(nums,target, 0, new ArrayList<Integer>(), ans, 0);


		return ans;
	}

	private void find(int [] nums, int target, int start, List<Integer> cur, List<List<Integer>> ans, int curSum){

		//found
		if (cur.size() == 4 )
		{
			if (curSum == target)
			{
				ArrayList<Integer> combination = new ArrayList<Integer>(cur);
				if(!repeated.contains(combination))
				{
					ans.add(combination);
					repeated.add(combination);
				}
				return;
			}
			else
			{
				return;
			}
		}

		//early stop if there is no room for 4 nums

		if (nums.length - start < 4 - cur.size())
		{
			return;
		}

		//if out of bounds stop
		if(start >= nums.length)
		{
			return;
		}

		//process combinations
		for(int i = start; i < nums.length; i++)
		{
			cur.add(nums[i]);

			find(nums, target, i+1, cur, ans, curSum + nums[i]);

			cur.remove(cur.size()-1);
		}
	}
}
