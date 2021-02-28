package com.data.structures.problems;

public class StoneGame {


	/**
	 * @intuition
	 * 		
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Stone Game.
	 * 		Memory Usage: 36.4 MB, less than 96.61% of Java online submissions for Stone Game.
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 **/
	public boolean stoneGame(int[] piles) {

		return play(piles, 0, piles.length, 0, 0, true);

	}

	private boolean play(int[] piles, int start, int end, int aPoints, int lPoints, boolean Alex)
	{
		if (start > end || start >= piles.length || end < 0)
		{
			return Math.abs(aPoints) > Math.abs(lPoints);
		}


		if (Alex)
		{
			return play(piles, start+1, end, aPoints+piles[start], lPoints, !Alex) || play(piles, start, end, aPoints+piles[end], lPoints, !Alex);

		}
		else
		{
			return play(piles, start+1, end, aPoints+-1*piles[start], lPoints, !Alex) || play(piles, start, end, aPoints+-1*piles[end], lPoints, !Alex);

		}
	}
}
