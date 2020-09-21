package com.data.structures.problems.contest;

import java.util.HashMap;

/**
 * contest 206
 * https://leetcode.com/problems/count-unhappy-friends
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w206CountUnhappyFriends {

	public static void main(String[] args) {

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @score
	 * 		(this was the result after the costest, which does not mean it is accurate, I saw My solution have not even appeared)
	 *		Runtime: 4 ms, faster than 100.00% of Java online submissions for Count Unhappy Friends.
	 *		Memory Usage: 60.1 MB, less than 80.00% of Java online submissions for Count Unhappy Friends.
	 * 		
	 * 
	 * 
	 * @fail
	 *      1) bad interpretation, they should match their preferences is not just the existence of a better possibility
	 *
	 * @timespent
	 *		about 2h40
	 **/
	public int unhappyFriends(int n, int[][] pref, int[][] pairs) {


		//first create array with positions and weight
		int [][] weight = new int[pref.length][pref[0].length + 1];
		int happyFriend = pref[0].length;

		for (int r = 0; r < pref.length; r ++ )
		{
			for (int c = 0; c < pref[r].length; c++)
			{
				weight[r][pref[r][c]] = pref[r].length - c;

			}
		}

		//create graph with pairs and weight
		HashMap<Integer, Pair> graph = new HashMap<>();

		for (int r = 0; r < pairs.length; r++)
		{

			int friend1 = pairs[r][0];
			int friend2 = pairs[r][1];

			graph.put(friend1, new Pair(friend2, weight[friend1][friend2]));
			graph.put(friend2, new Pair(friend1, weight[friend2][friend1]));

		}



		//for each node in the pair check if it is happy (max value), if it isnt check better candidates 1 by one if they would be happier
		int unhappy = 0;
		for (Integer friend : graph.keySet())
		{
			if (graph.get(friend).w == happyFriend)
			{
				continue;
			}
			else
			{
				for (int i = 0; i < pref[0].length; i++)
				{
					//do I have a friend who fits best than my current and I fit best than its current?
					int friend2 = pref[friend][i];
					if (weight[friend][friend2] > graph.get(friend).w && weight[friend2][friend] > graph.get(friend2).w )
					{
						unhappy++;
						break;
					}
				}
			}
		}
		return unhappy;
	}

	class Pair{
		int friend;
		int w;

		Pair(int f, int w)
		{
			friend = f;
			this.w = w;
		}
	}
}
