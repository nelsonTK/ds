package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RottingOranges {

	public static void main(String[] args) {

		RottingOranges r = new RottingOranges();
		RottingOrangesSolution rs = new RottingOrangesSolution();
		//		Possible =() 4
		//		int [][] g = new int [3][3];
		//		g[0] = new int [] {2,1,1};
		//		g[1] = new int [] {0,1,1};
		//		g[2] = new int [] {0,1,1};

		//		 Impossible = -1
		int [][] g = new int [3][3];
		g[0] = new int [] {2,1,1};
		g[1] = new int [] {0,1,1};
		g[2] = new int [] {1,0,1};

		// possible = 0
		//int [][] g = new int [1][2];
		//g[0] = new int [] {0,2};

		// possible = 0
		//		 int [][] g = new int [1][1];
		//		 g[0] = new int [] {0};

		//		 int [][] g = new int [3][1];
		//		 g[0] = new int [] {1};
		//		 g[1] = new int [] {2};
		//		 g[2] = new int [] {2};


		//				 int [][] g = new int [4][1];
		//				 g[0] = new int [] {1};
		//				 g[1] = new int [] {2};
		//				 g[2] = new int [] {1};
		//				 g[3] = new int [] {2};		

		//				 int [][] g = new int [3][3];
		//				 g[0] = new int [] {2,1,1};
		//				 g[1] = new int [] {0,1,1};
		//				 g[2] = new int [] {2,0,1};


		//				 int [][] g = new int [1][7];
		//				 g[0] = new int[]{1,2,1,1,2,1,1};

		for(int i = 0; i < g.length; i++)
		{
			System.out.println(Arrays.toString(g[i]));
		}
		
		System.out.println("minutes to rotten everything Solution: " + rs.orangesRotting(g));

		System.out.println("minutes to rotten everything: " + r.orangesRotting(g));
	}


/***************************************
 * 
 * LAST AND FINAL ATTEMPT WITH CORRECT INTERPRETATION, WHOO
 * 
 **************************************/

	int totalRottenOranges;

	/**
	 * 		Runtime: 2 ms, faster than 97.94% of Java online submissions for Rotting Oranges.
			Memory Usage: 39 MB, less than 81.25% of Java online submissions for Rotting
	 * 
	 * 
	 * FINALYY!!!!!
	 * 
	 * Very Hard to understand what they were asking
	 * @time O(NxM) + O(NxM) = O(NxM)
	 * @space O(NxM) 
	 * @param g
	 * @return
	 */
	public int orangesRotting(int[][] g) {

		Queue<Orange> q = new ArrayDeque<>();
		int totalFreshOranges = 0, mins = 0;
		totalRottenOranges = 0;
		Orange curr;

		for (int r = 0; r < g.length; r++) {
			for (int c = 0; c < g[r].length; c++) {
				if (g[r][c] == 2)
				{
					q.add(new Orange(r,c,g[r][c], 0));
					g[r][c] = 0;
				}
				else if (g[r][c] == 1)
				{
					totalFreshOranges++;
				}
			}
		}

		while(!q.isEmpty())
		{
			curr = q.poll();
			enqueueOranges(curr.r, curr.c, g, curr.timeStamp + 1, q);
			mins = curr.timeStamp;
		}

		return totalFreshOranges == totalRottenOranges? mins : -1;
	}


	public void enqueueOranges(int r, int c, int [][] g, int timestamp, Queue<Orange> q)
	{
		enqueueOrange(r - 1, c, g, timestamp, q);
		enqueueOrange(r + 1, c, g, timestamp, q);
		enqueueOrange(r, c + 1, g, timestamp, q);
		enqueueOrange(r, c - 1, g, timestamp, q);
	}

	public void enqueueOrange(int r, int c, int [][] g, int timestamp, Queue<Orange> q)
	{
		if (r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] != 1)
		{
			return;
		}

		if (g[r][c] == 1)
		{
			q.add(new Orange(r, c, g[r][c], timestamp));
			g[r][c] = 0;
			totalRottenOranges++;
		}
	}




