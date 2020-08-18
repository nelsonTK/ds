package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
	 * 	@intuition
	 * 		Despite it has worked it consumes too much memory
	 * 
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
			//if snake piece is tail is game over
			if (board[r][c] == 1)
			{
				return -1;
			}
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


/*********************
 * OTHERS SOLUTIONS
 *********************/
class DesignSnakeGameUnofficialSolution1{
	class SnakeGame {

		//2D position info is encoded to 1D and stored as two copies 
		Set<Integer> set; // this copy is good for fast loop-up for eating body case
		Deque<Integer> body; // this copy is good for updating tail
		int score;
		int[][] food;
		int foodIndex;
		int width;
		int height;

		public SnakeGame(int width, int height, int[][] food) {
			this.width = width;
			this.height = height;
			this.food = food;
			set = new HashSet<>();
			set.add(0); //intially at [0][0]
			body = new LinkedList<>();
			body.offerLast(0);
		}


		public int move(String direction) {
			//case 0: game already over: do nothing
			if (score == -1) {
				return -1;
			}

			// compute new head
			int rowHead = body.peekFirst() / width;
			int colHead = body.peekFirst() % width;
			switch (direction) {
			case "U" : rowHead--;
			break;
			case "D" : rowHead++;
			break;
			case "L" : colHead--;
			break;
			default :  colHead++;
			}
			int head = rowHead * width + colHead;

			//case 1: out of boundary or eating body
			set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily 
			if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
				return score = -1;
			}

			// add head for case2 and case3
			set.add(head); 
			body.offerFirst(head);

			//case2: eating food, keep tail, add head
			if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
				set.add(body.peekLast()); // old tail does not change, so add it back to set
				foodIndex++;
				return ++score;
			}

			//case3: normal move, remove tail, add head
			body.pollLast();
			return score;

		}
	}
}