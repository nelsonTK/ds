package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w234MinimumNumberofOperationstoReinitializeaPermutation {
	/**
	 * @intuition
	 * 	This solution results of the following observation:
	 * 
	 * 	when a number returns to its initial position the whole array has returned to the initial state
	 * 	From this what I do is to track the middle numbers journey until it returns to its initial position
	 * 	So I reversed the math formulas provided to get the next index a number is going, instead of getting the index that will replace it's value
	 * 
	 * 	next = n / 2 + (i - 1) / 2 ======> i = 2 * next - n + 1 
	 * 	next = i / 2 ======> i = next *2
	 * 
	 * 	I called the number I'm following the traveller
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param n
	 * @return
	 */
	public int reinitializePermutation(int n) {

		Traveller t = new Traveller(n);
		t.move();

		while(t.val != t.idx)
		{
			t.move();
		}

		return t.moves;        
	}

	class Traveller{
		int val;
		int idx;
		int boardSize;
		int moves;

		public Traveller(int n){
			this.val = n/2;
			this.idx = n/2;
			this.boardSize = n;
			moves = 0;
		}

		private void move(){
			if (idx >= (boardSize/2))
			{
				idx = 2*idx - boardSize + 1;
			}
			else
			{
				idx = idx*2;
			}
			moves++;
		}
	}
}