/***************************************
 * 
 * FIRST SET OF ATTEMPTS AND MISS INTERPRETATIONS
 * 
 **************************************/


	boolean[][] visitedFinal;

	/**
	 * I don't know If I've interpreted the question well.
	 * 
	 * to find the minimal time, I though that I would need to calculate the rotten time from each different rotten orange.
	 * this means that I dont clear the oranges from the original array but only mark them visited from a boolean matrix
	 * I use bread first search
	 * 
	 * It looks almost certain that I miss interpreted this question
	 * 
	 * 
	 * @failed the case with one element only in the array
	 * 
	 * @time  O(N^2) it looks linear time the breat first search itself, the iteration of the nodes is O(N), so a natural answer would be O(N^2) but I have doubts.
	 * @space O(2N) = O(N)
	 * @param g
	 * @return
	 */
	public int orangesRotting2(int[][] g) {

		int current;
		int minTimeToRottenAll = -1;
		int minsToRottenGroup = 0;
		int totalFreshOranges = 0;
		int orangesRotten = 0;
		//boolean[][] visitedtmp ;
		visitedFinal = new boolean[g.length][g[0].length];
		Orange currentOrange;

		Queue<Orange> q = new ArrayDeque<>();
		for (int r = 0; r < g.length; r++) {
			for (int c = 0; c < g[0].length; c++) {
				current = g[r][c];

				if (current == 1)
				{
					totalFreshOranges++;
				}
				else if (current == 2)
				{
					q.add(new Orange(r, c, g[r][c], 0));
					g[r][c]= 0;
					minsToRottenGroup = 0;

					while (!q.isEmpty())
					{
						currentOrange = q.poll();
						enqueueNeigbors(q, currentOrange.r, currentOrange.c, visitedFinal, g, currentOrange.timeStamp + 1);
						minsToRottenGroup = Math.max(minsToRottenGroup, currentOrange.timeStamp);					
						g[r][c]= 0;

					}
					minTimeToRottenAll = Math.max(minTimeToRottenAll, minsToRottenGroup);
				}
			}
		}

		for (int i = 0; i < visitedFinal.length; i++) {
			for (int j = 0;j < visitedFinal[i].length; j++) {
				if(visitedFinal[i][j])
				{
					orangesRotten++;	
				}
			}
		}

		//if all oranges rotten, we retrieve the time. if zero oranges exists, we retrieve zero, else we didn't processed all oranges
		if (totalFreshOranges == orangesRotten)
			return totalFreshOranges != 0 ? minTimeToRottenAll : totalFreshOranges;
		else return  -1;
	}



	public void enqueueNeigbors(Queue<Orange> q, int r, int c, boolean [][] visited, int [][]array, int rottenTimeStamp)
	{
		enqueue(q, r - 1, c, visited, array, rottenTimeStamp);
		enqueue(q, r + 1, c, visited, array, rottenTimeStamp);
		enqueue(q, r, c - 1, visited, array, rottenTimeStamp);
		enqueue(q, r, c + 1, visited, array, rottenTimeStamp);
	}

	public void enqueue(Queue<Orange> q, int r, int c, boolean [][] visited, int [][]array, int rottenTimeStamp) {

		if(r < 0 || c < 0 || r >= array.length || c >= array[0].length)
		{
			return;
		}


		if (!visited[r][c] && array[r][c] == 1)
		{
			visited[r][c] = true;
			visited[r][c] = true;
			q.add(new Orange(r, c, array[r][c], rottenTimeStamp));	
		}
	}



