package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/minimum-knight-moves/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumKnightMoves extends LeetCodeExercise{

	static MinimumKnightMoves m = new MinimumKnightMoves();
	public static void main(String[] args) {

		int x = 2, y = 112;
		//System.out.println(m.minKnightMoves(2, 112));
		System.out.println(m.minKnightMovesBiDirectional(2, 112));
	}

	/*********************************
	 * SOLUTION 1 (MY BEST)
	 ********************************/
	/**
	 * @intuition
	 * 		Important observations:
	 * 			1) the board is symmetric, so you can turn the value absolute.
	 * 			2) except for the case 1, 1, we can cut the search space
	 * 
	 * @score
	 *		Runtime: 32 ms, faster than 83.87% of Java online submissions for Minimum Knight Moves.
	 *		Memory Usage: 51 MB, less than 52.59% of Java online submissions for Minimum Knight Moves.
	 * 
	 * @failed
	 * 		1) Time out because I was creating an infinite loop caused by the hashCode function that was badly typed it missed the uppercase 'C'
	 * 		Its a great lesson to use the overrride annotation. because it points it out quickly.
	 * 
	 * @time
	 * 		O(2^n)
	 * 
	 * @space
	 * 		O(N^2)
	 * 		At each level it is expected the double of the elements in the q, 
	 * 		although it do not happen this way
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int minKnightMoves(int x, int y) {

		x = Math.abs(x);
		y = Math.abs(y);

		Queue<Coordinate> q = new ArrayDeque<>();
		q.add(new Coordinate(0, 0, 0));
		Coordinate cur;
		Coordinate jump;
		Set<Coordinate> seen = new HashSet<>();
		seen.add(q.peek());


		int [] xa = {1, 2, 2, 1, -1, -2, -2, -1};
		int [] ya = {2, 1, -1, -2, -2, -1, 1, 2};

		while (!q.isEmpty())
		{
			cur = q.poll();

			if (cur.x == x && cur.y == y)
				return cur.cost;


			for (int i = 0; i < xa.length; i++)
			{
				jump = new Coordinate(cur.x + xa[i], cur.y + ya[i], cur.cost + 1);


				//cut paths that are un productive
				if (x != 1 && y != 1) {

					if (jump.x < cur.x && jump.x < x)
						continue;

					if (jump.x > cur.x && cur.x > x)
						continue;

					if (jump.y > cur.y && cur.y > y)
						continue;

					if (jump.y < cur.y && cur.y < y)
						continue; 
				}

				if (!seen.contains(jump) )
				{
					q.add(jump);
					seen.add(jump);
				}
			}
		}


		return -1;
	}




	class Coordinate{
		int x;
		int y;
		int cost;

		Coordinate(int x, int y){
			this.x = x;
			this.y = y;
			cost = 0;

		}
		Coordinate(int x, int y, int c){
			this.x = x;
			this.y = y;
			cost = c;

		}
		Coordinate(int x, int y, int c, boolean rev){
			this.x = x;
			this.y = y;
			cost = c;
		}

		@Override
		public boolean equals(Object o)
		{
			Coordinate c = (Coordinate) o;
			return this.x == c.x && this.y == c.y ? true : false;
		}

		@Override
		public int hashCode(){
			return (x * 13 + y);
		}
	}



	/*********************************
	 * SOLUTION 2  (LESS PERFORMANT)
	 ********************************/
	CoordinateSol2 start;
	CoordinateSol2 end;
	/**
	 * 	@intuition
	 * 		This was the first implementation of a bidirectional bfs applied to a problem	
	 * 		it's quite messy but it was cool to have an idea of how it could be done.
	 * 		I just adapted the previous solution and changed the coordinate object a bit to fit my needs
	 * 		
	 * 		I think less memory is consumed
	 * 
	 * @score
	 * 		Runtime: 1498 ms, faster than 8.59% of Java online submissions for Minimum Knight Moves.
	 *		Memory Usage: 211.8 MB, less than 22.15% of Java online submissions for Minimum Knight Moves.
	 *
	 *
	 * @time
	 *		O(2^N)
	 *
	 * @space
	 * 		O(N^2)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int minKnightMovesBiDirectional(int x, int y) {

		if (x == 0 && y == 0)
			return 0;


		x = Math.abs(x);
		y = Math.abs(y);

		start = new CoordinateSol2(0,0);
		end = new CoordinateSol2(x,y);

		Queue<CoordinateSol2> q = new ArrayDeque<>();
		CoordinateSol2 reverseStart = new CoordinateSol2(x, y, 0, true);
		q.add(new CoordinateSol2(0, 0, 0));
		q.add(reverseStart);
		CoordinateSol2 cur;
		CoordinateSol2 jump;
		HashMap<CoordinateSol2, Integer> forward = new HashMap<>();
		HashMap<CoordinateSol2, Integer> reversed = new HashMap<>();
		HashMap<CoordinateSol2, Integer> seen = new HashMap<>();
		forward.put(q.peek(), 0);
		reversed.put(reverseStart, 0);


		int [] xa = {1, 2, 2, 1, -1, -2, -2, -1};
		int [] ya = {2, 1, -1, -2, -2, -1, 1, 2};

		while (!q.isEmpty())
		{
			cur = q.poll();

			if (cur.reversedSearch)
			{
				x = start.x;
				y = start.y;
				seen = reversed;
			}
			else
			{
				x= end.x;
				y= end.y;
				seen = forward;
			}


			for (int i = 0; i < xa.length; i++)
			{
				jump = new CoordinateSol2(cur.x + xa[i], cur.y + ya[i], cur.cost + 1, cur.reversedSearch);

				//cut paths that are un productive
				//the if is a bit hardcode for a specific case, the 1, 1 in the forward search
				if (x != 1 && y != 1 && cur.reversedSearch == false) {

					if (jump.x < cur.x && jump.x < x)
						continue;

					if (jump.x > cur.x && cur.x > x)
						continue;

					if (jump.y > cur.y && cur.y > y)
						continue;

					if (jump.y < cur.y && cur.y < y)
						continue; 
				}

				if (!seen.containsKey(jump) )
				{
					q.add(jump);
					seen.put(jump, jump.cost);

					if(reversed.containsKey(jump) && forward.containsKey(jump))
					{
						return reversed.get(jump) + forward.get(jump);
					}
				}
			}
		}

		return -1;
	}

	class CoordinateSol2{
		int x;
		int y;
		int cost;
		boolean reversedSearch;

		CoordinateSol2(int x, int y){
			this.x = x;
			this.y = y;
			cost = 0;

		}

		CoordinateSol2(int x, int y, int c){
			this.x = x;
			this.y = y;
			cost = c;

		}

		CoordinateSol2(int x, int y, int c, boolean rev){
			this.x = x;
			this.y = y;
			cost = c;
			reversedSearch = rev;
		}

		@Override
		public boolean equals(Object o)
		{
			CoordinateSol2 c = (CoordinateSol2) o;
			return this.x == c.x && this.y == c.y ? true : false;
		}

		@Override
		public int hashCode()
		{
			return (x * 13 + y);
		}
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This is the top 2 solution
 * 
 * DP Based
 * 
 * @author Nelson Costa
 *
 */
class MinimumKnightMovesUnofficialSolution1 {
	// DP - recursive
	//
	// Time: O(2^N)
	// Space: O(max(x, y)) for the recursion stack
	public int minKnightMoves(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);

		int[][] dp = new int[301][301];
		return minKnightMovesSub(x, y, dp);
	}

	private int minKnightMovesSub(int x, int y, int[][] dp) {
		if (x + y == 0) {
			return 0;
		}

		if (x + y == 2) {
			return 2;
		}

		if (dp[x][y] != 0) {
			return dp[x][y];
		}

		int rst = Math.min(
				minKnightMovesSub(Math.abs(x - 2), Math.abs(y - 1), dp),
				minKnightMovesSub(Math.abs(x - 1), Math.abs(y - 2), dp)
				) + 1;

		dp[x][y] = rst;
		return rst;
	}
}
