package com.data.structures.problems.contest;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/map-of-highest-peak/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b46MapofHighestPeak {

	/**
    Runtime: 225 ms, faster than 16.67% of Java online submissions for Map of Highest Peak.
Memory Usage: 201.4 MB, less than 83.33% of Java online submissions for Map of Highest Peak.
Clone Binary Tree With Random Pointer

Course Schedule IV
	 */
	public int[][] highestPeak(int[][] isWater) {

		Set<Integer> set = new HashSet<>();
		Set<Integer> seen = new HashSet<>();



		Queue<Point> points = new ArrayDeque<>();

		int colLen = isWater[0].length;
		int rowLen = isWater.length;

		//reset matrix
		int [][] ans = new int[isWater.length][isWater[0].length];

		//save water points and mark the answer
		for (int r = 0; r < isWater.length; r++)
		{
			for (int c = 0; c < isWater[r].length; c++)
			{
				if (isWater[r][c] == 1)
				{
					points.add(new Point(r, c, colLen));
					ans[r][c] = 0;
				}
				else
				{
					ans[r][c] = Integer.MAX_VALUE;
				}
			}
		}

		int [][] moves = {{-1, 0},{0,1},{1,0},{0,-1}};
		Point cur;


		while (!points.isEmpty())
		{
			cur = points.poll();

			if(set.contains(getId(cur.r, cur.c, colLen)))
				continue;

			int cellValue = Integer.MAX_VALUE;
			// System.out.println(cur.r + " " + cur.c);

			for (int [] move : moves)
			{
				int y = cur.r + move[0];
				int x = cur.c + move[1];

				if (isValid(y, x, rowLen, colLen))
				{
					cellValue = Math.min(ans[y][x], cellValue);

					if (!set.contains(getId(y,x, colLen)) && !seen.contains(getId(y,x, colLen)))
					{
						points.add(new Point(y,x, colLen));
						seen.add(getId(y,x, colLen));
					}
				}
			}

			if (ans[cur.r][cur.c] !=0)
				ans[cur.r][cur.c] = cellValue + 1;

			set.add(getId(cur.r, cur.c, colLen));
		}

		return ans;
	}

	private boolean isValid(int r, int c, int rl, int cl)
	{
		return  (r < 0 || c <0 || r >= rl || c >= cl) ? false : true;

	}

	private int getId(int r, int c, int colLen)
	{
		return r * colLen + c;
	}


	class Point{
		int r;
		int c;
		int colLen;

		public Point(int r, int c, int cl)
		{
			this.r=r;
			this.c=c;
			colLen = cl;
		}

		@Override
		public boolean equals(Object o2)
		{
			Point other = (Point) o2;
			return this.r == other.r && this.c == other.c;
		}

		@Override
		public int hashCode(){
			return r * colLen + c;
		}
	}

}


