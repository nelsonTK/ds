package com.data.structures.problems;

import java.util.PriorityQueue;

public class FindtheMostCompetitiveSubsequence {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 *	@intuition
	 *		Sliding window with priority queue.
	 *		window size n - k
	 *
	 * @score
	 * 		Runtime: 150 ms, faster than 10.21% of Java online submissions for Find the Most Competitive Subsequence.
	 * 		Memory Usage: 52.2 MB, less than 64.41% of Java online submissions for Find the Most Competitive Subsequence.
	 * 
	 * @time
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 **/
	public int[] mostCompetitive(int[] nums, int k) {

		PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) ->
		{
			if (a.val > b.val)
			{
				return 1;
			}
			else if (a.val < b.val){
				return -1;
			}
			else{
				return Integer.compare(a.index, b.index);
			}
		});

		int R = 0, n = nums.length, count = 0, selected = 0;
		int [] ans = new int[k];
		
		while (k > 0)
		{
			//remove invalid digits
			while (!pq.isEmpty() && pq.peek().index < selected)
				pq.poll();

			//add the next candidate
			while (R < n && R <= n - k)
			{
				pq.add(new Pair(nums[R], R));
				R++;
			}

			//update the array
			selected = pq.peek().index;
			ans[count] = pq.poll().val;
			k--;
			count++;
		}
		return ans;
	}


	class Pair {
		int val;
		int index;

		public Pair(int v, int i){
			val = v;
			index = i;
		}
	}
}
