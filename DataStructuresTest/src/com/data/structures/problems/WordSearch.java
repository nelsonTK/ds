package com.data.structures.problems;

public class WordSearch {
	static  WordSearch w = new WordSearch();
	public static void main(String[] args) {


		char[] c1 = new char[]{'A','B','C','E'};
		char[] c2 = new char[]{'S','F','E','S'};
		char[] c3 = new char[]{'A','D','E','E'};
		char [][] board = new char[3][4];
		board[0] = c1;
		board[1] = c2;
		board[2] = c3;
		String word = "ABCESEEEFS";
		word = "ABCEFSADEESE";

//		board = new char[2][2];
//		board[0] = new char[]{'a','b'};
//		board[1] = new char[]{'c','d'};
//		word = 	"acdb";
		

		System.out.println(w.exist(board, word));

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @fail 
     *     1) forgot to set elements to true
     *     2) (misjudgement)I had the mark of visited in the wrong place, this was a bad trap ahahah and I failed like a duck 
     *     3) the trap was that I needed to restore the original state after exausting one element
	 *
	 * @comments 
		this one was tricky in the sense I forgot that I needed to revert the state of visited nodes...
		I should not forget that in backtracking and can change and revert my move...
		this would allow me to go faster and also to avoid using extra space...
	 * 		
	 * 
	 * @score
	 * 	
	 *	Same code..
	 * 		
	 *		Runtime: 32 ms, faster than 13.22% of Java online submissions for Word Search.
	 *		Memory Usage: 53.9 MB, less than 5.01% of Java online submissions for Word Search.
	 * 			
	 *		Runtime: 11 ms, faster than 29.85% of Java online submissions for Word Search.
	 *		Memory Usage: 42.2 MB, less than 17.93% of Java online submissions for Word Search.
	 * 			
	 * 		
	 * 	
     * @time   O(N*4^L) or O(r*c*4^L) r - rows, c - cols, L word size.
     * @space  O(N) or  O(r*c)
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		//Guards
		//one letter board
		//retangle board



		//traverse the Matrix and check the curr char is equals to the first char of the string
		//if response is true we return true
		//we continue

		//BACKTRACKING
		//Stop condition
		//if we are in the last char and it is equals to string last char, than we return true;
		//if we are out of bounds in the matrix we return false;
		//if we are off the string we return false;

		//processing
		//up    
		//down  
		//left  
		//right 



		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				if (word.charAt(0) == board[row][col])
				{
					if (isPresent(row, col, board, word, 0, new boolean[board.length][board[0].length]))
						return true;
				}
			}
		}

		return false;
	}


	private boolean isPresent(int r, int c, char [][] b, String w, int index, boolean[][] visited)
	{   
		//if outof bounds or has been visited or characters are different we stop 
		if(r < 0 || c < 0 || r >= b.length || c >= b[r].length || visited[r][c])
		{
			return false;
		}


		if (b[r][c] != w.charAt(index))
		{
			return false;
		}

		if (w.charAt(index) == b[r][c] && index == w.length() - 1)
		{
			return true;
		}

		visited[r][c] = true;
        
		if (isPresent(r - 1, c, b, w, index + 1, visited))
			return true;
		if (isPresent(r, c + 1, b, w, index + 1, visited))
			return true;
		if (isPresent(r + 1, c, b, w, index + 1, visited))
			return true;
		if (isPresent(r, c - 1, b, w, index + 1, visited))
			return true;


		visited[r][c] = false;

		return false;

	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Cleaver solution that works in O(1) Space complexity and works by using backtracting and the input matrix
 * 
 * the other cool detail is the offset
 * 
 * @score
	 	Runtime: 19 ms, faster than 17.19% of Java online submissions for Word Search.
		Memory Usage: 52.2 MB, less than 5.10% of Java online submissions for Word Search.
 * @author Nelson Costa
 *
 */
class WordSearchSolution1 {
  private char[][] board;
  private int ROWS;
  private int COLS;

  public boolean exist(char[][] board, String word) {
    this.board = board;
    this.ROWS = board.length;
    this.COLS = board[0].length;

    for (int row = 0; row < this.ROWS; ++row)
      for (int col = 0; col < this.COLS; ++col)
        if (this.backtrack(row, col, word, 0))
          return true;
    return false;
  }

  protected boolean backtrack(int row, int col, String word, int index) {
    /* Step 1). check the bottom case. */
    if (index >= word.length())
      return true;

    /* Step 2). Check the boundaries. */
    if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
        || this.board[row][col] != word.charAt(index))
      return false;

    /* Step 3). explore the neighbors in DFS */
    boolean ret = false;
    // mark the path before the next exploration
    this.board[row][col] = '#';

    int[] rowOffsets = {0, 1, 0, -1};
    int[] colOffsets = {1, 0, -1, 0};
    for (int d = 0; d < 4; ++d) {
      ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
      if (ret)
        break;
    }

    /* Step 4). clean up and return the result. */
    this.board[row][col] = word.charAt(index);
    return ret;
  }
}
