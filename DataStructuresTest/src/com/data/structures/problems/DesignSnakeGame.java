package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;

public class DesignSnakeGame extends LeetCodeExercise{

	static DesignSnakeGame d = new DesignSnakeGame();
	public static void main(String[] args) {
		int width = 3;
		int height = 2;
		int [][] food = stringToMatrix("[[1,2],[0,1]]");
		SnakeGame s = d.new SnakeGame(width, height, food);

		System.out.println(s.move("R"));
		System.out.println(s.move("D"));
		System.out.println(s.move("R"));
		System.out.println(s.move("U"));
		System.out.println(s.move("L"));
		System.out.println(s.move("U"));

	}
	/**
	 * 	@score
	 * 		Runtime: 260 ms, faster than 5.02% of Java online submissions for Design Snake Game.
	 * 		Memory Usage: 571.8 MB, less than 5.62% of Java online submissions for Design Snake Game.
	 *  
	 *  @fail 
	 * 			1) directions in the switch were wrong 
	 * 			2) I switched the constructor parameter w for h 
	 * 			3) index out of bounds in foods. I forgot about gameover. 
	 * 			4) misread the description. what I need to return is the current level
	 * 			5) Looks like the head of the queue was wrong
	 * 			6) had not the level update correctly
	 * 			7) failed. the snake colides with itself wrongly because the tail is no longer there
	**/ 
	class SnakeGame {

		/** Initialize your data structure here.
	        @param width - screen width
	        @param height - screen height 
	        @param food - A list of food positions
	        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
		ArrayDeque<Coordinate> q;
		int [][] food;
		int level; // means the index of the food element
		int [][] board;

		public SnakeGame(int w, int h, int[][] food) {
			q = new ArrayDeque<>();
			this.food = food;
			board = new int [h][w]; 
			level = 0;
			board[0][0] = 1;
			q.add(new Coordinate(0, 0));
		}

		/** Moves the snake.
	        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	        @return The game's score after the move. Return -1 if game over. 
	        Game over when snake crosses the screen boundary or bites its body. */
		public int move(String direction) {
			int r = q.getLast().r;
			int c = q.getLast().c;

			switch(direction){
			case "U":
				return newPosition(r - 1, c);

			case "L":
				return newPosition(r, c - 1);

			case "R":
				return newPosition(r, c + 1);

			case "D":
				return newPosition(r + 1, c);
			}
			System.out.println("This is not expected");
			return -1;


			//if gameover -1, if it level 0 return 1, if head level 1 return next level
		}

		private int newPosition(int r, int c){

			//if out of bound or snake then we game over
			if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
			{
				return -1;

			}
			else if (level < food.length && food[level][0] == r && food[level][1] == c)
			{
				//add new head
				q.add(new Coordinate(r, c));
				board[r][c] = 1;
				level++;
				return level;

			}
            Coordinate tail = q.poll();
            board[tail.r][tail.c] = 0;
            //if snake piece is not tail is game over
			if (board[r][c] == 1)
			{
				return -1;
			}//if food we take it 
			//if not food nor snake nor out of bounds is just a valid cell
			else
			{
				//move tail to head;
				//increment level
				//return level;
				
				
				q.add(new Coordinate(r, c));
				board[r][c] = 1;
				return level;
			}

		}


		class Coordinate{
			int r, c;

			public Coordinate(int r, int c){
				this.r = r;
				this.c = c;
			}
		}
	}

}