/***************************************
 * 
 * FIRST SET OF ATTEMPTS AND MISS INTERPRETATIONS
 * 
 **************************************/


	private int rottenOranges = 0;

	/**
	 * @failed have not get it right at first, forgot to add to the minutes as I deepen the search. Secondly I was not putting visited nodes for the rotten oranges.
	 *  I'am conting the minutes incorrectly. I mistaken completely, this is a Bread first search question....
	 * 
	 * @time O(MxN) I believe so because each entry is processed only one time
	 * @space O(MxN) the worst case I believe is MxN, which is the case where one cell is 2 and rottens everything else. the stacks goes MxN before backtracking
	 * @param grid
	 * @return
	 */
	public int orangesRottingDfs(int[][] grid) {

		if (grid == null || grid.length < 1 || grid[0].length < 1)
		{
			throw new IllegalArgumentException();
		}

		int initialFreshOranges = 0;
		int minutes = -1;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {

				if (grid[row][col] == 1 || grid[row][col] == 3)
				{
					initialFreshOranges++;
				}

				if (grid[row][col] == 2) {
					minutes = Math.max(minutes, rotten(grid, row, col, 0));
				}
			}
		}

		return rottenOranges == initialFreshOranges ? minutes : - 1;
	}

	/**
	 * 3 is my custom visited state
	 * 2 means a rotten orange
	 * 1 means a fresh orange
	 * 0 means a empty cell
	 * 
	 * @param g matrix
	 * @param r row
	 * @param c column
	 * @param mins minutes
	 * @return
	 */
	public int rotten(int [][] g, int r, int c, int mins) {


		if (r < 0 || c < 0 || c >= g[0].length || r >= g.length )
		{
			return mins - 1;
		}

		//if is empty cell, if is a visited fresh orange which rottened, or is a rotten orange from the start go back 
		if (g[r][c] == 0 || g[r][c] >= 3 || g[r][c] == 2 && mins != 0) {
			return mins - 1;
		} 

		if (g[r][c] == 1) {
			g[r][c] = 3; //mark orange as visited
			rottenOranges++;
		}

		if (g[r][c] == 2) {
			g[r][c] = 4;
		}


		mins = Math.max(mins, rotten(g, r - 1, c, mins + 1));
		mins = Math.max(mins, rotten(g, r, c + 1, mins + 1));
		mins = Math.max(mins, rotten(g, r + 1, c, mins + 1));
		mins = Math.max(mins, rotten(g, r, c - 1, mins + 1));

		return mins;
	}
}

class Orange {
	int r; //row
	int c; //column
	int v; //value
	int timeStamp; //rotten Stamp

	public Orange(int r, int c, int v, int s) {
		super();
		this.r = r;
		this.c = c;
		this.v = v;
		this.timeStamp = s;
	}
}


/**
 * leetcode solution
 *
 */
class RottingOrangesSolution {

	//this is the many direction variations of of going up right, botton left
	int[] dr = new int[]{-1, 0, 1, 0};
	int[] dc = new int[]{0, -1, 0, 1};

	public int orangesRotting(int[][] grid) {
		int R = grid.length, C = grid[0].length;

		// queue : all starting cells with rotten oranges
		Queue<Integer> queue = new ArrayDeque();
		Map<Integer, Integer> depth = new HashMap();
		for (int r = 0; r < R; ++r)
			for (int c = 0; c < C; ++c)
				if (grid[r][c] == 2) {
					int code = r * C + c;
					queue.add(code);
					depth.put(code, 0);
				}

		int ans = 0;
		while (!queue.isEmpty()) {
			//get current code that points to the depth
			int code = queue.remove();
			int r = code / C, c = code % C;
			
			//add to the queue up, right, bottom, and left
			for (int k = 0; k < 4; ++k) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
					grid[nr][nc] = 2;
					int ncode = nr * C + nc;
					queue.add(ncode);
					//the current item in the queue has always the most recent depth
					depth.put(ncode, depth.get(code) + 1);
					ans = depth.get(ncode);
				}
			}
		}

		//search leftovers 
		for (int[] row: grid)
			for (int v: row)
				if (v == 1)
					return -1;
		return ans;

	}
}
