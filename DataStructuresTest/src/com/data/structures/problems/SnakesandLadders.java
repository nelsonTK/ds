package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SnakesandLadders extends LeetCodeExercise{
	static SnakesandLadders s = new SnakesandLadders();
	static SnakesandLaddersSolution1 s2 = new SnakesandLaddersSolution1();
	public static void main(String[] args) {
		int [][] board = stringToMatrix("[\r\n" + 
				"[-1,-1,-1,-1,-1,-1],\r\n" + 
				"[-1,-1,-1,-1,-1,-1],\r\n" + 
				"[-1,-1,-1,-1,-1,-1],\r\n" + 
				"[-1,35,-1,-1,13,-1],\r\n" + 
				"[-1,-1,-1,-1,-1,-1],\r\n" + 
				"[-1,15,-1,-1,-1,-1]]");
		board = stringToMatrix("\r\n" + 
				"[[-1,7,-1],"
				+ "[-1,6,9],"
				+ "[-1,-1,2]]");
		
		board = stringToMatrix(""
				+ "[[-1,1,2,-1],"
				+ "[2,13,15,-1],"
				+ "[-1,10,-1,-1],"
				+ "[-1,6,2,8]]");
		board = stringToMatrix("["
				+ "[-1,-1,-1,135,-1,-1,-1,-1,-1,185,-1,-1,-1,-1,105,-1],"
				+ "[-1,-1,92,-1,-1,-1,-1,-1,-1,201,-1,118,-1,-1,183,-1],"
				+ "[-1,-1,-1,-1,-1,-1,-1,-1,-1,179,-1,-1,-1,-1,-1,-1],"
				+ "[-1,248,-1,-1,-1,-1,-1,-1,-1,119,-1,-1,-1,-1,-1,192],"
				+ "[-1,-1,104,-1,-1,-1,-1,-1,-1,-1,165,-1,-1,206,104,-1],"
				+ "[145,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,229,-1],"
				+ "[-1,-1,75,140,-1,-1,-1,-1,-1,-1,-1,-1,43,-1,34,-1],"
				+ "[-1,-1,-1,-1,-1,-1,169,-1,-1,-1,-1,-1,-1,188,-1,-1],"
				+ "[-1,-1,-1,-1,-1,-1,92,-1,171,-1,-1,-1,-1,-1,-1,66],"
				+ "[-1,-1,-1,126,-1,-1,68,-1,-1,-1,-1,-1,-1,-1,-1,-1],"
				+ "[-1,109,-1,86,28,228,-1,-1,144,-1,-1,-1,-1,-1,-1,-1],"
				+ "[-1,-1,-1,-1,59,-1,-1,-1,-1,-1,51,-1,-1,-1,62,-1],"
				+ "[-1,71,-1,-1,-1,63,-1,-1,-1,-1,-1,-1,212,-1,-1,-1],"
				+ "[-1,-1,-1,-1,174,-1,59,-1,-1,-1,-1,-1,-1,133,-1,-1],"
				+ "[-1,-1,62,-1,5,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1]"
				+ ",[-1,-1,-1,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]]");
		

		s.snakesAndLadders(board);
		s2.snakesAndLadders(board);

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is to do breath first search over the 6 possibilities of each cell.
	 * 		And as soon as we find the target cell we return it. 
	 * 
	 * 		What I did was to have a queue of game indexes and its cost. and BFS them.
	 * 		I take the row and column straight from the game index rules.
	 * 		Instead of trying to convert to a format which I already know, like r*c.len + c.
	 * 
	 *			row = N - 1 - (# - 1/N)
	 *			columns
	 *				when n size is odd  -> 
	 *					even rows -> column = (# - 1) % col.len
	 *					odd  rows -> column = col.len - 1 - ((# - 1) % col.len)
	 *
	 *				when n size is even ->
	 *					even rows -> column = col.len - 1 - ((# - 1) % col.len)
	 *					odd  rows -> column = (# - 1) % col.len
	 *
	 * 		This looks pretty simple but there where some challenges regarding the math, 
	 * 		which I had some troubles and didnt created a beautiful solution for it. 
	 * 		but solved the issue.
	 * 
	 * @comments
	 * 		My solution could be a lot cleaner
	 * 
	 * @score
	 *		Runtime: 7 ms, faster than 64.53% of Java online submissions for Snakes and Ladders.
	 *		Memory Usage: 39.5 MB, less than 46.81% of Java online submissions for Snakes and Ladders.
	 * 
	 * @fail 	
	 * 		1) out of bounds, forgot parentheses in the row formula
	 * 		2) calculation of column was not completly right
	 * 		3) parentheses missing again 
	 * 		4) wrong parentheses again
	 * 		5) I was cutting the seen too much
	 * 		6) wrong formula for the column once again
	 * 		7) I was lacking a visited hashmap
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param board
	 * @return
	 */
	public int snakesAndLadders(int[][] board) {


		int n = board.length;
		int total = n * n;
		boolean [] seen = new boolean [total + 1];
		boolean [] visited = new boolean [total + 1];
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(1, 0));

		Pair cur;
		int[] coordinates;
		int row;
		int col;
		while (!q.isEmpty())
		{
			cur = q.poll();

			if (cur.val == total)
			{
				return cur.cost;
			}

			if (visited[cur.val])
				continue;

			visited[cur.val] = true;
			//System.out.println(cur.val);

			for (int i = cur.val + 1; i <= cur.val + 6 && i <= total; i++)
			{
				coordinates = getRowCol(i, n);
				row = coordinates[0];
				col = coordinates[1];
				seen[i] = true;

				if (board[row][col]==-1)
				{
					q.add(new Pair(i, cur.cost + 1));                        
				}
				else
				{
					if (!seen[board[row][col]])
					{
						q.add(new Pair(board[row][col], cur.cost + 1));
						seen[board[row][col]] = true;
					}
				}
			}
		}

		return -1;
	}

	//Deal with inverted rows.
	private int [] getRowCol(int i, int n){
		int row = n - 1 - ((i - 1)/n);
		int col;

		if ((n - 1) % 2 != 0)
		{
			if (row % 2 != 0)
			{
				col = (i - 1) % n;
			}
			else
			{
				col = n - 1 - ((i - 1) % n);
			}

		}
		else
		{

			if (row % 2 == 0)
			{
				col = (i - 1) % n;
			}
			else
			{
				col = n - 1 - ((i - 1) % n);
			}
		}

		return new int[]{row, col};
	}

	class Pair
	{

		int val;
		int cost;
		Pair(int v, int c){
			val = v;
			cost = c;
		}
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * 
 * 
 * What the author does here is to convert the numbers to a r*N + c format.
 * and then extract the columns and rows from there using the usual math formulas.
 * 
 * c = index % N
 * r = index / N
 * 
 * The author also uses a hashmap to prevent loops. 
 * 
 * he only adds to the queue if the element was not added before
 * 
 * 
 * FINDING THE DIRECTION TO MOVE:
 * 
 * 		We can find if we need to move left or right by checking if  N%2 == row %2
 * 
 * 		N is the column length
 * 
 * 		if they are equal then we move from right to left, else we move from left to right
 * 
 * 		check with a 3x3 or a 4x4 matrix. It works and is very very clever.
 * 
 * 
 * @author Nelson Costa
 *
 */
class SnakesandLaddersSolution1 {
	public int snakesAndLadders(int[][] board) {
		int N = board.length;

		Map<Integer, Integer> dist = new HashMap();
		dist.put(1, 0);

		Queue<Integer> queue = new LinkedList();
		queue.add(1);

		while (!queue.isEmpty()) {
			int s = queue.remove();
			if (s == N*N) return dist.get(s);

			for (int s2 = s+1; s2 <= Math.min(s+6, N*N); ++s2) {
				int rc = get(s2, N);
				int r = rc / N, c = rc % N;
				int s2Final = board[r][c] == -1 ? s2 : board[r][c];
				if (!dist.containsKey(s2Final)) 
				{
					dist.put(s2Final, dist.get(s) + 1);
					queue.add(s2Final);
				}
			}
		}
		return -1;
	}

	public int get(int s, int N) {
		// Given a square num s, return board coordinates (r, c) as r*N + c
		int quot = (s-1) / N;
		int row = N - 1 - quot;
		int rem = (s-1) % N;
		int col = row % 2 != N % 2 ? rem : N - 1 - rem;
		return row * N + col;
	}
}
