package com.data.structures.problems;

/**
 * https://leetcode.com/problems/design-tic-tac-toe/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DesignTicTacToe {

	static DesignTicTacToe d = new DesignTicTacToe();
	static DesignTicTacToeUnofficialSolution1 toe;
	public static void main(String[] args) {
		//Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
		int n = 3;

		//TicTacToe toe = d.new TicTacToe(3);
		toe = new DesignTicTacToeUnofficialSolution1(3);
		
		toe.move(0, 0, 1);// -> Returns 0 (no one wins)
		//				|X| | |
		//				| | | |    // Player 1 makes a move at (0, 0).
		//				| | | |

		toe.move(0, 2, 2);// -> Returns 0 (no one wins)
		//				|X| |O|
		//				| | | |    // Player 2 makes a move at (0, 2).
		//				| | | |

		toe.move(2, 2, 1);// -> Returns 0 (no one wins)
		//				|X| |O|
		//				| | | |    // Player 1 makes a move at (2, 2).
		//				| | |X|

		toe.move(1, 1, 2);// -> Returns 0 (no one wins)
		//				|X| |O|
		//				| |O| |    // Player 2 makes a move at (1, 1).
		//				| | |X|

		toe.move(2, 0, 1);// -> Returns 0 (no one wins)
		//				|X| |O|
		//				| |O| |    // Player 1 makes a move at (2, 0).
		//				|X| |X|

		toe.move(1, 0, 2);// -> Returns 0 (no one wins)
		//				|X| |O|
		//				|O|O| |    // Player 2 makes a move at (1, 0).
		//				|X| |X|

		toe.move(2, 1, 1);// -> Returns 1 (player 1 wins)
		//				|X| |O|
		//				|O|O| |    // Player 1 makes a move at (2, 1).
		//				|X|X|X|

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is simple the way to avoid O(N^2) time complexity is to use arrays for 
	 * 			-horizontal wins, [the count per row tells you if you have win]
	 * 			-for vertical wins [the count per column tells you if you have win] 
	 * 			-for diagonal wins. [the count in the forward or backwards diagonal tells if you win	]
	 * 			
	 * 			I didn't came up straigth away with this solution.
	 * 			I also went throught a hipotetical priorityQueue and a Hashmap but the patterns 
	 * 			revealed themself and I understood it was suficient to just use an array
	 * 
	 * 		the diagonal wins has the thing that we have a forward diagonal and a backwards diagonal.
	 * 		I took some time calculating it. but after that it was easy to continue.
	 * 
	 * 		
	 * @score
	 * 		Runtime: 7 ms, faster than 37.13% of Java online submissions for Design Tic-Tac-Toe.
	 *		Memory Usage: 44.9 MB, less than 5.12% of Java online submissions for Design Tic-Tac-Toe.
	 * 
	 * 
	 * @comments
	 * 		Initially I was going to implement with out concerning about the user provided and only assume the first user 
	 * 		is the first playing but I decided to play safe because I never know how leetcode plays it's test cases
	 * 		I was confused about the the end of the game, should I do some type of game over to prevent users to play again? didn't understood that part
	 * @fail
	 * 		1) distraction in using the move method in the if with && it cause the move to be run to both players in every iteration
	 * 		2) distraction in implementing switch didn't break and didnt put the if when migrating the code
	 * 
	 * @time O(1) 
	 * 
	 * @space O(N)
	 **/
	class TicTacToe {
		Game player1;
		Game player2;

		public TicTacToe(int n) {
			player1 = new Game(n);
			player2 = new Game(n);
		}

		/** Player {player} makes a move at ({row}, {col}).
	        @param row The row of the board.
	        @param col The column of the board.
	        @param player The player, can be either 1 or 2.
	        @return The current winning condition, can be either:
	                0: No one wins.
	                1: Player 1 wins.
	                2: Player 2 wins. */
		public int move(int row, int col, int player) {
			switch(player) {

			case 1:

				if (player1.move(row, col) != 0)
				{
					player1.gameOver = true;
					player2.gameOver = true;
					return 1;
				}
				break;

			case 2: 

				if (player2.move(row, col) != 0)
				{
					player1.gameOver = true;
					player2.gameOver = true;
					return 2;
				}
				break;
			}

			return 0;
		}
	}

	class Game{
		int [] diagonal;
		int [] horizontal;
		int [] vertical;
		int n;
		boolean gameOver;

		public Game(int n){

			diagonal = new int[2]; //0 forward diagonal; 1 backwarfs diagonal
			horizontal = new int[n];
			vertical = new int[n];
			this.n = n;
		}

		//O(N)
		public int move(int row, int col){
			if (gameOver)
				return 0;

			//diagonal
			//forward diagonal
			if(row == col)
			{
				diagonal[0]++;
			}
			//backwards diagonal 
			if(row == (n - 1) - col)
			{
				diagonal[1]++;
			}

			if(diagonal[0] == n || diagonal[1] == n)
				return 1;

			//horizontal 
			if (++horizontal[row] == n)
				return 1;

			//vertical
			if(++vertical[col] == n)
				return 1;

			return 0;
		}

	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This is the top solution and it uses the same fundamental logic than mine :)
 * 
 * The major difference is in how he substracts the rows. contrary to me, he just uses one array for both players.
 * 
 * and if the row or column or diagonal have value equals to n, it means nobody has written to that line.
 * 
 * so for one player he adds a value and for other he subtracts. if two players have cells in the same line it will never be n. becase one player adds and the other subtracts.
 * 
 * you have to use absolute value
 * 
 * 
 * @author Nelson Costa
 *
 */
class DesignTicTacToeUnofficialSolution1 {
    int[] R, C;
    int diagonal, antidiag;
    /** Initialize your data structure here. */
    public DesignTicTacToeUnofficialSolution1(int n) {
      R = new int[n];
      C = new int[n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1:-1, len = C.length;
        R[row] += toAdd;
        C[col] += toAdd;
        
        if(row == col)  diagonal += toAdd;
        if(col == len - row - 1)  antidiag += toAdd;
        
        
        if(Math.abs(R[row]) == len ||
          Math.abs(C[col]) == len ||
          Math.abs(diagonal) == len ||
          Math.abs(antidiag) == len
          ){
          return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */